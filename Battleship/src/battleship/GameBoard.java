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
import java.awt.event.WindowEvent;
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
	
	
	private volatile boolean isMyTurn;
	public void setIsMyTurn(boolean turn) {
		isMyTurn = turn;
	}
	public boolean getIsMyTurn() {
		return isMyTurn;
	}
	
	private volatile OnlineShot shotMade;
	
	private volatile boolean isGameOver = false;
	
	public boolean getIsGameOver() {
		return isGameOver;
	}
	
	private GameServer gsClient;
	private GameClient gcClient;
	
	
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
		if (currentMode == GameMode.OnePlayerMode) {
			this.setLayout(new GridLayout(0, 2));
		}
	}
	
	/**
	 * constructor for multiplayer
	 * @param p1Values array of values for player 1
	 * @param p2Values array of values for player 2
	 * @param c Object passed to create GameBoard on
	 */
	public GameBoard(int[][] p1Values, int[][] p2Values, Object c) {
		
		setPlayer1Values(p1Values);
		setPlayer2Values(p2Values);
		currentMode = GameMode.MultiplayerMode;
		
		if (c instanceof GameServer) {
			gsClient = (GameServer) c;
		} else if (c instanceof GameClient) {
			gcClient = (GameClient) c;
		}
		
		
		
		this.setSize(1200, 700);
		this.setLayout(new GridLayout(0, 2));
		
	}
	
	public void start() {
		beginGame();
	}
	
	
	public void beginPassAndPlay() {
		this.setLayout(new GridBagLayout());
		this.setTitle("Player 1");
		GridBagConstraints c = new GridBagConstraints();
		
		//create the panels
		player1Panel = new JPanel();
		player2Panel = new JPanel();
		middlePanel = new JPanel();
		
		
		//set panel Layouts
		player1Panel.setLayout(new GridLayout(0,2));
		player2Panel.setLayout(new GridLayout(0,2));
		//middlePanel.setLayout(new GridLayout(0,2));
		middlePanel.setLayout(new GridLayout(0,2));

		//create the grids
		player1ButtonGrid = new ButtonGrid();
		player2LabelGrid = new LabelGrid();
		player1LabelGrid = new LabelGrid();
		player2ButtonGrid = new ButtonGrid();
		currentGrid = new LabelGrid();
		
		//create switch button
		switchPlayers = new JButton("<html><center>"+"Next Player"+"<br>"+"Click Here To Continue"+"</center></html>");
		switchPlayers.setFont(new Font("Arial", Font.PLAIN, 50));
		switchPlayers.setBackground(Color.LIGHT_GRAY);
		switchPlayers.setForeground(Color.BLACK);
		switchPlayers.setPreferredSize(new Dimension(400, 50));

		switchPlayers.setBorder(BorderFactory.createLineBorder(
				Color.BLACK));

		//Changes button border on mouse hover
		switchPlayers.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e) {
				if (switchPlayers.getModel().isRollover()) {
					switchPlayers.setBorder(
							BorderFactory.
							createEtchedBorder());
				} else {
					switchPlayers.setBorder(
							BorderFactory.
							createLineBorder(Color.BLACK));
				}
			}
		});
				
		
		//set panel visibility for start
		player2Panel.setVisible(false);
		middlePanel.setVisible(false);
		
		//add button to the middlePanel
		middlePanel.add(switchPlayers);
		
		//add grid to the middlePanel
		middlePanel.add(currentGrid);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		this.add(middlePanel, c);
		
		//add the grids to the panels
		player1Panel.add(player1LabelGrid);
		player1Panel.add(player2ButtonGrid);
		player2Panel.add(player2LabelGrid);
		player2Panel.add(player1ButtonGrid);
		
		//set constraints and add to the frame
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		this.add(player1Panel,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		this.add(player2Panel, c);
		
		//set Mouse listener
		switchPlayers.addMouseListener(this);
		
		player1LabelGrid.loadPictures(player1Values, true);
		player2ButtonGrid.loadPictures(player2Values, this);
		player2LabelGrid.loadPictures(player2Values, true);
		player1ButtonGrid.loadPictures(player1Values, this);
		this.setVisible(true);
		
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
					
					y2++;
				}
				System.out.println("");
				x2++;
				y2 = 0;
			}
			System.out.println("");
			System.out.println("Player 2 values");
			rightBoard.setValues(player2Values);
			int x = 0;
			int y = 0;
			for (JButton[] row  : rightBoard.getButtonGrid()) {
				for (JButton button : row) {
					System.out.print(player2Values[x][y]);
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
	
	/*
	 * add shot from other player to board
	 */
	public void append(OnlineShot shot) {
		cpuFire(shot.getShotLoc());
		
		System.out.println(shot.getShotLoc());
	}
	
	/*
	 * when connection fails
	 */
	public void connectionFailed() {
		System.exit(0);
	}
	
	
	
	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == switchPlayers) {
			switchPlayers();
		} else if (currentMode == GameMode.MultiplayerMode && isMyTurn){
			JButton button = (JButton) e.getSource();
			playerShot(button);
		} else if (currentMode != GameMode.MultiplayerMode){
			JButton button = (JButton) e.getSource();
			playerShot(button);
		}
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
		
		//multiplayer only
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
					p = new Point(x, y);
					if (this.getTitle().contains("2")) {
						player1Values[x][y] = 70;	
					}
					else player2Values[x][y] = 70;
				}  else	if (button.getName().contains("5")) {
					String location = button.getText();
					int x = Integer.parseInt(location.split(",")[0]);
					int y = Integer.parseInt(location.split(",")[1]);
					p = new Point(x, y);
					button.setIcon(new ImageIcon(hit));
					button.setName("shot5");
					if (this.getTitle().contains("2")) {
						player1Values[x][y] = 65;	
					}
					else player2Values[x][y] = 65; 		
					if (currentMode == GameMode.OnePlayerMode || currentMode == GameMode.MultiplayerMode) {
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
								showShipMiddle(65);
								
							}
						}
						else if (this.getTitle().contains("2")) {
							player2ButtonGrid.hit(ShipType.AircraftCarrier);
							int hits = player2ButtonGrid.getAircraftCarrierHits();
							if (hits == 5) {
								showCpuShipPlayer2(ShipType.AircraftCarrier);
								showShipMiddle(65);
							}
						}
					}
				}else if (button.getName()
					.contains("4")) {
					
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				p = new Point(x, y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 64;	
				}
				else player2Values[x][y] = 64;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot4");
				if (currentMode == GameMode.OnePlayerMode || currentMode == GameMode.MultiplayerMode) {
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
							showShipMiddle(64);
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Battleship);
						int hits = player2ButtonGrid.getBattleShipHits();
						if (hits == 4) {
							showCpuShipPlayer2(ShipType.Battleship);
							showShipMiddle(64);
						}
					}
				}
			} else if (button.getName()
					.contains("2")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				p = new Point(x, y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 62;	
				}
				else player2Values[x][y] = 62;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot2");
				if (currentMode == GameMode.OnePlayerMode || currentMode == GameMode.MultiplayerMode) {
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
							showShipMiddle(62);
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Submarine);
						int hits = player2ButtonGrid.getSubmarineHits();
						if (hits == 3) {
							showCpuShipPlayer2(ShipType.Submarine);
							showShipMiddle(62);
						}
					}
				}
			} else if (button.getName()
					.contains("3")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				p = new Point(x, y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 63;	
				}
				else player2Values[x][y] = 63;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot3");
				if (currentMode == GameMode.OnePlayerMode || currentMode == GameMode.MultiplayerMode) {
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
							showShipMiddle(63);
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.Cruiser);
						int hits = player2ButtonGrid.getCruiserHits();
						if (hits == 3) {
							showCpuShipPlayer2(ShipType.Cruiser);
							showShipMiddle(63);
						}
					}
				}
			} else if (button.getName()
					.contains("1")) {
				String location = button.getText();
				int x = Integer.parseInt(location.split(",")[0]);
				int y = Integer.parseInt(location.split(",")[1]);
				p = new Point(x, y);
				if (this.getTitle().contains("2")) {
					player1Values[x][y] = 61;	
				}
				else player2Values[x][y] = 61;
				button.setIcon(new ImageIcon(hit));
				button.setName("shot1");
				if (currentMode == GameMode.OnePlayerMode || currentMode == GameMode.MultiplayerMode) {
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
							showShipMiddle(61);
						}
					}
					else if (this.getTitle().contains("2")) {
						player2ButtonGrid.hit(ShipType.PatrolBoat);
						int hits = player2ButtonGrid.getPatrolBoatHits();
						if (hits == 2) {
							showCpuShipPlayer2(ShipType.PatrolBoat);
							showShipMiddle(61);
						}
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
			isGameOver = true;
			this.setVisible(false);
		}

		player2Turn = !player2Turn;
		player1Turn = !player1Turn;
		//initiates and makes the computers shot
		if (currentMode == GameMode.TwoPlayerPassAndPlay) {
			
			//check to see who wins
			if (player1ButtonGrid.getAircraftCarrierHits() + player1ButtonGrid.getBattleShipHits()
				+ player1ButtonGrid.getCruiserHits() + player1ButtonGrid.getSubmarineHits()
				+ player1ButtonGrid.getPatrolBoatHits() == 17) {
					JOptionPane.showMessageDialog(null, "Player 1 Wins");
					this.setVisible(false);
			}
			
			if (player2ButtonGrid.getAircraftCarrierHits() + player2ButtonGrid.getBattleShipHits()
				+ player2ButtonGrid.getCruiserHits() + player2ButtonGrid.getSubmarineHits()
				+ player2ButtonGrid.getPatrolBoatHits() == 17) {
					JOptionPane.showMessageDialog(null, "Player 2 Wins");
					this.setVisible(false);
		}
			
			//show middlePanel and update panel visibility
			if (player1Panel.isVisible()) {
				this.setTitle("Switch Players");
				player1Panel.setVisible(false);
				currentGrid.loadPictures(player2Values, false);
				middlePanel.setVisible(true);
				//player2LabelGrid.loadPictures(player2Values);
			} else if (player2Panel.isVisible()) {
				this.setTitle("Switch Players");
				player2Panel.setVisible(false);
				currentGrid.loadPictures(player1Values, false);
				middlePanel.setVisible(true);
			}
			
		}
		else if (currentMode == GameMode.MultiplayerMode) {
			
			//set shot for server or client to send
			if (p != null) {
				OnlineShot shot = new OnlineShot(p, false);
				setShot(shot);
				
			}
			
			
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
		//System.out.println("User Point " + lastShot.x + "," + lastShot.y);
	}
	
	
	public void setShot(OnlineShot shot) {
		shotMade = shot;
	}
	
	public OnlineShot getShot() {
		return shotMade;
	}
	
	public void clearShot() {
		shotMade = null;
		isMyTurn = false;
	}
	
	
	public void showShipMiddle(final int ship ) {
		
		if(this.getTitle().contains("1")) {
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					if (player2Values[x][y] == ship) {
						player2Values[x][y] = 80;
					}
				}
			}
		} else if (this.getTitle().contains("2")) {
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					if (player1Values[x][y] == ship) {
						player1Values[x][y] = 80;
					}
				}
			}
		}
		
		
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
			isGameOver = true;
			
			this.setVisible(false);
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING && currentMode == GameMode.MultiplayerMode) {
			
			if(gsClient != null) {
				gsClient.shutDown();
			}else if(gcClient != null) {
				gcClient.shutDown();
			}
				
			super.processWindowEvent(e);
		} else {
			super.processWindowEvent(e);
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
