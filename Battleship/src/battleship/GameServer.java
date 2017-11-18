package battleship;
import java.io.*;
import java.net.*;
import java.util.*;

import battleship.Server.ClientThread;


public class GameServer extends Thread {
	
	private int port;
	private String server;
	private ShipSetupFrame ssf;
	private int[][] p1Values = new int[10][10];
	private int[][] p2Values = new int[10][10];
	private int[][] blankValues = new int[10][10];
	private boolean ready = false;
	private boolean isMyTurn = true;
	private OnlineGameBoard gb;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public GameServer(String server, int port) {
		this.port = port;
		this.server = server;
	}
	
	/*
	 * start
	 */
	public void run() {
		
		ssf = new ShipSetupFrame(GameMode.MultiplayerMode, 1);
		ssf.setLocationRelativeTo(null);
		
		System.out.println("Waiting for host to set ships");
		while(!ssf.getAreShipsSet()) {
			//System.out.println("sorry");
		}
		System.out.println("Host ships set");
		
		p1Values = ssf.getPlayer1Values();
		
		//try to create serverSocket
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for second player");
			Socket socket = serverSocket.accept();
			System.out.println("Second player connected");
			
			//communicate with client(second player)
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			//get p2 ships
			p2Values = (int[][]) in.readObject();
			
			//try to send ship values
			out.writeObject(p1Values);
			
			//create gameboard
//			(new Thread(new OnlineGameBoard(p1Values, p2Values))).start();
			
			gb = new OnlineGameBoard(p1Values, p2Values);
			gb.start();
			System.out.println("board is made");
			

		} catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("loop for game started");
		//play the game
		while(true) {
			
			//take my shot
			if(isMyTurn && gb.getShot() != null) {
				OnlineShot shot = gb.getShot();
				try {
					out.writeObject(shot);
					System.out.println("Shot Sent");
				} catch (IOException e) {
					e.printStackTrace();
				}
				isMyTurn = false;
			
			//get shot
			} else if(!isMyTurn) {
				try {
					OnlineShot shot = (OnlineShot) in.readObject();
					if(shot != null) {
						gb.append(shot);
						System.out.println("Shot Recieved");
						isMyTurn = true;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
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
		server.start();
	}
}


	
