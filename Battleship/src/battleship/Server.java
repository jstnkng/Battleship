package battleship;
import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
	
	private int port;
	private boolean keepGoing;
	private static int uniqueId;
	private ArrayList<ClientThread> clients;
	private ArrayList<ServerInfo> serverList;
	
	/*
	 * constructor
	 */
	public Server(int port) {
		this.port = port;
		clients = new ArrayList<ClientThread>();
		serverList = new ArrayList<ServerInfo>();
	}
	
	/*
	 * start
	 */
	public void start() {
		keepGoing = true;
		
		//try to create serverSocket and wait for connections
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(keepGoing) {
				
				System.out.println("Waiting for clients");
				
				Socket socket = serverSocket.accept();
				
				//if asked to stop
				if(!keepGoing) {
					break;
				}
				
				ClientThread thread = new ClientThread(socket);
				clients.add(thread);
				thread.start();
			}
			
			//asked to stop
			try {
				serverSocket.close();
				for(int i = 0; i < clients.size(); i ++) {
					ClientThread tc = clients.get(i);
					try {
						tc.in.close();
						tc.out.close();
						tc.socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				System.out.println("Exception, closing server and clients");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
		}
	}
	
	/*
	 * broadcast server to all clients
	 */
	private synchronized void broadcast(ServerInfo server) {
		
		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for(int i = clients.size(); --i >= 0;) {
			ClientThread ct = clients.get(i);
			// try to write to the Client if it fails remove it from the list
			if(!ct.writeMsg(server)) {
				clients.remove(i);
				System.out.println("Disconnected Client");
			}
		}
	}
	
	/*
	 * for a disconnected client
	 */
	public void remove(int id) {
		// scan the array list until we found the Id
		for(int i = 0; i < clients.size(); ++i) {
			ClientThread ct = clients.get(i);
			// found it
			if(ct.id == id) {
				clients.remove(i);
				return;
			}
		}
	}
	
	/*
	 * run server
	 */
	public static void main(String[] args) {
		int portNum = 5335;
		
		Server server = new Server(portNum);
		server.start();
	}
	
	
	/** One instance of this thread will run for each client */
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		// my unique id (easier for deconnection)
		int id;
		// the Username of the Client
		//String username;
		// the only type of message a will receive
		ServerInfo server;
		

		// Constructore
		ClientThread(Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;
			/* Creating both Data Stream */
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				// create output first
				out = new ObjectOutputStream(socket.getOutputStream());
				in  = new ObjectInputStream(socket.getInputStream());
				// read the username
				//username = (String) in.readObject();
				System.out.println(" connected");
				
			}
			catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			//try to send arraylist of servers
			try {
				if(serverList != null) {
					for(ServerInfo server: serverList) {
						out.writeObject(server);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			boolean keepGoing = true;
			while(keepGoing) {
				// read a String (which is an object)
				try {
					server = (ServerInfo) in.readObject();
				}
				catch (IOException e) {
					e.printStackTrace();
					break;				
				}
				catch(ClassNotFoundException e2) {
					break;
				}
				
				broadcast(server);
				serverList.add(server);
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}
		
		// try to close everything
		private void close() {
			// try to close the connection
			try {
				if(out != null) out.close();
			}
			catch(Exception e) {}
			try {
				if(in != null) in.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		/*
		 * Write a server to the Client output stream
		 */
		private boolean writeMsg(ServerInfo server) {
			// if Client is still connected send the message to it
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				out.writeObject(server);
				//out.close();
			}
			// if an error occurs, do not abort just inform the user
			catch(IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}
