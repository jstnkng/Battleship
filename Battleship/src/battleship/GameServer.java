package battleship;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Server for a multiplayer game.
 * 
 * @author Sam Carson
 *
 */
public class GameServer extends Thread {
	
	/**
	 * port.
	 */
	private int port;
	/**
	 * server.
	 */
	private String server;
	/**
	 * ship setup frame.
	 */
	private ShipSetupFrame ssf;
	/**
	 * p1 board vlaues.
	 */
	private int[][] p1Values = new int[10][10];
	/**
	 * p2 board values.
	 */
	private int[][] p2Values = new int[10][10];
	/**
	 * gameboard.
	 */
	private GameBoard gb;
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
	 * serversocket.
	 */
	private ServerSocket serverSocket;
	/**
	 * keep going or stop.
	 */
	private boolean keepGoing = true;
	
	/**
	 * constructor. 
	 * @param server ip address
	 * @param port number
	 */
	public GameServer(final String server, final int port) {
		this.port = port;
		this.server = server;
	}
	
	/**
	 * start.
	 */
	public void run() {
		
		
		//try to create serverSocket
		try {

			//create server socket
			serverSocket = new ServerSocket(port);
			
			//create wait frame
			JFrame frame = waitFrame("Waiting for another player to connect");
			frame.setVisible(true);
			
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to cancel your game?") == JOptionPane.YES_OPTION) {
			            	shutDown();
			        }
			    }
			});
			
			
			//wait for second player to connect
			System.out.println("Waiting for second player, port: " + port + " IP: " + server);
			socket = serverSocket.accept();
			System.out.println("Second player connected");
			
			//hide wait frame
			frame.setVisible(false);
			
			//create input and output streams
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			//create ship setup frame
			ssf = new ShipSetupFrame(GameMode.MultiplayerMode, 1);
			ssf.setLocationRelativeTo(null);

			System.out.println("Waiting for host to set ships");
			while (!ssf.getAreShipsSet()) {
				//System.out.println("sorry");
			}
			System.out.println("Host ships set");

			p1Values = ssf.getPlayer1Values();
			
			//create wait frame
			JFrame frame2 = waitFrame("Waiting for other player to set ships");
			frame2.setVisible(true);
			
			frame2.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to cancel your game?") == JOptionPane.YES_OPTION) {
			            	shutDown();
			        }
			    }
			});
			
			
			//get p2 ships
			p2Values = (int[][]) in.readObject();
			
			//hide wait frame
			frame2.setVisible(false);
			
			//try to send ship values
			out.writeObject(p1Values);
		
			//create gameboard
			gb = new GameBoard(p1Values, p2Values, this);
			gb.start();
			gb.setIsMyTurn(true);
			System.out.println("board is made");
			

		} catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
			shutDown();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shutDown();
		}
		
		System.out.println("loop for game started");
		//play the game
		while (!gb.getIsGameOver() && keepGoing) {
			
			//take my shot
			if (gb.getIsMyTurn()) {
				gb.setTitle("Your Turn");
				System.out.println("Waiting for host to shoot");
				while (gb.getShot() == null) {
					
				}
				System.out.println("host has made shot");
	
				OnlineShot shot = gb.getShot();
				try {
					out.writeObject(shot);
					System.out.println("Shot Sent");
				} catch (IOException e) {
					e.printStackTrace();
				}
				gb.clearShot();
				gb.setIsMyTurn(false);
			
			//get shot
			} else if (!gb.getIsMyTurn()) {
				
				gb.setTitle("Waiting for other player's shot");
				System.out.println("waiting for p2 to shoot");
				try {
					OnlineShot shot = (OnlineShot) in.readObject();
					if (shot != null) {
						gb.append(shot);
						System.out.println("Shot Recieved");
						gb.setIsMyTurn(true);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
			
		}
		
		//close everything when game is over
		shutDown();
		
	}
	
	/**
	 * close sockets and exit.
	 */
	public void shutDown() {
		
		//stop loop
		keepGoing = false;

		//close everything
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
        try {
			if (serverSocket != null) serverSocket.close();
		}
		catch (Exception e) { }
		// inform the GUI
		if (gb != null) {
			gb.connectionFailed();
		}
		
		System.exit(0);
	}
	
	/**
	 * frame for when host is waiting.
	 * 
	 * @param message to display
	 * @return JFrame frame to display wait message
	 */
	public JFrame waitFrame(final String message) {
		JFrame waitFrame = new JFrame();
		waitFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		waitFrame.setPreferredSize(new Dimension(500, 200));
		waitFrame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel label = new JLabel(message);
		c.anchor = GridBagConstraints.CENTER;
		panel.add(label, c);
		
		waitFrame.add(panel);
		waitFrame.pack();
		
		return waitFrame;
	}
	
	/**
	 * run gameserver.
	 * @param args Array of strings for main method
	 */
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
