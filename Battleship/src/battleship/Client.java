package battleship;
import java.net.*;
import java.io.*;
import java.util.*;


public class Client {
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	
	//Client GUI
	private MultiplayerMenu gui;
	
	private String server;
	private String username;
	private int port;
	
	/*
	 * constructor
	 */
	public Client(String server, String username, int port, MultiplayerMenu gui) {
		this.server = server;
		this.username = username;
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
		
//		//try to send username to server
//		try {
//			out.writeObject(username);
//		} catch (IOException eIO) {
//			eIO.printStackTrace();
//			disconnect();
//			return false;
//		}
		
		//success
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
	 * disconnect
	 * close input/output streams
	 */
	private void disconnect() {
		try { 
			if(in!= null) in.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(out != null) out.close();
		}
		catch(Exception e) {} // not much else I can do
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do
		
		// inform the GUI
		if(gui != null)
			gui.connectionFailed();
	}
	
	/*
	 * run client
	 */
	public static void main(String[] args) {
		int portNum = 5335;
		String serverAdd = "localhost";
		String user = "sam";
		MultiplayerMenu mm = new MultiplayerMenu(serverAdd, portNum);
		
		//create client
		Client client = new Client(serverAdd, user, portNum, mm);
		
		if(!client.start()) {
			return;
		}
		
//		while(true) {
//			
//		}
	}
	
	/*
	 * a class that waits for the message from the server and append them to the JTextArea
	 * if we have a GUI or simply System.out.println() it in console mode
	 */
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try {
					ServerInfo server = (ServerInfo) in.readObject();
					// if console mode print the message and add back the prompt
					gui.append(server);
				}
				catch(IOException e) {
					System.out.println("Server has closed the connection: " + e);
					if(gui != null) 
						gui.connectionFailed();
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
	
	
	
	
	
	
}
