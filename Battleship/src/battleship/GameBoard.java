package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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
	private Grid leftBoard;
	/**
	 * The board where the second players ships 
	 * are. The current player clicks on buttons
	 * on this board to fire.
	 */
	private Grid rightBoard;
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
	 * Current number of hits that the cpu has
	 * hit player 1's aircraft carrier.
	 */
	private int cpuAircraftCarrierHits = 0;
	/**
	 * Returns cpuAircraftCarrierHits.
	 * @return cpuAircraftCarrierHits
	 */
	public int getCpuAircraftCarrierHits() {
		return cpuAircraftCarrierHits;
	}
	/**
	 * Sets cpuAircraftCarrierHits.
	 * @param num number of hits
	 */
	public void setCpuAircraftCarrierHits(final int num) {
		cpuAircraftCarrierHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's battleship.
	 */
	private int cpuBattleShipHits = 0;
	/**
	 * Returns cpuBattleShiphits.
	 * @return cpuBattleShipHits
	 */
	public int getCpuBattleShipHits() {
		return cpuBattleShipHits;
	}
	/**
	 * Sets cpuBattleShipHits.
	 * @param num number of hits
	 */
	public void setCpuBattleShipHits(final int num) {
		cpuBattleShipHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's submarine.
	 */
	private int cpuSubmarineHits = 0;
	/**
	 * Returns cpuSubmarinehits.
	 * @return cpuSubmarineHits
	 */
	public int getCpuSubmarineHits() {
		return cpuSubmarineHits;
	}
	/**
	 * Sets cpuSubmarineHits.
	 * @param num number of hits
	 */
	public void setCpuSubmarineHits(final int num) {
		cpuSubmarineHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's cruiser.
	 */
	private int cpuCruiserHits = 0;
	/**
	 * Returns cpuCruiserHits.
	 * @return cpuCruiserHits
	 */
	public int getCpuCruiserHits() {
		return cpuCruiserHits;
	}
	/**
	 * Sets cpuCruiserHits.
	 * @param num number of hits
	 */
	public void setCpuCruiserHits(final int num) {
		cpuCruiserHits = num;
	}
	/**
	 * Current number of hits that the cpu has
	 * hit player 1's patrol boat.
	 */
	private int cpuPatrolBoatHits = 0;
	/**
	 * Returns cpuPatrolBoathits.
	 * @return cpuPatrolBoatHits
	 */
	public int getCpuPatrolBoatHits() {
		return cpuPatrolBoatHits;
	}
	/**
	 * Sets cpuPatrolBoatHits.
	 * @param num number of hits
	 */
	public void setCpuPatrolBoatHits(final int num) {
		cpuPatrolBoatHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's aircraft carrier.
	 */	
	private int aircraftCarrierHits = 0;
	/**
	 * Returns aircraftCarrierHits.
	 * @return aircraftCarrierHits
	 */
	public int getAircraftCarrierHits() {
		return aircraftCarrierHits;
	}
	/**
	 * Sets aircraftCarrierHits.
	 * @param num number of hits
	 */
	public void setAircraftCarrierHits(final int num) {
		aircraftCarrierHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's battle ship.
	 */
	private int battleShipHits = 0;
	/**
	 * Returns battleShipHits.
	 * @return battleShipHits
	 */
	public int getBattleShipHits() {
		return battleShipHits;
	}
	/**
	 * Sets battleShipHits.
	 * @param num number of hits
	 */
	public void setBattleShipHits(final int num) {
		battleShipHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's submarine.
	 */
	private int submarineHits = 0;
	/**
	 * Returns submarineHits.
	 * @return submarineHits
	 */
	public int getSubmarineHits() {
		return submarineHits;
	}
	/**
	 * Sets submarineHits.
	 * @param num number of hits
	 */
	public void setSubmarineHits(final int num) {
		submarineHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's cruiser.
	 */
	private int cruiserHits = 0;
	/**
	 * Returns cruiserHits.
	 * @return cruiserHits
	 */
	public int getCruiserHits() {
		return cruiserHits;
	}
	/**
	 * Sets cruiserHits.
	 * @param num number of hits
	 */
	public void setCruiserHits(final int num) {
		cruiserHits = num;
	}
	/**
	 * Current number of hits that player 1
	 * has hit the cpu's patrol boat.
	 */
	private int patrolBoatHits = 0;
	/**
	 * Returns patrolBoatHits.
	 * @return patrolBoatHits
	 */
	public int getPatrolBoatHits() {
		return patrolBoatHits;
	}
	/**
	 * Sets patrolBoatHits.
	 * @param num number of hits
	 */
	public void setPatrolBoatHits(final int num) {
		patrolBoatHits = num;
	}
	/**
	 * Current gameMode being played.
	 */
	private GameMode currentMode;
	/**
	 * Current difficulty being played.
	 */
	private Difficulty diffChoice;
	/**
	 * Boolean if cpu is still trying to sink certain ship.
	 */
	private boolean inPursuit = false;
	/**
	 * ships sunk by cpu.
	 */
	private int shipsSunk = 0;
	/**
	 * location of last cpu hit.
	 */
	private Point lastHitLoc = null;
	/**
	 * location of first cpu hit on ship.
	 */
	private Point firstHitLoc = null;
	/**
	 * boolean if last cpu shot was a hit.
	 */
	private boolean wasHit = false;
	/**
	 * counts sunken ships by cpu.
	 */
	private int countSunk = 0;
	/**
	 * value of the square hit by the cpu
	 */
	private int hitValue = 0;
	/**
	 * boolean if the cpu sunk the ship it was targeting
	 */
	private boolean targetSunk = true;
	/**
	 * creates a cpu.
	 */
	private Cpu computer;
	/**
	 * Sets the gameMode to the current gameMode.
	 * Sets the size and layout of the panel.
	 * @param mode current mode being played
	 * @param difficulty current difficulty being played
	 */	
	public GameBoard(final GameMode mode, final Difficulty difficulty) {
		currentMode = mode;
		diffChoice = difficulty;
		computer = new Cpu(difficulty);
		this.setSize(1200, 700);
		this.setLayout(new GridLayout(0, 2));
	}
	/**
	 * Creates the side by side boards in the panel.
	 * Player 1's board on the left.
	 * Player 2's/cpu's board on the right.
	 */
	public void beginGame() {
		
		leftBoard = new Grid(10, "Label");
		rightBoard = new Grid(10, "Button");
		loadBoards(1);
		this.add(leftBoard);
		this.add(rightBoard);
	}
	
	public void loadBoards(final int currentPlayer) {
		int x2 = 0;
		int y2 = 0;
		if (currentPlayer == 1) {
			leftBoard.setValues(player1Values);
			for (JLabel[] row  : Grid.getLabelGrid()) {
				for (JLabel box : row) {
					if (player1Values[x2][y2] == 0) {
					    Image img;
					    //Probably need code here for putting in hits and misses too instead of just waves
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
					box.setText(player1Values[x2][y2] + "");
					
					y2++;
				}
				x2++;
				y2 = 0;
			}
			rightBoard.setValues(player2Values);
			int x = 0;
			int y = 0;
			for (JButton[] row  : Grid.getButtonGrid()) {
				for (JButton button : row) {
					button.addMouseListener(this);
					button.setName(player2Values[x][y] + "");
					y++;
				}
				x++;
				y = 0;
			}		
		}
		else if (currentPlayer == 2){
			leftBoard.setValues(player2Values);
			for (JLabel[] row  : Grid.getLabelGrid()) {
				for (JLabel box : row) {
					if (player2Values[x2][y2] == 0) {
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
					box.setText(player2Values[x2][y2] + "");
					
					y2++;
				}
				x2++;
				y2 = 0;
			}
			rightBoard.setValues(player1Values);
			int x = 0;
			int y = 0;
			for (JButton[] row  : Grid.getButtonGrid()) {
				for (JButton button : row) {
					button.addMouseListener(this);
					button.setName(player1Values[x][y] + "");
					y++;
				}
				x++;
				y = 0;
			}
		}
	}
	@Override
	public void mousePressed(final MouseEvent e) {
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
		
		//initiates and makes the computers shot
		if (currentMode == GameMode.TwoPlayerPassAndPlay) {
			if (player2Turn) {
				loadBoards(2);
			}
			else loadBoards(1);
		}
		else if (currentMode == GameMode.MultiplayerMode) {
			
		}
		else {
			if (this.isVisible()) {
				Point nextPoint;
				if (diffChoice == Difficulty.Easy) {
					nextPoint = computer.fireEasy();
				}else if (diffChoice == Difficulty.Normal) {
					nextPoint = computer.fireNormal(
							firstHitLoc, lastHitLoc, wasHit, inPursuit);
				}else {
					System.out.println("Getting next point...");
					System.out.println("(Target: "+ hitValue + ", Target sunk: " + targetSunk + ")");
					nextPoint = computer.fireHard(hitValue, player1Values, targetSunk);
					System.out.println("Next point complete. CPU Fire at " + nextPoint.y + ", " + nextPoint.x);
				}
				cpuFire(nextPoint);
			}
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
		
		int randomX = point.x;
		int randomY = point.y;
		//TODO fix weird bug x and y flipped for some reason
		JLabel randomBox = Grid.getLabel(randomY, randomX);
		if (randomBox
				.getText().contains("1")) {
			hitValue = 1;
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
			cpuPatrolBoatHits++;
			if (cpuPatrolBoatHits == 2) {
				targetSunk = true;
				shipsSunk++;
			} else if (cpuPatrolBoatHits == 1) {
				targetSunk = false;
				firstHitLoc = new Point(randomX, randomY);
				inPursuit = true;
				lastHitLoc = new Point(randomX, randomY);
			} else {
				targetSunk = false;
				lastHitLoc = new Point(randomX, randomY);
				wasHit = true;
			}
		} else if (randomBox
				.getText().contains("2")) {
			hitValue = 2;
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
			cpuSubmarineHits++;
			if (cpuSubmarineHits == 3) {
				shipsSunk++;
				targetSunk = true;
			} else if (cpuSubmarineHits == 1) {
				targetSunk = false;
				firstHitLoc = new Point(randomX, randomY);
				inPursuit = true;
				lastHitLoc = new Point(randomX, randomY);
			} else {
				targetSunk = false;
				lastHitLoc = new Point(randomX, randomY);
				wasHit = true;
			}
		} else if (randomBox
				.getText().contains("3")) {
			hitValue = 3;
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
			cpuCruiserHits++;
			if (cpuCruiserHits == 3) {
				shipsSunk++;
				targetSunk = true;
			} else if (cpuCruiserHits == 1) {
				targetSunk = false;
				firstHitLoc = new Point(randomX, randomY);
				inPursuit = true;
				lastHitLoc = new Point(randomX, randomY);
			} else {
				targetSunk = false;
				lastHitLoc = new Point(randomX, randomY);
				wasHit = true;
			}
		} else if (randomBox
				.getText().contains("4")) {
			hitValue = 4;
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
			cpuBattleShipHits++;
			if (cpuBattleShipHits == 4) {
				shipsSunk++;
				targetSunk = true;
			} else if (cpuBattleShipHits == 1) {
				targetSunk = false;
				firstHitLoc = new Point(randomX, randomY);
				inPursuit = true;
				lastHitLoc = new Point(randomX, randomY);
			} else {
				targetSunk = false;
				lastHitLoc = new Point(randomX, randomY);
				wasHit = true;
			}
		} else if (randomBox
				.getText().contains("5")) {
			hitValue = 5;
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
			cpuAircraftCarrierHits++;
			if (cpuAircraftCarrierHits == 5) {
				shipsSunk++;
				targetSunk = true;
			} else if (cpuAircraftCarrierHits == 1) {
				targetSunk = false;
				firstHitLoc = new Point(randomX, randomY);
				inPursuit = true;
				lastHitLoc = new Point(randomX, randomY);
			} else {
				targetSunk = false;
				lastHitLoc = new Point(randomX, randomY);
				wasHit = true;
			}
		} else {
			Image miss;
			hitValue = 0;
			try {
				miss = ImageIO.read(
				new File("res\\waves_whitedot.png"));
				randomBox
						.setIcon(new ImageIcon(miss));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wasHit = false;
		}
		
		//check if another ship was sunk
		if (shipsSunk > countSunk) {
			inPursuit = false;
			countSunk = shipsSunk;
		}
		
		//check win condition
		if (countSunk == 5) {
			JOptionPane.showMessageDialog(null, "Player 2 wins");
			
			this.setVisible(false);
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
	public void mouseClicked(final MouseEvent e) {
		// TODO Auto-generated method stub
//		JButton button = (JButton) e.getSource();
//		playerShot(button);
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
