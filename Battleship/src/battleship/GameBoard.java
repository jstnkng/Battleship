package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Displays the two boards, one with the players ships and one
 * for the user to fire on.
 */
public class GameBoard extends JFrame implements MouseListener {

	/**
	 *ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The board where the current players ships
	 * are displayed.
	 */
	private Grid player1Board;
	/**
	 * The board where the second players ships 
	 * are. The current player clicks on buttons
	 * on this board to fire.
	 */
	private Grid player2Board;
	/**
	 * The array of ints that store the locations of each
	 * point that contains player 1's ships.
	 */
	private int[][] player1Values = new int[10][10];
	/**
	 * Set the values in the 2-dimensional array
	 * of ints for player 1.
	 * @param values values from the grid
	 */
	public void setPlayer1Values(final int[][] values) {
		player1Values = values;
	}
	/**
	 * The array of ints that store the locations of each
	 * point that contains player 2's ships.
	 */
	private int[][] player2Values = new int[10][10];
	/**
	 * Set the values in the 2-dimensional array
	 * of ints for player 2.
	 * @param values values from the grid
	 */
	public void setPlayer2Values(final int[][] values) {
		player2Values = values;
	}
	/**
	 * Set ship pictures on gameboard.
	 */
	@SuppressWarnings("unused")
	//Only used for setting ship pictures on gameboard
	private Ship[] player1Ships = new Ship[5];
	/**
	 * Sets player1Ships to an array of ships.
	 * @param ships array of ship info
	 */
	public void setPlayer1Ships(final Ship[] ships) {
		player1Ships = ships;
	}
	/**
	 * set ship pictures on gameboard.
	 */
	@SuppressWarnings("unused")
	//only used for setting ship pictures on gameboard
	private Ship[] player2Ships = new Ship[5];
	/**
	 * Sets player2Ships to an array of ships.
	 * @param ships array of ship info
	 */
	public void setPlayer2Ships(final Ship[] ships) {
		player2Ships = ships;
	}
	/**
	 * Boolean to determine if it is player 1's turn.
	 * Starts true so player1 has first move.
	 */
	private boolean player1Turn = true;
	/**
	 * Boolean to determine if it is player 2's turn.
	 * Starts false so player2 has second move.
	 */
	private boolean player2Turn = false;
	/**
	 * ArrayList of points to hold where the cpu has already shot.
	 */
	private ArrayList<Point> cpuShots = new ArrayList<Point>();
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's aircraft carrier.
	 */
	private int cpuAircraftCarrierHits = 0;
	/**
	 * Returns cpuAircraftCarrierHits
	 */
	public int getCpuAircraftCarrierHits() {
		return cpuAircraftCarrierHits;
	}
	/**
	 * Sets cpuAircraftCarrierHits
	 */
	public void setCpuAircraftCarrierHits(int num) {
		cpuAircraftCarrierHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's battleship.
	 */
	private int cpuBattleShipHits = 0;
	/**
	 * Returns cpuBattleShiphits
	 */
	public int getCpuBattleShipHits() {
		return cpuBattleShipHits;
	}
	/**
	 * Sets cpuBattleShipHits
	 */
	public void setCpuBattleShipHits(int num) {
		cpuBattleShipHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's submarine.
	 */
	private int cpuSubmarineHits = 0;
	/**
	 * Returns cpuSubmarinehits
	 */
	public int getCpuSubmarineHits() {
		return cpuSubmarineHits;
	}
	/**
	 * Sets cpuSubmarineHits
	 */
	public void setCpuSubmarineHits(int num) {
		cpuSubmarineHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's cruiser.
	 */
	private int cpuCruiserHits = 0;
	/**
	 * Returns cpuCruiserHits
	 */
	public int getCpuCruiserHits() {
		return cpuCruiserHits;
	}
	/**
	 * Sets cpuCruiserHits
	 */
	public void setCpuCruiserHits(int num) {
		cpuCruiserHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's patrol boat.
	 */
	private int cpuPatrolBoatHits = 0;
	/**
	 * Returns cpuPatrolBoathits
	 */
	public int getCpuPatrolBoatHits() {
		return cpuPatrolBoatHits;
	}
	/**
	 * Sets cpuPatrolBoatHits
	 */
	public void setCpuPatrolBoatHits(int num) {
		cpuPatrolBoatHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's aircraft carrier.
	 */	
	private int aircraftCarrierHits = 0;
	/**
	 * Returns aircraftCarrierHits
	 */
	public int getAircraftCarrierHits(){
		return aircraftCarrierHits;
	}
	/**
	 * Sets aircraftCarrierHits
	 */
	public void setAircraftCarrierHits(int num){
		aircraftCarrierHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's battle ship.
	 */
	private int battleShipHits = 0;
	/**
	 * Returns battleShipHits
	 */
	public int getBattleShipHits(){
		return battleShipHits;
	}
	/**
	 * Sets battleShipHits
	 */
	public void setBattleShipHits(int num){
		battleShipHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's submarine.
	 */
	private int submarineHits = 0;
	/**
	 * Returns submarineHits
	 */
	public int getSubmarineHits(){
		return submarineHits;
	}
	/**
	 * Sets submarineHits
	 */
	public void setSubmarineHits(int num){
		submarineHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's cruiser.
	 */
	private int cruiserHits = 0;
	/**
	 * Returns cruiserHits
	 */
	public int getCruiserHits(){
		return cruiserHits;
	}
	/**
	 * Sets cruiserHits
	 */
	public void setCruiserHits(int num){
		cruiserHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's patrol boat.
	 */
	private int patrolBoatHits = 0;
	/**
	 * Returns patrolBoatHits
	 */
	public int getPatrolBoatHits(){
		return patrolBoatHits;
	}
	/**
	 * Sets patrolBoatHits
	 */
	public void setPatrolBoatHits(int num){
		patrolBoatHits = num;
	}
	/**
	 * Current gameMode being played.
	 */
	private GameMode currentMode;
	/**
	 * Sets the gameMode to the current gameMode.
	 * Sets the size and layout of the panel.
	 * @param mode current mode being played
	 */	
	public GameBoard(final GameMode mode) {
		currentMode = mode;
		this.setSize(1200, 700);
		this.setLayout(new GridLayout(0, 2));
	}
	/**
	 * Creates the side by side boards in the panel.
	 * Player 1's board on the left.
	 * Player 2's/cpu's board on the right.
	 */
	public void beginGame() {
		
		player1Board = new Grid(10, "Label");
		player1Board.setValues(player1Values);
		int x2 = 0;
		int y2 = 0;
		for (JLabel[] row  : Grid.getLabelGrid()) {
			for (JLabel box : row) {
				if (player1Values[y2][x2] == 0) {
				    Image img;
					try {
					img = ImageIO.read(
					new File("res\\waves.png"));
					box.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
					box.setForeground(Color.BLUE);
					box.setBackground(Color.BLUE);
				} else {
					box.setForeground(Color.GRAY);
					box.setBackground(Color.GRAY);
				}
				box.setText(player1Values[y2][x2] + "");
				
				x2++;
			}
			y2++;
			x2 = 0;
		}		
		this.add(player1Board);
		
		player2Board = new Grid(10, "Button");
		player2Board.setValues(player2Values);
		int x = 0;
		int y = 0;
		for (JButton[] row  : Grid.getButtonGrid()) {
			for (JButton button : row) {
				button.addMouseListener(this);
				button.setName(player2Values[y][x] + "");
				if (player2Values[y][x] == 1) {
					button.setForeground(Color.RED);
				} else {
					button.setForeground(Color.black);
				}
				x++;
			}
			y++;
			x = 0;
		}
		this.add(player2Board);
		
	}
	
	@Override
	public void mouseClicked(final MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		playerShot(button);
	}
	/**
	 * Called by mouseClicked when the player clicks on a JButton
	 * at a certain coordinate. Determines if spot has already been shot
	 * and sets image to hit or miss.
	 * @param button button that player clicks on
	 */
	public void playerShot(final JButton button) {
		try {
			Image hit = ImageIO.read(
					new File("res\\waves_reddot.png"));
			Image miss = ImageIO.read(
					new File("res\\waves_whitedot.png"));
			if (!button.getName().contains("shot")) {
				if (button.getName().contains("0")) {
					button.setIcon(new ImageIcon(miss));
					button.setName("shot");
				} else { 
					button.setIcon(new ImageIcon(hit));
					if (button.getName().contains("5")) {
						button.setName("shot5");
						aircraftCarrierHits++;
						if (aircraftCarrierHits == 5) {
						showCpuShip(
						 ShipType.AircraftCarrier);
//						JOptionPane.showMessageDialog(
//						null, "Aircraft Carrier Sunk");
						}
					} else if (button.getName()
							.contains("4")) {
						button.setName("shot4");
						battleShipHits++;
						if (battleShipHits == 4) {
						showCpuShip(
							ShipType.Battleship);
//						JOptionPane.showMessageDialog(
//						null, "BattleShip Sunk");
						}
					} else if (button.getName()
							.contains("2")) {
						button.setName("shot2");
						submarineHits++;
						if (submarineHits == 3) {
						showCpuShip(
							ShipType.Submarine);
//						JOptionPane.showMessageDialog(
//						null, "Submarine Sunk");
						}
					} else if (button.getName()
							.contains("3")) {
						button.setName("shot3");
						cruiserHits++;
						if (cruiserHits == 3) {
						showCpuShip(
							ShipType.Cruiser);
//						JOptionPane.showMessageDialog(
//							null, "Cruiser Sunk");
						}
					} else if (button.getName()
							.contains("1")) {
						button.setName("shot1");
						patrolBoatHits++;
						if (patrolBoatHits == 2) {
						showCpuShip(
							ShipType.PatrolBoat);
//						JOptionPane.showMessageDialog(
//						null, "Patrol Boat Sunk");
						}
					}
				}
			}  else {
				JOptionPane.showMessageDialog(null,
						"You already shot here");
				return;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (aircraftCarrierHits + battleShipHits + cruiserHits
				+ submarineHits + patrolBoatHits == 17) {
			JOptionPane.showMessageDialog(null, "You Win!");
			this.setVisible(false);
		}
		player2Turn = !player2Turn;
		player1Turn = !player1Turn;
		if (currentMode == GameMode.OnePlayerMode) {
			chooseShot();
		}
	}
	
	/**
	 * Shows player 2's sunk ships.
	 * @param type the type of ship that was sunk
	 */
	public void showCpuShip(final ShipType type) {
		
		Ship sunkShip = new Ship(type);
			for (JButton[] row  : Grid.getButtonGrid()) {
				for (JButton box : row) {
					if (box.getName().
						contains(sunkShip.getValue()
							+ "")) {
					   Image img;
					   try {
					   img = ImageIO.read(
					   new File("res\\ship_reddot.png"));
					   box.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
	}
		
	/**
	 * Executes the cpu's shot at the given coordinate.
	 * Then changes the image for a hit or miss.
	 * @param point the coordinates of the shot
	 */
	public void cpuFire(final Point point) {	
		
		int randomX = (int) point.getX();
		int randomY = (int) point.getY();
		JLabel randomBox = Grid.getLabel(randomX, randomY);
		if (randomBox
				.getText().contains("1")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuPatrolBoatHits++;
			if (cpuPatrolBoatHits == 2) {
//				JOptionPane.showMessageDialog(null,
//				"Your patrol boat has been sunk");
			}
		} else if (randomBox
				.getText().contains("2")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuSubmarineHits++;
			if (cpuSubmarineHits == 3) {
//			JOptionPane.showMessageDialog(null,
//					"Your submarine has been Sunk");
			}
		} else if (randomBox
				.getText().contains("3")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuCruiserHits++;
			if (cpuCruiserHits == 3) {
//				JOptionPane.showMessageDialog(
//				null, "Your cruiser has been sunk");
			}
		} else if (randomBox
				.getText().contains("4")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuBattleShipHits++;
			if (cpuBattleShipHits == 4) {
//				JOptionPane.showMessageDialog(null,
//					"Your battleship has been sunk");
			}
		} else if (randomBox
				.getText().contains("5")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuAircraftCarrierHits++;
			if (cpuAircraftCarrierHits == 5) {
//				JOptionPane.showMessageDialog(null,
//				"Your aircraft carrier has been sunk");
			}
		} else {
			Image miss;
			try {
				miss = ImageIO.read(
				new File("res\\waves_whitedot.png"));
				randomBox
						.setIcon(new ImageIcon(miss));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.WHITE);
			randomBox
					.setForeground(Color.WHITE);
		}
		
		if (cpuAircraftCarrierHits + cpuBattleShipHits 
				+ cpuCruiserHits + cpuSubmarineHits
				+ cpuPatrolBoatHits == 17) {
			JOptionPane.showMessageDialog(null, "Player 2 wins");
			
			this.setVisible(false);
		}
	}
	
	//Picks the point for the cpu to shoot
	//making sure it always picks a different spot
	/**
	 * Chooses a random coordinate on the board to
	 * have the cpu shoot. Verifies that the cpu
	 * cannot shoot in the same spot twice.
	 */
	public void chooseShot() {
		
		int minimum = 0;
		int maximum = 9;
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int x =  rn.nextInt(range) + minimum;
		int y = rn.nextInt(range) + minimum;
		
		Point point = new Point(x, y);
		
		if (cpuShots.contains(point)) {
			chooseShot();
		} else {
			cpuShots.add(point);
			cpuFire(point);
		}
	}


	@Override
	public void mouseEntered(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
