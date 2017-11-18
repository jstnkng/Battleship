package battleship;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Displays the two boards, one with the players ships and one
 * for the user to fire on.
 */
public class OnlineGameBoard extends JFrame implements MouseListener {

	/**
	 *ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The board where the current players ships
	 * are displayed.
	 */
	private Grid leftBoard;
	public Grid getLeftBoard() {
		return leftBoard;
	}
	
	
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
	
	public int[][] getPlayer1Values(){
		return player1Values;
	}
	
	public void setPlayer1Value(Point shot) {
		cpuFire(shot);
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
	
	private JPanel player1Panel;
	
	private JPanel player2Panel;
	
	private JPanel middlePanel;
	
	private ButtonGrid player1ButtonGrid;
	private LabelGrid player1LabelGrid;
	private ButtonGrid player2ButtonGrid;
	private LabelGrid player2LabelGrid;
	private LabelGrid currentGrid;
	
	private JButton switchPlayers;
	
	private String server;
	private int port;
	
	private GameClient client;
	
	private OnlineShot shotMade;
	
	
	/**
	 * Sets the gameMode to the current gameMode.
	 * Sets the size and layout of the panel.
	 * @param mode current mode being played
	 * @param difficulty current difficulty being played
	 */	
	public OnlineGameBoard(final GameMode mode, final Difficulty difficulty) {
		currentMode = mode;
		diffChoice = difficulty;
		computer = new Cpu(difficulty);
		this.setSize(1200, 700);
		if (currentMode == GameMode.OnePlayerMode) {
			this.setLayout(new GridLayout(0, 2));
		}
	}
	
	/**
	 * constructor for multiplayer
	 */
	public OnlineGameBoard(int[][] p1Values, int[][] p2Values) {
		
		setPlayer1Values(p1Values);
		setPlayer2Values(p2Values);
		
		this.setSize(1200, 700);
		this.setLayout(new GridLayout(0, 2));
		
	}
	
	public void start() {
		// TODO Auto-generated method stub
		beginGame();
	}
	
	/*
	 * add shot from other player to board
	 */
	public void append(OnlineShot shot) {
		cpuFire(shot.getShotLoc());
	}
	
//	public void start() {
//		client = new GameClient(server, port, this);
//		
//		// test if we can start the Client
//		if(!client.start()) 
//			return;
//	}
	
	/*
	 * when connection fails
	 */
	public void connectionFailed() {
		
	}
	
	/**
	 * Creates the side by side boards in the panel.
	 * Player 1's board on the left.
	 * Player 2's/cpu's board on the right.
	 */
	public void beginGame() {		
		leftBoard = new Grid(10, "Label");
		rightBoard = new Grid(10, "Button");
		loadBoards();
		this.add(leftBoard);		
		this.add(rightBoard);
		this.setVisible(true);
	}
	
	public void loadBoards() {
		System.out.println(this.getTitle());
		System.out.println("Player 1 values");
		int x2 = 0;
		int y2 = 0;
			leftBoard.setValues(player1Values);
			for (JLabel[] row  : leftBoard.getLabelGrid()) {
				for (JLabel box : row) {
					System.out.print(player1Values[x2][y2] +",");
					if (player1Values[x2][y2] == 0) {
					    Image img;
						try {
						img = ImageIO.read(
						new File("res/waves.png"));
						box.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (player1Values[x2][y2] == 70) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res/waves_whitedot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					} else if (player1Values[x2][y2] > 60) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res/ship_reddot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
					else {
						box.setForeground(Color.GRAY);
						box.setBackground(Color.GRAY);
					}
					box.setText(player1Values[x2][y2] + "");
					
					x2++;
				}
				System.out.println("");
				y2++;
				x2 = 0;
			}
			System.out.println("");
			System.out.println("Player 2 values");
			rightBoard.setValues(player2Values);
			int x = 0;
			int y = 0;
			for (JButton[] row  : rightBoard.getButtonGrid()) {
				for (JButton button : row) {
					System.out.print(player2Values[x][y] +",");
					button.addMouseListener(this);
					button.setName(player2Values[x][y] + "");
					button.setText(x + "," + y);
					y++;
				}
				System.out.println("");
				x++;
				y = 0;
			}
	}
	
	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub

		JButton button = (JButton) e.getSource();
		playerShot(button);
	}
	
	public void switchPlayers() {
		if (!player1Turn) {
			this.setTitle("Player 2");
			middlePanel.setVisible(false);
			player2Panel.setVisible(true);
			//p2.setVisible(false);
			//p1.setVisible(true);
			player2LabelGrid.loadPictures(player2Values, true);
		} else {
			this.setTitle("Player 1");
			middlePanel.setVisible(false);
			player1Panel.setVisible(true);
			//p2.setVisible(false);
			//p1.setVisible(true);
			player1LabelGrid.loadPictures(player1Values, true);
		}
	}
	
	/**
	 * Called by mouseClicked when the player clicks on a JButton
	 * at a certain coordinate. Determines if spot has already been shot
	 * and sets image to hit or miss.
	 * @param button button that player clicks on
	 */
	public void playerShot(final JButton button) {
		
		Point p = null;
		
		try {
			System.out.println("Button shot name: " + button.getName());
			Image hit = ImageIO.read(
					new File("res/waves_reddot.png"));
			Image miss = ImageIO.read(
					new File("res/waves_whitedot.png"));
			if (!button.getName().contains("shot")) {
				if (button.getName().contains("0")) {
					button.setIcon(new ImageIcon(miss));
					button.setName("shot");
					String location = button.getText();
					int x = Integer.parseInt(location.split(",")[0]);
					int y = Integer.parseInt(location.split(",")[1]);
					 p = new Point(x,y);
					if (this.getTitle().contains("2")) {
						player1Values[x][y] = 70;	
					}
					else player2Values[x][y] = 70;
				}  else	if (button.getName().contains("5")) {
					String location = button.getText();
					int x = Integer.parseInt(location.split(",")[0]);
					int y = Integer.parseInt(location.split(",")[1]);
					 p = new Point(x,y);
					button.setIcon(new ImageIcon(hit));
					button.setName("shot5");
					if (this.getTitle().contains("2")) {
						player1Values[x][y] = 65;	
					}
					else player2Values[x][y] = 65; 		
					if (currentMode == GameMode.OnePlayerMode) {
						aircraftCarrierHits++;
						if (aircraftCarrierHits == 5) {
							showCpuShip(
									ShipType.AircraftCarrier);
						}
						
					}
					else {
						if (this.getTitle().contains("1")) {
							player1ButtonGrid.hit(ShipType.AircraftCarrier);
							int hits = player1ButtonGrid.getAircraftCarrierHits();
							if (hits == 5) {
								showCpuShipPlayer1(ShipType.AircraftCarrier);
								
								
							}
						}
						else if (this.getTitle().contains("2")) {
							player2ButtonGrid.hit(ShipType.AircraftCarrier);
							int hits = player2ButtonGrid.getAircraftCarrierHits();
							if (hits == 5) {
								showCpuShipPlayer2(ShipType.AircraftCarrier);
								
							}
						}
					}
				}else if (button.getName()
					.contains("4")) {
					
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				 p = new Point(x,y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 64;	
				}
				else player2Values[x][y] = 64;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot4");
				if (currentMode == GameMode.OnePlayerMode) {
					battleShipHits++;
					if (battleShipHits == 4) {
						showCpuShip(
								ShipType.Battleship);
					}
				}
				else {
					if (this.getTitle().contains("1")) {
						player1ButtonGrid.hit(ShipType.Battleship);
						int hits = player1ButtonGrid.getBattleShipHits();
						if (hits == 4) {
							showCpuShipPlayer1(ShipType.Battleship);
							
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Battleship);
						int hits = player2ButtonGrid.getBattleShipHits();
						if (hits == 4) {
							showCpuShipPlayer2(ShipType.Battleship);
							
						}
					}
				}
			} else if (button.getName()
					.contains("2")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				 p = new Point(x,y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 62;	
				}
				else player2Values[x][y] = 62;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot2");
				if (currentMode == GameMode.OnePlayerMode) {
					submarineHits++;
					if (submarineHits == 3) {
						showCpuShip(
								ShipType.Submarine);
					}
				}
				else {
					if (this.getTitle().contains("1")) {
						player1ButtonGrid.hit(ShipType.Submarine);
						int hits = player1ButtonGrid.getSubmarineHits();
						if (hits == 3) {
							showCpuShipPlayer1(ShipType.Submarine);
							
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Submarine);
						int hits = player2ButtonGrid.getSubmarineHits();
						if (hits == 3) {
							showCpuShipPlayer2(ShipType.Submarine);
							
						}
					}
				}
			} else if (button.getName()
					.contains("3")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				 p = new Point(x,y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 63;	
				}
				else player2Values[x][y] = 63;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot3");
				if (currentMode == GameMode.OnePlayerMode) {
					cruiserHits++;
					if (cruiserHits == 3) {
						showCpuShip(
								ShipType.Cruiser);
					}
				}
				else {
					if (this.getTitle().contains("1")) {
						player1ButtonGrid.hit(ShipType.Cruiser);
						int hits = player1ButtonGrid.getCruiserHits();
						if (hits == 3) {
							showCpuShipPlayer1(ShipType.Cruiser);
							
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Cruiser);
						int hits = player2ButtonGrid.getCruiserHits();
						if (hits == 3) {
							showCpuShipPlayer2(ShipType.Cruiser);
							
						}
					}
				}
			} else if (button.getName()
					.contains("1")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				 p = new Point(x,y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 61;	
				}
				else player2Values[x][y] = 61;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot1");
				if (currentMode == GameMode.OnePlayerMode) {
					patrolBoatHits++;
					if (patrolBoatHits == 2) {
						showCpuShip(
								ShipType.PatrolBoat);
					}
				}
				else {
					if (this.getTitle().contains("1")) {
						player1ButtonGrid.hit(ShipType.PatrolBoat);
						int hits = player1ButtonGrid.getPatrolBoatHits();
						if (hits == 2) {
							showCpuShipPlayer1(ShipType.PatrolBoat);
							
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.PatrolBoat);
						int hits = player2ButtonGrid.getPatrolBoatHits();
						if (hits == 2) {
							showCpuShipPlayer2(ShipType.PatrolBoat);
							
						}
					}
				}

			}

			}  else {
				JOptionPane.showMessageDialog(null,
						"You already shot here");
				return;
			}
			
			//set shot for server or client to send
			if (p != null) {
				OnlineShot shot = new OnlineShot(p, false);
				setShot(shot);
				
			}
			
			

		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (aircraftCarrierHits + battleShipHits + cruiserHits
				+ submarineHits + patrolBoatHits == 17) {
			JOptionPane.showMessageDialog(null, "You Win!");
			this.setVisible(false);
		}

		
	}
	
	public void setShot(OnlineShot shot) {
		shotMade = shot;
	}
	
	public OnlineShot getShot() {
		return shotMade;
	}
	
	public void clearShot() {
		shotMade = null;
	}
	
	/**
	 * Shows player 2's sunk ships.
	 * @param type the type of ship that was sunk
	 */
	public void showCpuShip(final ShipType type) {
		
		Ship sunkShip = new Ship(type);
			for (JButton[] row  : rightBoard.getButtonGrid()) {
				for (JButton box : row) {
					if (box.getName().
						contains(sunkShip.getValue()
							+ "")) {
					   Image img;
					   try {
					   img = ImageIO.read(
					   new File("res/ship_reddot.png"));
					   box.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
	}
	
	public void showCpuShipPlayer1(final ShipType type) {
		Ship sunkShip = new Ship(type);
		for (JButton[] row  : player2ButtonGrid.getButtons()) {
			for (JButton box : row) {
				if (box.getName().
					contains(sunkShip.getValue()
						+ "")) {
				   Image img;
				   try {
				   img = ImageIO.read(
				   new File("res/ship_reddot.png"));
				   box.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void showCpuShipPlayer2(final ShipType type) {
		Ship sunkShip = new Ship(type);
		for (JButton[] row  : player1ButtonGrid.getButtons()) {
			for (JButton box : row) {
				if (box.getName().
					contains(sunkShip.getValue()
						+ "")) {
				   Image img;
				   try {
				   img = ImageIO.read(
				   new File("res/ship_reddot.png"));
				   box.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
		
	public void fire(final JLabel randomBox) {
		if (randomBox
				.getText().contains("1")) {
			hitValue = 1;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpuPatrolBoatHits++;
		
		} else if (randomBox
				.getText().contains("2")) {
			hitValue = 2;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpuSubmarineHits++;
		
		} else if (randomBox
				.getText().contains("3")) {
			hitValue = 3;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpuCruiserHits++;
			
		} else if (randomBox
				.getText().contains("4")) {
			hitValue = 4;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpuBattleShipHits++;
			
		} else if (randomBox
				.getText().contains("5")) {
			hitValue = 5;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpuAircraftCarrierHits++;
			
		} else {
			Image miss;
			hitValue = 0;
			try {
				miss = ImageIO.read(
				new File("res/waves_whitedot.png"));
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
			JOptionPane.showMessageDialog(null, "Computer wins");
			
			this.setVisible(false);
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
		
		System.out.println(this.getTitle());
		JLabel randomBox = this.leftBoard.getLabel(randomX, randomY);
		if (randomBox
				.getText().contains("1")) {
			hitValue = 1;
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res/ship_reddot.png"));
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
				new File("res/ship_reddot.png"));
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
				new File("res/ship_reddot.png"));
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
				new File("res/ship_reddot.png"));
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
				new File("res/ship_reddot.png"));
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
				new File("res/waves_whitedot.png"));
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

	/**
	 * run client
	 * @param args
	 */
	public static void main(String[] args) {
		//new OnlineGameBoard("localhost", 5445, 1);
	}

	
}
