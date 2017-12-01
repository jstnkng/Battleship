package battleship;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

/**
 * Creates the frame with ships on the left and
 * the board to place ships on the right.
 */
public class ShipSetupFrame extends JFrame 
	implements MouseListener, MouseMotionListener, KeyListener {
	
	/**
	 * ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * X and Y coordinates of the mouse.
	 */
	private int mx, my;
	/**
	 * X and Y coordinates used to line up
	 * the ship with the mouse when dragged.
	 */
	private int dragFromX, dragFromY;
	/**
	 * Boolean to check if you can drag the
	 * aircraft carrier. Starts false because
	 * it is not clicked on yet.
	 */
	private boolean acCcanDrag = false;
	/**
	 * Boolean to check if you can drag the
	 * battle ship. Starts false because
	 * it is not clicked on yet.
	 */
	private boolean bsCanDrag = false;
	/**
	 * Boolean to check if you can drag the
	 * cruiser. Starts false because
	 * it is not clicked on yet.
	 */
	private boolean cCanDrag = false;
	/**
	 * Boolean to check if you can drag the
	 * submarine. Starts false because
	 * it is not clicked on yet.
	 */
	private boolean sCanDrag = false;
	/**
	 * Boolean to check if you can drag the
	 * patrol boat. Starts false because
	 * it is not clicked on yet.
	 */
	private boolean pbCanDrag = false;
	/**
	 * Sets board size to 10.
	 */
	private int boardSize = 10;
	/**
	 * Creates a layered pane.
	 */
	private JLayeredPane layeredPane;
	/**
	 * Creates a Grid for the board.
	 */
	private Grid board;
	/**
	 * Creates a Grid for the board.
	 */
	private Grid player2Board;
	/**
	 * Creates an instance of Ship for the aircraft carrier.
	 */
	private Ship aircraftCarrier;
	/**
	 * Returns the frame's aircraft carrier.
	 *@return Ship aircraftCarrier
	 */
	public Ship getAircraftCarrier() {
		return aircraftCarrier;
	}
	/**
	 * Creates an instance of Ship for the battle ship.
	 */
	private Ship battleShip;
	/**
	 * Returns the frame's battleship.
	 * @return Ship battleShip
	 */
	public Ship getBattleShip() {
		return battleShip;
	}
	/**
	 * Creates an instance of Ship for the cruiser.
	 */
	private Ship cruiser;
	/**
	 * Returns the frame's cruiser.
	 * @return Ship cruiser
	 */
	public Ship getCruiser() {
		return cruiser;
	}
	/**
	 * Creates an instance of Ship for the patrol boat.
	 */
	private Ship patrolBoat;
	/**
	 * Returns the frame's patrol boat.
	 * @return Ship patrolBoat
	 */
	public Ship getPatrolBoat() {
		return patrolBoat;
	}
	/**
	 * Creates an instance of Ship for the submarine.
	 */
	private Ship submarine;
	/**
	 * Returns the frame's submarine.
	 * @return Ship submarine
	 */
	public Ship getSubmarine() {
		return submarine;
	}
	/**
	 * Creates a button to submit ship placement.
	 */
	private JButton submit;
	/**
	 * Current gameMode being played.
	 */
	private GameMode mode;
	/**
	 * Current difficulty being played.
	 */
	private Difficulty diffChoice;
	/**
	 * Board for the values that player 1 sees
	 */
	private GameBoard player1GameBoard;
	/**
	 * Board for the values that player 1 sees
	 */
	private GameBoard player2GameBoard;

	/**
	 * Current player setting ships.
	 */
	private int currentPlayer;
	/**
	 * Creates a 2D array of labels for the board.
	 */
	private JLabel[][] grid = new JLabel[boardSize][boardSize];
	/**
	 * Creates a 2D array of labels for the player 2 board.
	 */
	private JLabel[][] player2Grid = new JLabel[boardSize][boardSize];
	/**
	 * Creates a 2D array of int to hold the values for player1's ships.
	 */
	private int[][] player1Values = new int[boardSize][boardSize];
	/**
	 * Returns player1Values.
	 * @return int[][] player1Values
	 */
	public int[][] getPlayer1Values() {
		return player1Values;
	}
	/**
	 * Creates a 2D array of int to hold the values for player2's ships.
	 */
	private int[][] player2Values = new int[boardSize][boardSize];
	/**
	 * Returns player2Values.
	 * @return int[][] player2Values
	 */
	public int[][] getPlayer2Values() {
		return player2Values;
	}
	/**
	 * Creates an array of Ship to hold player1's 5 ships.
	 */
	private Ship[] player1Ships = new Ship[5];
	/**
	 * Returns player1Ships.
	 * @return Ship[] player1Ships
	 */
	public Ship[] getPlayer1Ships() {
		return player1Ships;
	}
	/**
	 * Creates an array of Ship to hold player2's 5 ships.
	 */
	private Ship[] player2Ships = new Ship[5];
	/**
	 * Returns player2Ships.
	 * @return Ship[] player2Ships
	 */
	public Ship[] getPlayer2Ships() {
		return player2Ships;
	}
	/**
	 * Flag to check if ships are set correctly or not.
	 */
	private boolean invalidShipPlacement;
	/**
	 * Flag to check if player 2 ships are set correctly or not.
	 */
	private boolean invalidShipPlacement2;
	/**
	 * Returns invalidShipPlacement flag.
	 * @return boolean invalidShipPlacement
	 */
	public boolean getInvalidShipPlacement() {
		return invalidShipPlacement;
	}
	
	
	private volatile boolean areShipsSet = false;
	public boolean getAreShipsSet() {
		return areShipsSet;
	}
	
	
	
	/**
	 * Calls shipSetup passing gameMode and current player.
	 * @param currentMode mode that the game is being played in
	 * @param player either 1 or 2 to determine current player
	 * @param difficulty difficulty to be played
	 */
	public ShipSetupFrame(final GameMode currentMode, final int player,
			final Difficulty difficulty) {
		shipSetup(currentMode, player, difficulty);
	}
	
	//constructor for online multiplayer game
	public ShipSetupFrame(final GameMode currentMode, final int player) {
		// TODO Auto-generated constructor stub
		shipSetup(currentMode, player, null);
	}
	/**
	 * Creates the frame and populates it with the board,
	 * the ships, and the submit button.
	 * @param currentMode mode that the game is being played in
	 * @param player either 1 or 2 to determine current player
	 * @param difficulty difficulty to be played
	 */
	public void shipSetup(final GameMode currentMode, final int player,
			final Difficulty difficulty) {
		if (player == 2) {
			this.setTitle("Player 2 Set Ships");
		} else {
			this.setTitle("Player 1 Set Ships");
		}
		
		mode = currentMode;
		diffChoice = difficulty;
		currentPlayer = player;
		System.out.println("Player: " + currentPlayer);
		//JComponent contentPane = new ShipSetupPanel(currentMode);
		getContentPane().setLayout(
			    new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)
			);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(gridbag);
		//create Aircraft Carrier
		aircraftCarrier = new Ship(ShipType.AircraftCarrier);
		player1Ships[0] = aircraftCarrier;

		//add Aircraft Carrier ship to layered pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(aircraftCarrier, c);
		layeredPane.setLayer(aircraftCarrier, 1, 0);
		//create Battle Ship 
		battleShip = new Ship(ShipType.Battleship);
		player1Ships[1] = battleShip;

		//add Battle Ship to layered pane
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(battleShip, c);
		layeredPane.setLayer(battleShip, 1, 1);
		
		//create cruiser
		cruiser = new Ship(ShipType.Cruiser);
		player1Ships[2] = cruiser;

		//add cruiser to layered pane
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(cruiser, c);
		layeredPane.setLayer(cruiser, 1, 2);
		
		//create submarine
		submarine = new Ship(ShipType.Submarine);
		player1Ships[4] = submarine;

		//add submarine to layered pane
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(submarine, c);
		layeredPane.setLayer(submarine, 1, 3);
		
		//create patrol boat
		patrolBoat = new Ship(ShipType.PatrolBoat);
		player1Ships[3] = patrolBoat;

		//add patrol boat to layered pane
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(patrolBoat, c);
		layeredPane.setLayer(patrolBoat, 1, 1);
		
		
		submit = new JButton();
		submit.setText("Submit");
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(submit,  c, 1);
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				submit();
			}
		});

		
		board = new Grid(boardSize, "Label");
		player2Board = new Grid(boardSize, "Label");
		grid = board.getLabelGrid();
		grid = player2Board.getLabelGrid();
		player1Values = board.getValues();
		player2Values = player2Board.getValues();

