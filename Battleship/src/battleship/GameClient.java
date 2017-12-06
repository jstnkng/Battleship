package battleship;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * client for a multiplayer game.
 * @author Sam Carson
 *
 */
public class GameClient extends Thread {
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
	 * p2 board values.
	 */
	private int[][] p2Values = new int[10][10];
	/**
	 * p1 board values.
	 */
	private int[][] p1Values = new int[10][10];
	/**
	 * socket.
	 */
	private Socket socket;
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
	 * keep going or stop.
	 */
	private boolean keepGoing = true;
	
	/**
	 * constructor.
	 * 
	 * @param server ip address
	 * @param port number
	 */
	public GameClient(final String server, final int port) {
		this.port = port;
		this.server = server;
		
	}
	
	/**
	 * start.
	 */
	public void run() {
		
		
		//try to connect to server
		System.out.println("Trying to connect to server, port: " + port + " IP: " + server);
		try {
			socket = new Socket(server, port);
			
			//create input and output streams
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			//if it can't connect exit
			System.exit(0);
		}
		
		//print to console that it is connected
		System.out.println("Connection accepted " 
				+ socket.getInetAddress() + ":" + socket.getPort());
		
		//create ship setup frame
		ssf = new ShipSetupFrame(GameMode.MultiplayerMode, 2);
		
		System.out.println("Waiting for p2 to set ships");
		while (!ssf.getAreShipsSet()) {
			//System.out.println("sorry");
		}
		System.out.println("p2 ships set");
		
		p2Values = ssf.getPlayer2Values();
		
		
		//try to send and receive ships
		try {
			//send server ship values
			out.writeObject(p2Values);
			
			
			//create wait frame
			JFrame frame = waitFrame("Waiting for other player to set ships");
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
			
			
			
			//try to get hosts ship values
			p1Values = (int[][]) in.readObject();
			
			//hide wait frame
			frame.setVisible(false);
			
			//create gameboard
			gb = new GameBoard(p2Values, p1Values, this);
			gb.start();
			gb.setIsMyTurn(false);
			
			
		} catch (IOException eIO) {
			eIO.printStackTrace();
			shutDown();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shutDown();
		}
		
		
		//play the game
		while (!gb.getIsGameOver() && keepGoing) {

			//take my shot
			if (gb.getIsMyTurn()) {
				gb.setTitle("Your turn");
				System.out.println("Waiting for p2 to shoot");
				while (gb.getShot() == null) {

				}
				System.out.println("p2 has made shot");

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
				System.out.println("waiting for host to shoot");
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
		catch(Exception e) { }
		try {
			if (out != null) out.close();
		}
		catch (Exception e) { }
        try {
			if (socket != null) socket.close();
		}
		catch (Exception e) { }
		// inform the GUI
		if (gb != null) {
			gb.connectionFailed();
		}
		
		System.exit(0);
	}
	
	/**
	 * frame when client is waiting.
	 * 
	 * @param message to display
	 * @return JFrame the frame to display wait message
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
	 * run gameclient.
	 * @param args Array of strings passed to main
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
		
		GameClient client = new GameClient(ip, portNum);
		//client.start();
	}
}	
