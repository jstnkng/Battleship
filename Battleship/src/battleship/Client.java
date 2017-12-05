package battleship;


import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Client for MultiplayerMenu used to communicate with server.
 * @author Sam Carson
 *
 */
public class Client {
	
	/**
	 * output stream.
	 */
	private ObjectOutputStream out;
	
	/**
	 * input stream.
	 */
	private ObjectInputStream in;
	
	/**
	 * socket.
	 */
	private Socket socket;
	
	/**
	 * gui for menu.
	 */
	private MultiplayerMenu gui;
	
	/**
	 * ip address.
	 */
	private String server;
	
	/**
	 * port number.
	 */
	private int port;
	
	/**
	 * ip address of this client.
	 */
	private String myIP;
	
	/**
	 * constructor.
	 * 
	 * @param server ip address
	 * @param port number
	 * @param gui gui
	 */
	public Client(final String server, final int port, final MultiplayerMenu gui) {
		this.server = server;
		this.port = port;
		this.gui = gui;
		
		//get ip of client
		String ip = "";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));

			ip = in.readLine(); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//set ip of client
		myIP = ip;
		
		
	}
	

	/**
	 * start.
	 * 
	 * @return if client starts
	 */
	public boolean start() {
		
		//try to connect to server
		try {
			socket = new Socket(server, port);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//print to console that it is connected
		System.out.println("Connection accepted " 
				+ socket.getInetAddress() + ":" + socket.getPort());
		
		//try to create the object streams
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException eIO) {
			eIO.printStackTrace();
			return false;
		}
		
		//create the Thread to listen from server
		new ListenFromServer().start();
		
		//client start was successful
		return true;
		
	}
	
	
	/**
	 * send serverInfo to server.
	 * 
	 * @param server info of server
	 */
	public void sendServerInfo(final ServerInfo server) {
		try {
			out.writeObject(server);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns the ip of the client.
	 * 
	 * @return ip of client
	 */
	public String getMyIP() {
		return myIP;
	}
	
	
	/**
	 * disconnect
	 * close input/output streams.
	 */
	public void disconnect() {
		try { 
			if (in != null) in.close();
		}
		catch (Exception e) { }
		try {
			if (out != null) out.close();
		}
		catch (Exception e) { }
        try {
			if (socket != null) socket.close();
		}
		catch (Exception e) { }
		
		// inform the GUI
		if (gui != null) {
			gui.connectionFailed();
		}
		
		//System.exit(0);
	}

//	/**
//	 * run client.
//	 * 
//	 * @param args args
//	 */
//	public static void main(String[] args) {
//		int portNum = 5445;
//		//aws ip = 13.58.209.10
//		String serverAdd = "13.58.209.10";
//		MultiplayerMenu gui = new MultiplayerMenu();
//		
//		//create client
//		Client client = new Client(serverAdd, portNum, gui);
//		
//		gui.start(client);
//		
//		if (!client.start()) {
//			return;
//		}
//		
//		
//	}
	
	/**
	 * a class that waits for the serverInfo object from the server and then adds
	 * it to the table in the gui.
	 */
	class ListenFromServer extends Thread {
		
		/**
		 * loops forever.
		 */
		public void run() {
			
			//waits for serverInfo objects
			while (true) {
				try {
					ServerInfo server = (ServerInfo) in.readObject();
					
					System.out.println("Type: " + server.getType());
					
					//checks whether to add or delete a server
					if (server.getType() == 0) {
						//sends serverInfo to the gui to add to the table
						gui.append(server);
					} else {
						//remove serverInfo from gui
						gui.delete(server);
					}
					
					
					
				}
				catch (IOException e) {
					System.out.println("Server has closed the connection: " + e);
					if (gui != null) 
						gui.connectionFailed();
					break;
				}
				catch (ClassNotFoundException e2) {
				}
			}
		}
	}
	
}