//		for (JLabel[] row  : board.labelGrid) {
//			for (JLabel box : row) {
//				System.out.println(box.getSize().toString());
//			}
//		}		
		//add board to the layeredPane
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .6;
		c.weighty = 1;
		c.gridheight = 6;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(board, c);
		layeredPane.setLayer(board, -1);
		
//		contentPane.setOpaque(true);
//		this.setContentPane(contentPane);
		
		//Display;
		this.setSize(1200, 700);
		this.setResizable(false);
		//Set Ships to starting locations
		returnShipToStart(aircraftCarrier);
		returnShipToStart(battleShip);
		returnShipToStart(cruiser);
		returnShipToStart(submarine);
		returnShipToStart(patrolBoat);
		
		submit.addKeyListener(this);
		System.out.println("end of ship set up frame");
		
		//add layered pane to the JPanel
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		this.add(layeredPane);
		this.setVisible(true);	
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (acCcanDrag) {
			if (aircraftCarrier.isHorizontal()) {
				aircraftCarrier.setSize(
						aircraftCarrier.getWidthH(),
						aircraftCarrier.getHeightH());
			} else {
				aircraftCarrier.setSize(
						aircraftCarrier.getWidthV(),
						aircraftCarrier.getHeightV());
			}
			int aircraftX = mx - dragFromX;
			int aircraftY = my - dragFromY;
			aircraftCarrier.setLocation(
					aircraftX, aircraftY);
			
		} else if (bsCanDrag) {
			if (battleShip.isHorizontal()) {
				battleShip.setSize(battleShip.getWidthH(),
						battleShip.getHeightH());
			} else {
				battleShip.setSize(battleShip.getWidthV(),
						battleShip.getHeightV());
			}
			int battleShipX = mx - dragFromX;
			int battleShipY = my - dragFromY;
			battleShip.setLocation(battleShipX, battleShipY);
			
		} else if (cCanDrag) {
			if (cruiser.isHorizontal()) {
				cruiser.setSize(cruiser.getWidthH(),
						cruiser.getHeightH());
			} else {
				cruiser.setSize(cruiser.getWidthV(),
						cruiser.getHeightV());
			}
			int cruiserX = mx - dragFromX;
			int cruiserY = my - dragFromY;
			cruiser.setLocation(cruiserX, cruiserY);
			
		} else if (sCanDrag) {
			if (submarine.isHorizontal()) {
				submarine.setSize(submarine.getWidthH(),
						submarine.getHeightH());
			} else {
				submarine.setSize(submarine.getWidthV(),
						submarine.getHeightV());
			}
			int submarineX = mx - dragFromX;
			int submarineY = my - dragFromY;
			submarine.setLocation(submarineX, submarineY);
			
		} else if (pbCanDrag) {
			if (patrolBoat.isHorizontal()) {
				patrolBoat.setSize(patrolBoat.getWidthH(),
						patrolBoat.getHeightH());
			} else {
				patrolBoat.setSize(patrolBoat.getWidthV(),
						patrolBoat.getHeightV());
			}
			int patrolBoatX = mx - dragFromX;
			int patrolBoatY = my - dragFromY;
			patrolBoat.setLocation(patrolBoatX, patrolBoatY);
			
		}
		
		
		
	}

	@Override
	public void mouseMoved(final MouseEvent e) {

		
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		
//		int mx = e.getX();
//		int my = e.getY();
		//Aircraft Carrier
		int acX = aircraftCarrier.getX();
		int acY = aircraftCarrier.getY();	
		int acW = aircraftCarrier.getWidth();
		int acH = aircraftCarrier.getHeight();
		
		if (mx >= acX && mx <= (acX + acW) 
				&& my >= acY && my <= (acY + acH)) {
			aircraftCarrier.rotate();
			checkForShipOverLap(aircraftCarrier);
			checkForShipOffGrid(aircraftCarrier);
		}
		
		//BattleShip
		int bsX = battleShip.getX();
		int bsY = battleShip.getY();
		int bsW = battleShip.getWidth();
		int bsH = battleShip.getHeight();
		
		if (mx >= bsX && mx <= (bsX + bsW) 
				&& my >= bsY && my <= (bsY + bsH)) {
			battleShip.rotate();
			checkForShipOverLap(battleShip);
			checkForShipOffGrid(battleShip);
		}
		
		//Cruiser
		int cX = cruiser.getX();
		int cY = cruiser.getY();
		int cW = cruiser.getWidth();
		int cH = cruiser.getHeight();

		if (mx >= cX && mx <= (cX + cW) && my >= cY 
				&& my <= (cY + cH)) {

			cruiser.rotate();
			checkForShipOverLap(cruiser);
			checkForShipOffGrid(cruiser);
		}
		
		//Submarine
		int sX = submarine.getX();
		int sY = submarine.getY();
		int sW = submarine.getWidth();
		int sH = submarine.getHeight();

		if (mx >= sX && mx <= (sX + sW) && my >= sY 
				&& my <= (sY + sH)) {

			submarine.rotate();
			checkForShipOverLap(submarine);
			checkForShipOffGrid(submarine);
		}
		
		//PatrolBoat
		int pbX = patrolBoat.getX();
		int pbY = patrolBoat.getY();
		int pbW = patrolBoat.getWidth();
		int pbH = patrolBoat.getHeight();

		if (mx >= pbX && mx <= (pbX + pbW) 
				&& my >= pbY && my <= (pbY + pbH)) {
			patrolBoat.rotate();
			checkForShipOverLap(patrolBoat);
			checkForShipOffGrid(patrolBoat);
		}
				
				
		
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		//Aircraft Carrier
		int acX = aircraftCarrier.getX();
		int acY = aircraftCarrier.getY();
		int acW = aircraftCarrier.getWidth();
		int acH = aircraftCarrier.getHeight();
		
		if (mx >= acX && mx <= (acX + acW) 
				&& my >= acY && my <= (acY + acH)) {
			
			layeredPane.moveToFront(aircraftCarrier);
			acCcanDrag = true;
			dragFromX = mx - acX;
			dragFromY = my - acY;
		} else {
			acCcanDrag = false;
		}
		
		//BattleShip
		int bsX = battleShip.getX();
		int bsY = battleShip.getY();
		int bsW = battleShip.getWidth();
		int bsH = battleShip.getHeight();
		
		if (mx >= bsX && mx <= (bsX + bsW) 
				&& my >= bsY && my <= (bsY + bsH)) {
			
			layeredPane.moveToFront(battleShip);
			bsCanDrag = true;
			dragFromX = mx - bsX;
			dragFromY = my - bsY;
			
		} else {
			bsCanDrag = false;
		}
		
		//Cruiser
		int cX = cruiser.getX();
		int cY = cruiser.getY();
		int cW = cruiser.getWidth();
		int cH = cruiser.getHeight();

		if (mx >= cX && mx <= (cX + cW) && my >= cY 
				&& my <= (cY + cH)) {
			
			layeredPane.moveToFront(cruiser);
			cCanDrag = true;
			dragFromX = mx - cX;
			dragFromY = my - cY;

		} else {
			cCanDrag = false;
		}
		
		//Submarine
		int sX = submarine.getX();
		int sY = submarine.getY();
		int sW = submarine.getWidth();
		int sH = submarine.getHeight();

		if (mx >= sX && mx <= (sX + sW) && my >= sY 
				&& my <= (sY + sH)) {
			
			layeredPane.moveToFront(submarine);
			sCanDrag = true;
			dragFromX = mx - sX;
			dragFromY = my - sY;

		} else {
			sCanDrag = false;
		}
		
		//PatrolBoat
		int pbX = patrolBoat.getX();
		int pbY = patrolBoat.getY();
		int pbW = patrolBoat.getWidth();
		int pbH = patrolBoat.getHeight();

		if (mx >= pbX && mx <= (pbX + pbW) 
				&& my >= pbY && my <= (pbY + pbH)) {
			
			layeredPane.moveToFront(patrolBoat);
			pbCanDrag = true;
			dragFromX = mx - pbX;
			dragFromY = my - pbY;

		} else {
			pbCanDrag = false;
		}
		
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		//Aircraft Carrier
		int acX = aircraftCarrier.getX();
		int acY = aircraftCarrier.getY();
		int acW = aircraftCarrier.getWidth();
		int acH = aircraftCarrier.getHeight();
		
		if (mx >= acX && mx <= (acX + acW) 
				&& my >= acY && my <= (acY + acH)) {
			
			snapShipToY(aircraftCarrier);
			snapShipToX(aircraftCarrier);
		}
		
		//BattleShip
		int bsX = battleShip.getX();
		int bsY = battleShip.getY();
		int bsW = battleShip.getWidth();
		int bsH = battleShip.getHeight();
		
		if (mx >= bsX && mx <= (bsX + bsW) 
				&& my >= bsY && my <= (bsY + bsH)) {
			
			snapShipToY(battleShip);
			snapShipToX(battleShip);
		}
		
		//Cruiser
		int cX = cruiser.getX();
		int cY = cruiser.getY();
		int cW = cruiser.getWidth();
		int cH = cruiser.getHeight();

		if (mx >= cX && mx <= (cX + cW) && my >= cY 
				&& my <= (cY + cH)) {
			
			snapShipToY(cruiser);
			snapShipToX(cruiser);
		}
		
		//Submarine
		int sX = submarine.getX();
		int sY = submarine.getY();
		int sW = submarine.getWidth();
		int sH = submarine.getHeight();

		if (mx >= sX && mx <= (sX + sW) && my >= sY 
				&& my <= (sY + sH)) {
			
			snapShipToY(submarine);
			snapShipToX(submarine);
		}
		
		//PatrolBoat
		int pbX = patrolBoat.getX();
		int pbY = patrolBoat.getY();
		int pbW = patrolBoat.getWidth();
		int pbH = patrolBoat.getHeight();

		if (mx >= pbX && mx <= (pbX + pbW) 
				&& my >= pbY && my <= (pbY + pbH)) {
			
			snapShipToY(patrolBoat);
			snapShipToX(patrolBoat);
		}
	}
	/**
	 * Moves the ship into the nearest column to avoid
	 * overlapping columns.
	 * @param shipToSnap the selected ship
	 */
	public void snapShipToX(final Ship shipToSnap) {
		int shipX;
		int shipY = shipToSnap.getY();
		if (shipToSnap.getX() >= 476 && shipToSnap.getX() <= 549) {
			shipX = 516;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(),
					shipToSnap.getHeight());
			shipToSnap.setColumn(0);
		} else if (shipToSnap.getX() > 549 
				&& shipToSnap.getX() <= 615) {
			shipX = 583; 
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(1);
		} else if (shipToSnap.getX() > 615 
				&& shipToSnap.getX() <= 681) {
			shipX = 650; 
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(2);
		} else if (shipToSnap.getX() > 681 
				&& shipToSnap.getX() <= 747) {
			shipX = 717;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(3);
		} else if (shipToSnap.getX() > 747 
				&& shipToSnap.getX() <= 813) {
			shipX = 784; 
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(4);
		} else if (shipToSnap.getX() > 813 
				&& shipToSnap.getX() <= 879) {
			shipX = 851; 
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(),
					shipToSnap.getHeight());
			shipToSnap.setColumn(5);
		} else if (shipToSnap.getX() > 879 
				&& shipToSnap.getX() <= 945) {
			shipX = 918;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(),
					shipToSnap.getHeight());
			shipToSnap.setColumn(6);
		} else if (shipToSnap.getX() > 945 
				&& shipToSnap.getX() <= 1011) {
			shipX = 985;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(7);
		} else if (shipToSnap.getX() > 1011 
				&& shipToSnap.getX() <= 1077) {
			shipX = 1052;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(8);
		} else if (shipToSnap.getX() > 1077 
				&& shipToSnap.getX() <= 1153) {
			shipX = 1119;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setColumn(9);
		}
		checkForShipOffGrid(shipToSnap);
		if (shipToSnap.getX() < 476 || shipToSnap.getX() > 1153) {
			returnShipToStart(shipToSnap);
		}
	}
	/**
	 * Moves the ship into the nearest row to avoid
	 * overlapping rows.
	 * @param shipToSnap the selected ship
	 */
	public void snapShipToY(final Ship shipToSnap) {
		int shipX = shipToSnap.getX();
		int shipY;
		if (shipToSnap.getY() >= 30 && shipToSnap.getY() <= 105) {
			shipY = 76;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(0);
		} else if (shipToSnap.getY() > 105 
				&& shipToSnap.getY() <= 163) {
			shipY = 134;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(1);
		} else if (shipToSnap.getY() > 163 
				&& shipToSnap.getY() <= 221) {
			shipY = 192;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(2);
		} else if (shipToSnap.getY() > 221 
				&& shipToSnap.getY() <= 279) {
			shipY = 250;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(3);
		} else if (shipToSnap.getY() > 279 
				&& shipToSnap.getY() <= 337) {
			shipY = 308;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(4);
		} else if (shipToSnap.getY() > 337 
				&& shipToSnap.getY() <= 395) {
			shipY = 366;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(5);
		} else if (shipToSnap.getY() > 395 
				&& shipToSnap.getY() <= 453) {
			shipY = 424;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(6);
		} else if (shipToSnap.getY() > 453 
				&& shipToSnap.getY() <= 511) {
			shipY = 482;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(7);
		} else if (shipToSnap.getY() > 511 
				&& shipToSnap.getY() <= 569) {
			shipY = 540;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(8);
		} else if (shipToSnap.getY() > 569 
				&& shipToSnap.getY() <= 615) {
			shipY = 598;
			shipToSnap.setBounds(shipX, shipY,
					shipToSnap.getWidth(), 
					shipToSnap.getHeight());
			shipToSnap.setRow(9);
		}
		checkForShipOffGrid(shipToSnap);
		if (shipToSnap.getY() < 30 || shipToSnap.getY() > 615) {
			returnShipToStart(shipToSnap);
		}
		checkForShipOverLap(shipToSnap);
	}
	/**
	 * Checks to see if the ship is off the grid, if so
	 * calls returnShipToStart to move ship back to starting
	 * position.
	 * @param shipToSnap the selected ship
	 */
	public void checkForShipOffGrid(final Ship shipToSnap) {
		if ((shipToSnap.getY() + shipToSnap.getHeight()) > 650) {
			returnShipToStart(shipToSnap);
		} else if ((shipToSnap.getX() + shipToSnap.getWidth()) > 1200) {
			returnShipToStart(shipToSnap);
		}
	}
	/**
	 * Checks to see if the ship overlaps another. If so it 
	 * calls returnShipToStart to move ship back to starting
	 * position.
	 * @param shipToSnap the selected ship
	 */
	public void checkForShipOverLap(final Ship shipToSnap) {
		if (shipToSnap.getTypeOfShip() == ShipType.AircraftCarrier) {
			if (shipToSnap.getBounds()
					.intersects(battleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(patrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		} else if (shipToSnap.getTypeOfShip() == ShipType.Battleship) {
			if (shipToSnap.getBounds()
				.intersects(aircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(patrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		} else if (shipToSnap.getTypeOfShip() == ShipType.Cruiser) {
			if (shipToSnap.getBounds()
				.intersects(aircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(battleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(patrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		} else if (shipToSnap.getTypeOfShip() == ShipType.Submarine) {
			if (shipToSnap.getBounds()
				.intersects(aircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(battleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(patrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		} else if (shipToSnap.getTypeOfShip() == ShipType.PatrolBoat) {
			if (shipToSnap.getBounds()
				.intersects(aircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(battleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			} else if (shipToSnap.getBounds()
					.intersects(submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
	}
	/**
	 * Moves the ship back to its starting location.
	 * @param shipToSnap the selected ship
	 */
	public void returnShipToStart(final Ship shipToSnap) {
		if (shipToSnap.getTypeOfShip() == ShipType.AircraftCarrier) {
			shipToSnap.setLocation(0, 1);
		} else if (shipToSnap.getTypeOfShip() == ShipType.Battleship) {
			shipToSnap.setLocation(0, 109);
		} else if (shipToSnap.getTypeOfShip() == ShipType.Cruiser) {
			shipToSnap.setLocation(0, 220);
		} else if (shipToSnap.getTypeOfShip() == ShipType.Submarine) {
			shipToSnap.setLocation(0, 330);
		} else {
			shipToSnap.setLocation(0, 432);
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
	/**
	 * Populates the values 2D array and checks
	 * to make sure all ships are set.
	 */
	public void submit() {
		
		JLabel[][] oldGridLoc = new JLabel[boardSize][boardSize];
		
		//creates 2d array to remember old locations of grid
		//for reseting grid if ships aren't set correctly
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				oldGridLoc[x][y] = new JLabel();
				oldGridLoc[x][y].setLocation(
					(int) grid[x][y].getLocation().getX(), 
					(int) grid[x][y].getLocation().getY());
				
			}
		}
		
		//sets grid location to the locations on screen
//		for (int x = 0; x < boardSize; x++) {
//			for (int y = 0; y < boardSize; y++) {
//				grid[x][y].setLocation(grid[x][y]
//				.getLocationOnScreen().x,
//				grid[x][y].getLocationOnScreen().y);
//			}
//		}
		setValues(aircraftCarrier);
		setValues(battleShip);
		setValues(cruiser);
		setValues(submarine);
		setValues(patrolBoat);
		
		//Count the number of values occupied by a ship
		int player1Count = 0;
			if (player1Values != null) {
				for (int x = 0; x < boardSize; x++) {
					for (int y = 0; y < boardSize; y++) {
						if (player1Values[x][y] != 0) {
							player1Count++;
						}
					}
				}
			}
		int player2Count = 0;
			if (player2Values != null) {
				for (int x = 0; x < boardSize; x++) {
					for (int y = 0; y < boardSize; y++) {
						if (player2Values[x][y] != 0) {
							player2Count++;
						}
					}
				}
			}


		//count is = to 17 if all ships are placed correctly
		if (currentPlayer == 1) {
			if (player1Count == 17) {
				invalidShipPlacement = false;
			}
			else {
				invalidShipPlacement = true;
				JOptionPane.showMessageDialog(null,
						"Invalid ship placement.");
				for (int x = 0; x < boardSize; x++) {
					for (int y = 0; y < boardSize; y++) {
						grid[x][y].setLocation(
							(int) oldGridLoc[x][y]
								.getLocation().getX(),
							(int) oldGridLoc[x][y]
								.getLocation().getY());
						player1Values[x][y] = 0;
					}
				}
			}
		}
		else if (currentPlayer == 2) {
			if (player2Count == 17) {
				invalidShipPlacement2 = false;
			}
			else {
				invalidShipPlacement2 = true;
				JOptionPane.showMessageDialog(null,
						"Invalid ship placement.");
				for (int x = 0; x < boardSize; x++) {
					for (int y = 0; y < boardSize; y++) {
						player2Grid[x][y].setLocation(
							(int) oldGridLoc[x][y]
								.getLocation().getX(),
							(int) oldGridLoc[x][y]
								.getLocation().getY());
						player2Values[x][y] = 0;
					}
				}
			}
		}

		if (currentPlayer == 1 && invalidShipPlacement == false && mode == GameMode.OnePlayerMode) { 
			player1GameBoard = new 
					GameBoard(mode, diffChoice);
			player1GameBoard.setPlayer1Ships(player1Ships);
			player1GameBoard.setPlayer1Values(player1Values);
			setCpuValues(0);
			player1GameBoard.setPlayer2Values(player2Values);
			System.out.println("CPU Ships)");
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
				System.out.print(player2Values[x][y]);
				}
				
				System.out.println("");
			}
			this.setVisible(false);
			player1GameBoard.setLocationRelativeTo(null);
			player1GameBoard.setVisible(true);
			player1GameBoard.beginGame();
		}
		else if (currentPlayer == 1 && invalidShipPlacement == false && mode == GameMode.TwoPlayerPassAndPlay) {
			player1GameBoard = new 
					GameBoard(mode, diffChoice);
			player1GameBoard.setTitle("Player 1 GameBoard");
			player1GameBoard.setPlayer1Values(player1Values);
			this.setTitle("Player 2 Set Ships");
			this.getContentPane().removeAll();
			shipSetup(mode, 2, diffChoice);		
		}
		else if (currentPlayer == 2 && invalidShipPlacement == false && mode == GameMode.TwoPlayerPassAndPlay) {
			//player2GameBoard = new GameBoard(mode, diffChoice);
			//player2GameBoard.setTitle("Player 2 GameBoard");
			//player2GameBoard.setPlayer1Values(player2Values);
			player1GameBoard.setPlayer2Values(player2Values);
			//player2GameBoard.setPlayer2Values(player1GameBoard.getPlayer1Values());
//			setPlayer2Ships(player1GameBoard);
//			setPlayer1Ships(player2GameBoard);
			this.setVisible(false);
			//GameBoardConnector connector = new GameBoardConnector(player1GameBoard, player2GameBoard);
			player1GameBoard.beginPassAndPlay();
			//connector.BeginGame();
		}
		else if (invalidShipPlacement == false && mode == GameMode.MultiplayerMode) {
			
			areShipsSet = true;
			this.setVisible(false);	
		}
		
	}
	
	public GameBoard getGameBoard() {
		while (player1GameBoard == null) {
			
		}
		return player1GameBoard;
	}
	
	private void setPlayer1Ships(GameBoard board) {
		board.setPlayer2Values(player1GameBoard.getPlayer1Values());
	}
	
	private void setPlayer2Ships(GameBoard board) {
		board.setPlayer2Values(player2Values);
	}
		
//		else if (currentPlayer == 2 && invalidShipPlacement2 == false && mode == GameMode.TwoPlayerPassAndPlay) {
//			playingBoard.setPlayer2Values(player2Values);
//		}
//			if (this.getTitle().contains("1") 
//					&& mode == GameMode.OnePlayerMode) {
//				playingBoard.setPlayer1Ships(player1Ships);
//				playingBoard.setPlayer1Values(player1Values);
//				setCpuValues(0);
//				playingBoard.setPlayer2Values(player2Values);
//				System.out.println("CPU Ships)");
//				for (int x = 0; x < 10; x++) {
//					for (int y = 0; y < 10; y++) {
//					System.out.print(player2Values[x][y]);
//					}
//					
//					System.out.println("");
//				}
//				this.setVisible(false);
//				playingBoard.setLocationRelativeTo(null);
//				playingBoard.setVisible(true);
//				playingBoard.beginGame();
//			} else if (this.getTitle().contains("1") && mode
//					== GameMode.TwoPlayerPassAndPlay) {
//			   playingBoard.setPlayer1Ships(player1Ships);
//			   playingBoard.setPlayer1Values(board.getValues());
//				this.setTitle("Player 2 Set Ships");
//				this.getContentPane().removeAll();
//				shipSetup(mode, 2, diffChoice);
//			} else {
//				player1Values = getPlayer1Values();
//				player2Values = board.getValues();
//				
//				playingBoard.setPlayer2Ships(player2Ships);
//			   playingBoard.setPlayer2Values(board.getValues());
//				this.setVisible(false);
//				playingBoard.setVisible(true);
//				playingBoard.beginGame();
//			}
//		
		
	/**
	 * sets the values of the ships in the 2D array.
	 * @param shipToSet the selected ship
	 */
	public void setValues(final Ship shipToSet) {
		if(currentPlayer == 1) {
			for (int s = 0; s < shipToSet.getShipSize(); s++) {
				if (shipToSet.isHorizontal()) {
				 player1Values[shipToSet.getColumn()+ s]
						 [shipToSet.getRow()] 
							= shipToSet.getValue();
				} else {
				 player1Values[shipToSet.getColumn() ]
						 [shipToSet.getRow() + s] 
							= shipToSet.getValue();
				}
			}
		}
		else if(currentPlayer == 2) {
			for (int s = 0; s < shipToSet.getShipSize(); s++) {
				if (shipToSet.isHorizontal()) {
				 player2Values[shipToSet.getColumn()+ s]
						 [shipToSet.getRow()] 
							= shipToSet.getValue();
				} else {
				 player2Values[shipToSet.getColumn() ]
						 [shipToSet.getRow() + s] 
							= shipToSet.getValue();
				}
			}
		}
		
	}
	
	/**
	*Sets the values for each cpu ship by calling
	*their respective methods.
	*@param value in game play this will always
	*be 0, but in testing it can be set to 1 for 
	*a horizontal ship, or 2 for a vertical ship.
	*/	
	public void setCpuValues(final int value) {
		setAircraftCarrier(value);
		setBattleShip(value);
		setCruiser(value);
		setSubmarine(value);
		setPatrolBoat(value);				
	}
	/**
	*Sets the location for the cpu's aircraft carrier.
	*@param value for testing this value can be set
	*to 1 for a horizontal ship, or 2 for a 
	*vertical ship. Otherwise set to 0.
	*/	
	private void setAircraftCarrier(final int value) {
		boolean aircraftCarrierSet = false;
		while (!aircraftCarrierSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) {
				shipVertical = false;
			} else {
				shipVertical = true;
			}
			if (value == 1) {
				shipVertical = false;
			} else if (value == 2) {
				shipVertical = true;
			}
			if (shipVertical) {
				if (randomX > 5) {
					continue; 
				} else {
					player2Values[randomX][randomY]
					   = aircraftCarrier.getValue();
					player2Values[randomX + 1][randomY] 
					   = aircraftCarrier.getValue();
					player2Values[randomX + 2][randomY] 
					   = aircraftCarrier.getValue();
					player2Values[randomX + 3][randomY] 
					   = aircraftCarrier.getValue();
					player2Values[randomX + 4][randomY] 
					   = aircraftCarrier.getValue();
					aircraftCarrierSet = true;
				}
			} else if (!shipVertical) {
				if (randomY > 5) {
					continue;
				} else {
					player2Values[randomX][randomY]
				       = aircraftCarrier.getValue();
					player2Values[randomX][randomY + 1] 
					   = aircraftCarrier.getValue();
					player2Values[randomX][randomY + 2] 
					   = aircraftCarrier.getValue();
					player2Values[randomX][randomY + 3]
					   = aircraftCarrier.getValue();
					player2Values[randomX][randomY + 4]
					   = aircraftCarrier.getValue();
					aircraftCarrierSet = true;
				}
			}
		}
	}
	/**
	*Sets the location for the cpu's battle ship.
	*@param value for testing this value can be set
	*to 1 for a horizontal ship, or 2 for a 
	*vertical ship. Otherwise set to 0.
	*/	
	private void setBattleShip(final int value) {
		boolean battleShipSet = false;
		while (!battleShipSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) {
				shipVertical = false;
			} else {
				shipVertical = true;
			}
			if (value == 1) {
				shipVertical = false;
			} else if (value == 2) {
				shipVertical = true;
			}
			if (shipVertical) {
				if (randomX > 6) {
					continue; 
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX + 1][randomY]
							== 0
					&& player2Values[randomX + 2][randomY]
							== 0 
					&& player2Values[randomX + 3][randomY]
							== 0) {
					player2Values[randomX][randomY]
							= battleShip.getValue();
					player2Values[randomX + 1][randomY]
							= battleShip.getValue();
					player2Values[randomX + 2][randomY] 
							= battleShip.getValue();
					player2Values[randomX + 3][randomY] 
							= battleShip.getValue();
						battleShipSet = true;
					} else {
						continue;
					}
				}
			} else if (!shipVertical) {
				if (randomY > 6) {
					continue; 
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX][randomY + 1]
							== 0
					&& player2Values[randomX][randomY + 2]
							== 0 
					&& player2Values[randomX][randomY + 3]
							== 0) {
					player2Values[randomX][randomY]
							= battleShip.getValue();
					player2Values[randomX][randomY + 1] 
							= battleShip.getValue();
					player2Values[randomX][randomY + 2] 
							= battleShip.getValue();
					player2Values[randomX][randomY + 3] 
							= battleShip.getValue();
						battleShipSet = true;
					} else {
						continue;
					}
				}
			}
		}
	}
	/**
	*Sets the location for the cpu's cruiser.
	*@param value for testing this value can be set
	*to 1 for a horizontal ship, or 2 for a 
	*vertical ship. Otherwise set to 0.
	*/	
	private void setCruiser(final int value) {
		boolean cruiserSet = false;
		while (!cruiserSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) {
				shipVertical = false;
			} else {
				shipVertical = true;
			}
			if (value == 1) {
				shipVertical = false;
			} else if (value == 2) {
				shipVertical = true;
			}
			if (shipVertical) {
				if (randomX > 7) {
					continue; 
				} else {
					if (player2Values[randomX][randomY]
							== 0 
					&& player2Values[randomX + 1][randomY]
							== 0
					&& player2Values[randomX + 2][randomY]
									== 0) {
					player2Values[randomX][randomY]
							= cruiser.getValue();
					player2Values[randomX + 1][randomY]
							= cruiser.getValue();
					player2Values[randomX + 2][randomY] 
							= cruiser.getValue();
						cruiserSet = true;
					} else {
						continue;
					}
				}
			} else if (!shipVertical) {
				if (randomY > 7) {
					continue;
				} else {	
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX][randomY + 1]
							== 0
					&& player2Values[randomX][randomY + 2]
							== 0) {
					player2Values[randomX][randomY]
							= cruiser.getValue();
					player2Values[randomX][randomY + 1] 
							= cruiser.getValue();
					player2Values[randomX][randomY + 2] 
							= cruiser.getValue();
						cruiserSet = true;
					} else {
						continue;
					}
				}
			}
		}
	}
	/**
	*Sets the location for the cpu's submarine.
	*@param value for testing this value can be set
	*to 1 for a horizontal ship, or 2 for a 
	*vertical ship. Otherwise set to 0.
	*/	
	private void setSubmarine(final int value) {
		boolean submarineSet = false;
		while (!submarineSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) {
				shipVertical = false;
			} else {
				shipVertical = true;
			}
			if (value == 1) {
				shipVertical = false;
			} else if (value == 2) {
				shipVertical = true;
			}
			if (shipVertical) {
				if (randomX > 7) {
					continue; 
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX + 1][randomY]
							== 0
					&& player2Values[randomX + 2][randomY] 
							== 0) {
					player2Values[randomX][randomY]
							= submarine.getValue();
					player2Values[randomX + 1][randomY] 
							= submarine.getValue();
					player2Values[randomX + 2][randomY] 
							= submarine.getValue();
						submarineSet = true;
					} else {
						continue;
					}
				}
			} else if (!shipVertical) {
				if (randomY > 7) {
					continue;
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX][randomY + 1]
							== 0
					&& player2Values[randomX][randomY + 2] 
									== 0) {
					player2Values[randomX][randomY] 
							= submarine.getValue();
					player2Values[randomX][randomY + 1] 
							= submarine.getValue();
					player2Values[randomX][randomY + 2] 
							= submarine.getValue();
						submarineSet = true;
					} else {
						continue;
					}
				}
			}
		}
	}
	/**
	*Sets the location for the cpu's patrol boat.
	*@param value for testing this value can be set
	*to 1 for a horizontal ship, or 2 for a 
	*vertical ship. Otherwise set to 0.
	*/	
	private void setPatrolBoat(final int value) {
		boolean patrolBoatSet = false;
		while (!patrolBoatSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) {
				shipVertical = false;
			} else {
				shipVertical = true;
			}
			if (value == 1) {
				shipVertical = false;
			} else if (value == 2) {
				shipVertical = true;
			}
			if (shipVertical) {
				if (randomX > 8) {
					continue;
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX + 1][randomY] 
							== 0) {
					player2Values[randomX][randomY] 
							= patrolBoat.getValue();
					player2Values[randomX + 1][randomY] 
							= patrolBoat.getValue();
						patrolBoatSet = true;
					} else {
						continue;
					}
				}
			} else if (!shipVertical) {
				if (randomY > 8) {
					continue;
				} else {
					if (player2Values[randomX][randomY] 
							== 0 
					&& player2Values[randomX][randomY + 1]
							== 0) {
					player2Values[randomX][randomY] 
							= patrolBoat.getValue();
					player2Values[randomX][randomY + 1] 
							= patrolBoat.getValue();
						patrolBoatSet = true;
					} else {
						continue;
					}
				}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//on S keypress auto set ships and continue
		if(e.getKeyCode() == KeyEvent.VK_S) {
			System.out.println("Keyboard press");
			
			aircraftCarrier.rotate();
			battleShip.rotate();
			battleShip.rotate();
			submarine.rotate();
			cruiser.rotate();
			cruiser.rotate();
			patrolBoat.rotate();
			
			aircraftCarrier.setLocation(1050, 125);
			snapShipToX(aircraftCarrier);
			snapShipToY(aircraftCarrier);
			
			battleShip.setLocation(800, 550);
			snapShipToX(battleShip);
			snapShipToY(battleShip);
			
			submarine.setLocation(550, 400);
			snapShipToX(submarine);
			snapShipToY(submarine);
			
			cruiser.setLocation(600, 125);
			snapShipToX(cruiser);
			snapShipToY(cruiser);
			
			patrolBoat.setLocation(900, 200);
			snapShipToX(patrolBoat);
			snapShipToY(patrolBoat);
			
			submit();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			System.out.println("Keyboard press");
			
			aircraftCarrier.rotate();
			aircraftCarrier.rotate();
			battleShip.rotate();
			battleShip.rotate();
			submarine.rotate();
			submarine.rotate();
			cruiser.rotate();
			cruiser.rotate();
			patrolBoat.rotate();
			patrolBoat.rotate();
			
			aircraftCarrier.setLocation(516, 76);
			snapShipToX(aircraftCarrier);
			snapShipToY(aircraftCarrier);
			
			battleShip.setLocation(516,136);
			snapShipToX(battleShip);
			snapShipToY(battleShip);
			
			submarine.setLocation(516,196);
			snapShipToX(submarine);
			snapShipToY(submarine);
			
			cruiser.setLocation(516,256);
			snapShipToX(cruiser);
			snapShipToY(cruiser);
			
			patrolBoat.setLocation(516,316);
			snapShipToX(patrolBoat);
			snapShipToY(patrolBoat);
			
			submit();
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING && mode == GameMode.MultiplayerMode) {
			
			System.exit(0);
				
			super.processWindowEvent(e);
		} else {
			super.processWindowEvent(e);
		}

	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
