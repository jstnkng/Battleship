package battleship;


import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Server hosted on aws to keep track of multiplayer games.
 * @author Sam Carson
 *
 */
public class Server extends Thread{
	
	/**
	 * port number for server.
	 */
	private int port;
	
	/**
	 * boolean whether to stop or keep going.
	 */
	private boolean keepGoing;
	
	/**
	 * unique id for clients.
	 */
	private static int uniqueId;

	/**
	 * list of clients connected.
	 */
	private ArrayList<ClientThread> clients;

	/**
	 * list of servers .
	 */
	private ArrayList<ServerInfo> serverList;

	
	/**
	 * constructor.
	 * 
	 * @param port for the server
	 */
	public Server(final int port) {
		this.port = port;
		clients = new ArrayList<ClientThread>();
		serverList = new ArrayList<ServerInfo>();
	}

	/**
	 * start.
	 */
	public void run() {
		keepGoing = true;

		//try to create serverSocket and wait for connections
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			while (keepGoing) {

				System.out.println("Waiting for clients");

				Socket socket = serverSocket.accept();

				//if asked to stop
				if (!keepGoing) {
					break;
				}

				//creates thread for client communication
				ClientThread thread = new ClientThread(socket);

				//adds the thread to a list of clients
				clients.add(thread);

				//starts the thread
				thread.start();
			}

			//asked to stop
			try {
				serverSocket.close();
				for (int i = 0; i < clients.size(); i++) {
					ClientThread tc = clients.get(i);

					//close the input stream, output stream, 
					//and socket of all clients
					try {
						tc.in.close();
						tc.out.close();
						tc.socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				System.exit(0);
				
			} catch (Exception e) {
				System.out.println("Exception, closing server and clients");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
		}
	}

	/**
	 * broadcast server to all clients.
	 * 
	 * @param server info of the server
	 */
	private synchronized void broadcast(final ServerInfo server) {

		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for (int i = clients.size(); --i >= 0;) {
			ClientThread ct = clients.get(i);
			// try to write to the Client if it fails remove it from the list
			if (!ct.writeMsg(server)) {
				clients.remove(i);
				System.out.println("Disconnected Client");
			}
		}
	}


	/**
	 * for a disconnected client to be removed.
	 * 
	 * @param id unique id of client
	 */
	public void remove(final int id) {

		// scan the array list until we found the Id
		for (int i = 0; i < clients.size(); ++i) {
			ClientThread ct = clients.get(i);
			//if client to remove is found
			if (ct.id == id) {
				clients.remove(i);
				System.out.println("client removed from list of clients");
				//return;
				break;
			}
		}

		//find and remove server from list if client started one
		for (ServerInfo s: serverList) {
			if (s.getID() == id) {
				serverList.remove(s);
				System.out.println("server removed from list of servers");

				//create ServerInfo object with type 1 for deletion
				ServerInfo server = new ServerInfo(null, null, s.getIP(), 1);

				//broadcast server deletion to all clients
				broadcast(server);
				break;
			}
		}


	}
	
	/**
	 * run server.
	 * @param args Array of strings for main method
	 */
	public static void main(String[] args) {
		int portNum = 5445;

		Server server = new Server(portNum);
		server.start();
	}


	/**
	 * class for communicating with the client.
	 *
	 */
	class ClientThread extends Thread {

		/**
		 * socket.
		 */
		Socket socket;
		/**
		 * input stream to communicate.
		 */
		ObjectInputStream in;
		/**
		 * output stream to communicate.
		 */
		ObjectOutputStream out;

		/**
		 * unique id of client.
		 */
		int id;

		/**
		 * serverInfo object that the server recieves.
		 */
		ServerInfo server;


		/**
		 * constructor.
		 * 
		 * @param socket socket to be used
		 */
		ClientThread(final Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;

			//create the input output streams
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				// create output first
				out = new ObjectOutputStream(socket.getOutputStream());
				in  = new ObjectInputStream(socket.getInputStream());
				System.out.println(" connected");

			}
			catch (IOException e) {
				e.printStackTrace();
				return;
			}

			//try to send arraylist of servers when client connects
			try {
				if (serverList != null) {
					for (ServerInfo server: serverList) {
						out.writeObject(server);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		/**
		 * runs until told not to.
		 */
		public void run() {

			boolean keepGoing = true;
			while (keepGoing) {
				//Receive serverInfo object from client
				try {
					server = (ServerInfo) in.readObject();

					//links client and client created server by ID
					server.setID(id);

				}
				catch (IOException e) {
					System.out.println("Client disconnected");
					keepGoing = false;
					break;				
				}
				catch (ClassNotFoundException e2) {
					break;
				}

				//end thread
				//type is equal to 1 for disconnect
				if (server.getType() == 1) {
					keepGoing = false;
					break;
				}

				//send serverInfo object to all clients
				broadcast(server);

				//add the serverInfo object to the list of servers
				serverList.add(server);
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}

		/**
		 * close the connections.
		 */
		private void close() {
			// try to close the connection
			try {
				if (out != null) out.close();
			}
			catch (Exception e) { }
			try {
				if (in != null) in.close();
			}
			catch (Exception e) { };
			try {
				if (socket != null) socket.close();
			}
			catch (Exception e) { }
		}

		/**
		 * Write a serverInfo object to the Client output stream.
		 * 
		 * @param server info of server
		 * @return boolean if message could be written
		 */
		private boolean writeMsg(final ServerInfo server) {
			// if Client is still connected send the message to it
			if (!socket.isConnected()) {
				close();
				return false;
			}
			// write the serverInfo object to the stream
			try {
				out.writeObject(server);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

}
