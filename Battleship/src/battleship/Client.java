package battleship;
import java.net.*;
import java.io.*;
import java.util.*;


/**
 * Client for server menu used to communicate with server
 * @author Sam Carson
 *
 */
public class Client {
	
private ObjectOutputStream out;
	
	private ObjectInputStream in;
	
	private Socket socket;
	
	private MultiplayerMenu gui;
	
	private String server;
	
	private int port;
	
	/*
	 * constructor
	 */
	public Client(String server, int port, MultiplayerMenu gui) {
		this.server = server;
		this.port = port;
		this.gui = gui;
	}
	

	/*
	 * start
	 */
	public boolean start() {
		
		//try to connect to server
		try {
			socket = new Socket(server, port);
		} catch(Exception e) {
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
	
	public String getInetAddress() {
		String ip = "";
		
		ip = socket.getInetAddress().toString();

		return ip;
		
	}
	
	
	/*
	 * send serverInfo to server
	 */
	public void sendServerInfo(ServerInfo server) {
		try {
			out.writeObject(server);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * create game server
	 */
	public void createServer(String ip, int portNum) {
		
		GameServer gs = new GameServer(ip, portNum);
		gs.start();
		//disconnect();
	}
	
	/*
	 * join game server
	 */
	public void joinServer(String ip, int portNum) {
		
		GameClient gc = new GameClient(ip, portNum);
		gc.start();
		//disconnect();
	}
	
	
	/*
	 * disconnect
	 * close input/output streams
	 */
	private void disconnect() {
		try { 
			if(in!= null) in.close();
		}
		catch(Exception e) {}
		try {
			if(out != null) out.close();
		}
		catch(Exception e) {}
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {}
		
		// inform the GUI
		if(gui != null) {
			gui.connectionFailed();
		}
		
		System.exit(0);
	}
	
	/*
	 * run client
	 */
	public static void main(String[] args) {
		int portNum = 5335;
		String serverAdd = "localhost";
		MultiplayerMenu gui = new MultiplayerMenu();
		
		//create client
		Client client = new Client(serverAdd, portNum, gui);
		
		gui.start(client);
		
		if(!client.start()) {
			return;
		}
		
		
	}
	
	/*
	 * a class that waits for the message from the server and append them to the JTextArea
	 * if we have a GUI or simply System.out.println() it in console mode
	 */
	class ListenFromServer extends Thread {

		public void run() {

			//waits for serverInfo objects
			while(true) {
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
				catch(IOException e) {
					System.out.println("Server has closed the connection: " + e);
					if(gui != null) 
						gui.connectionFailed();
					break;
				}
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}

}
