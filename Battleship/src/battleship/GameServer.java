package battleship;
import java.io.*;
import java.net.*;
import java.util.*;

import battleship.Server.ClientThread;


public class GameServer {
	
	private int port;
	private String server;
	private OnlineGameBoard p1GameBoard;
	private ShipSetupFrame ssf;
	private int[][] values = new int[10][10];
	private boolean ready = false;
	
	
	public GameServer(String server, int port) {
		this.port = port;
		this.server = server;
		
		start();
	}
	
	/*
	 * start
	 */
	public void start() {
		
		ssf = new ShipSetupFrame(GameMode.MultiplayerMode, 1);
		
		System.out.println("Waiting for host to set ships");
		while(!ssf.getAreShipsSet()) {
			//System.out.println("sorry");
		}
		System.out.println("Host ships set");
		
		values = ssf.getPlayer1Values();
		
		//try to create serverSocket
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for second player");
			Socket socket = serverSocket.accept();
			System.out.println("Second player connected");
			
			//communicate with client(second player)
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			
			
			
		} catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
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
		
		GameServer server = new GameServer(ip, portNum);
		//server.start();
	}
}
	
