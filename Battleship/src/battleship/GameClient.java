package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import battleship.Client.ListenFromServer;

public class GameClient extends Thread {
	
	private int port;
	private String server;
	private ShipSetupFrame ssf;
	private int[][] p2Values = new int[10][10];
	private int[][] p1Values = new int[10][10];
	private boolean ready = false;
	private Socket socket;
	
	
	public GameClient(String server, int port) {
		this.port = port;
		this.server = server;
		
	}
	
	/*
	 * start
	 */
	public void run() {
		
		ssf = new ShipSetupFrame(GameMode.MultiplayerMode, 2);
		
		System.out.println("Waiting for p2 to set ships");
		while(!ssf.getAreShipsSet()) {
			//System.out.println("sorry");
		}
		System.out.println("p2 ships set");
		
		p2Values = ssf.getPlayer2Values();
		
		//try to connect to server
		try {
			socket = new Socket(server, port);
		} catch(Exception e) {
			e.printStackTrace();
		}
			
			
		//print to console that it is connected
		System.out.println("Connection accepted " 
				+ socket.getInetAddress() + ":" + socket.getPort());
		
		//try to create the object streams
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			//send server ship values
			out.writeObject(p2Values);
			
			//try to get hosts ship values
			p1Values = (int[][]) in.readObject();
			
			//create gameboard
			//vals switched for player2's board
//			oh = new OnlineHandler(p2Values, p1Values);
//			oh.start();
			
			(new Thread(new OnlineGameBoard(p2Values, p1Values))).start();
			
			
		} catch (IOException eIO) {
			eIO.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		while(true) {
			//play the game
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		int portNum = 5445;
		String ip = "";
		
		try {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		ip = in.readLine(); 
		} catch (Exception e) {
			
		}
		System.out.println(ip);
		
		GameClient client = new GameClient(ip, portNum);
		//client.start();
	}
}
	
	