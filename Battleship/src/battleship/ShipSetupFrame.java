package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ShipSetupFrame extends JFrame implements MouseListener, MouseMotionListener{
	
	private int mx, my;
	private int dragFromX, dragFromY;
	private boolean ACcanDrag = false;
	private boolean BScanDrag = false;
	private boolean CcanDrag = false;
	private boolean ScanDrag = false;
	private boolean PBcanDrag = false;
	
	private int boardSize = 10;
	private JLayeredPane layeredPane;
	private Grid board;
	
	private Ship AircraftCarrier;
	private Ship BattleShip;
	private Ship Cruiser;
	private Ship PatrolBoat;
	private Ship Submarine;
	private boolean flip = true;
	
	private JButton submit;
	private GameMode mode;
	
	private JLabel[][] grid = new JLabel[boardSize][boardSize];
	private int[][] values = new int[boardSize][boardSize];
	
	private Ship[] Ships = new Ship[5];

	public ShipSetupFrame(GameMode currentMode, int player) {
		ShipSetup(currentMode, player);
	}
	
	public void ShipSetup(GameMode currentMode, int player) {
		if (player == 2) {
			this.setTitle("Player 2 Set Ships");
		}
		else this.setTitle("Player 1 Set Ships");
		
		mode = currentMode;
		//JComponent contentPane = new ShipSetupPanel(currentMode);
		getContentPane().setLayout(
			    new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)
			);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(gridbag);
		
		//create Aircraft Carrier
		AircraftCarrier = new Ship(ShipType.AircraftCarrier);
		Ships[0] = AircraftCarrier;
		
		//add Aircraft Carrier ship to layered pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(AircraftCarrier, c);
		layeredPane.setLayer(AircraftCarrier, 1, 0);
	
		//create Battle Ship 
		BattleShip = new Ship(ShipType.Battleship);
		Ships[1] = BattleShip;
		
		//add Battle Ship to layered pane
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(BattleShip, c);
		layeredPane.setLayer(BattleShip, 1, 1);
		
		//create cruiser
		Cruiser = new Ship(ShipType.Cruiser);
		Ships[2] = Cruiser;
		
		//add cruiser to layered pane
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(Cruiser, c);
		layeredPane.setLayer(Cruiser, 1, 2);
		
		//create submarine
		Submarine = new Ship(ShipType.Submarine);
		Ships[4] = Submarine;
		
		//add submarine to layered pane
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(Submarine, c);
		layeredPane.setLayer(Submarine, 1, 3);
		
		//create patrol boat
		PatrolBoat = new Ship(ShipType.PatrolBoat);
		Ships[3] = PatrolBoat;
		
		//add patrol boat to layered pane
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = .4;
		c.weighty = .16;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(PatrolBoat, c);
		layeredPane.setLayer(PatrolBoat, 1, 1);
		
		
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
			public void mouseClicked(MouseEvent e) {
				
				submit();
			}
		});
		
		board = new Grid(boardSize, "Label");
		grid = Grid.getLabelGrid();
		values = board.getValues();
		
		//add board to the layeredPane
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .6;
		c.weighty = 1;
		c.gridheight = 6;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(board, c);
		layeredPane.setLayer(board, -1);
		
		//add layered pane to the JPanel
		this.add(layeredPane);
		
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
//		contentPane.setOpaque(true);
//		this.setContentPane(contentPane);
		
		//Display;
		this.setSize(1200,700);
		this.setResizable(false);
		this.setVisible(true);	
		
		//used to fix x and y location of ships
		setBounds();
		
	}
	
	//sets the ships x and y values to be relative to the screen instead of the parent's coordinate space
	public void setBounds() {
		
		//AircraftCarrier
		AircraftCarrier.x = AircraftCarrier.getLocationOnScreen().x;
		AircraftCarrier.y = AircraftCarrier.getLocationOnScreen().y;
		AircraftCarrier.setBounds(AircraftCarrier.x, AircraftCarrier.y, AircraftCarrier.width, AircraftCarrier.height);
		
		//BattleShip
		BattleShip.x = BattleShip.getLocationOnScreen().x;
		BattleShip.y = BattleShip.getLocationOnScreen().y;
		BattleShip.setBounds(BattleShip.x, BattleShip.y, BattleShip.width, BattleShip.height);
		
		//Cruiser
		Cruiser.x = Cruiser.getLocationOnScreen().x;
		Cruiser.y = Cruiser.getLocationOnScreen().y;
		Cruiser.setBounds(Cruiser.x, Cruiser.y, Cruiser.width, Cruiser.height);
		
		//Submarine
		Submarine.x = Submarine.getLocationOnScreen().x;
		Submarine.y = Submarine.getLocationOnScreen().y;
		Submarine.setBounds(Submarine.x, Submarine.y, Submarine.width, Submarine.height);
		
		//PatrolBoat
		PatrolBoat.x = PatrolBoat.getLocationOnScreen().x;
		PatrolBoat.y = PatrolBoat.getLocationOnScreen().y;
		PatrolBoat.setBounds(PatrolBoat.x, PatrolBoat.y, PatrolBoat.width, PatrolBoat.height);
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		
		if(ACcanDrag) {
			AircraftCarrier.x = mx - dragFromX;
			AircraftCarrier.y = my - dragFromY;
			AircraftCarrier.setBounds(AircraftCarrier.x, AircraftCarrier.y, AircraftCarrier.width, AircraftCarrier.height);
			
		}else if(BScanDrag) {
			BattleShip.x = mx - dragFromX;
			BattleShip.y = my - dragFromY;
			BattleShip.setBounds(BattleShip.x, BattleShip.y, BattleShip.width, BattleShip.height);
			
		}else if(CcanDrag) {
			Cruiser.x = mx - dragFromX;
			Cruiser.y = my - dragFromY;
			Cruiser.setBounds(Cruiser.x, Cruiser.y, Cruiser.width, Cruiser.height);
			
		}else if(ScanDrag) {
			Submarine.x = mx - dragFromX;
			Submarine.y = my - dragFromY;
			Submarine.setBounds(Submarine.x, Submarine.y, Submarine.width, Submarine.height);
			
		}else if(PBcanDrag) {
			PatrolBoat.x = mx - dragFromX;
			PatrolBoat.y = my - dragFromY;
			PatrolBoat.setBounds(PatrolBoat.x, PatrolBoat.y, PatrolBoat.width, PatrolBoat.height);
			
		}
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//Aircraft Carrier
		int ACX = AircraftCarrier.x;
		int ACY = AircraftCarrier.y;
		int ACW = AircraftCarrier.width;
		int ACH = AircraftCarrier.height;
		
		if (mx >= ACX && mx <= (ACX + ACW) && my >= ACY && my <= (ACY + ACH)) {
			
			if (AircraftCarrier.isHorizontal == true) {
				AircraftCarrier.isHorizontal = false;
				AircraftCarrier.rotate();
				AircraftCarrier.setSize(AircraftCarrier.widthV, AircraftCarrier.heightV);
				
				
			}else {
				AircraftCarrier.isHorizontal = true;
				AircraftCarrier.rotate();
				AircraftCarrier.setSize(AircraftCarrier.widthH, AircraftCarrier.heightH);
			}
		}
		
		//BattleShip
		int BSX = BattleShip.x;
		int BSY = BattleShip.y;
		int BSW = BattleShip.width;
		int BSH = BattleShip.height;
		
		if (mx >= BSX && mx <= (BSX + BSW) && my >= BSY && my <= (BSY + BSH)) {
			
			if (BattleShip.isHorizontal == true) {
				BattleShip.isHorizontal = false;
				BattleShip.rotate();
				BattleShip.setSize(BattleShip.widthV, BattleShip.heightV);
				
				
			}else {
				BattleShip.isHorizontal = true;
				BattleShip.rotate();
				BattleShip.setSize(BattleShip.widthH, BattleShip.heightH);
			}
		}
		
		//Cruiser
		int CX = Cruiser.x;
		int CY = Cruiser.y;
		int CW = Cruiser.width;
		int CH = Cruiser.height;

		if (mx >= CX && mx <= (CX + CW) && my >= CY && my <= (CY + CH)) {

			if (Cruiser.isHorizontal == true) {
				Cruiser.isHorizontal = false;
				Cruiser.rotate();
				Cruiser.setSize(Cruiser.widthV, Cruiser.heightV);


			}else {
				Cruiser.isHorizontal = true;
				Cruiser.rotate();
				Cruiser.setSize(Cruiser.widthH, Cruiser.heightH);
			}
		}
		
		//Submarine
		int SX = Submarine.x;
		int SY = Submarine.y;
		int SW = Submarine.width;
		int SH = Submarine.height;

		if (mx >= SX && mx <= (SX + SW) && my >= SY && my <= (SY + SH)) {

			if (Submarine.isHorizontal == true) {
				Submarine.isHorizontal = false;
				Submarine.rotate();
				Submarine.setSize(Submarine.widthV, Submarine.heightV);


			}else {
				Submarine.isHorizontal = true;
				Submarine.rotate();
				Submarine.setSize(Submarine.widthH, Submarine.heightH);
			}
		}
		
		//PatrolBoat
		int PBX = PatrolBoat.x;
		int PBY = PatrolBoat.y;
		int PBW = PatrolBoat.width;
		int PBH = PatrolBoat.height;

		if (mx >= PBX && mx <= (PBX + PBW) && my >= PBY && my <= (PBY + PBH)) {

			if (PatrolBoat.isHorizontal == true) {
				PatrolBoat.isHorizontal = false;
				PatrolBoat.rotate();
				PatrolBoat.setSize(PatrolBoat.widthV, PatrolBoat.heightV);


			}else {
				PatrolBoat.isHorizontal = true;
				PatrolBoat.rotate();
				PatrolBoat.setSize(PatrolBoat.widthH, PatrolBoat.heightH);
			}
		}
				
				
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		mx = e.getX();
		my = e.getY();
		
		//Aircraft Carrier
		int ACX = AircraftCarrier.x;
		int ACY = AircraftCarrier.y;
		int ACW = AircraftCarrier.width;
		int ACH = AircraftCarrier.height;
		
		if (mx >= ACX && mx <= (ACX + ACW) && my >= ACY && my <= (ACY + ACH)) {
			
			layeredPane.moveToFront(AircraftCarrier);
			ACcanDrag = true;
			dragFromX = mx - ACX;
			dragFromY = my - ACY;
		}else {
			ACcanDrag = false;
		}
		
		//BattleShip
		int BSX = BattleShip.x;
		int BSY = BattleShip.y;
		int BSW = BattleShip.width;
		int BSH = BattleShip.height;
		
		if (mx >= BSX && mx <= (BSX + BSW) && my >= BSY && my <= (BSY + BSH)) {
			
			layeredPane.moveToFront(BattleShip);
			BScanDrag = true;
			dragFromX = mx - BSX;
			dragFromY = my - BSY;
			
		}else {
			BScanDrag = false;
		}
		
		//Cruiser
		int CX = Cruiser.x;
		int CY = Cruiser.y;
		int CW = Cruiser.width;
		int CH = Cruiser.height;

		if (mx >= CX && mx <= (CX + CW) && my >= CY && my <= (CY + CH)) {
			
			layeredPane.moveToFront(Cruiser);
			CcanDrag = true;
			dragFromX = mx - CX;
			dragFromY = my - CY;

		}else {
			CcanDrag = false;
		}
		
		//Submarine
		int SX = Submarine.x;
		int SY = Submarine.y;
		int SW = Submarine.width;
		int SH = Submarine.height;

		if (mx >= SX && mx <= (SX + SW) && my >= SY && my <= (SY + SH)) {
			
			layeredPane.moveToFront(Submarine);
			ScanDrag = true;
			dragFromX = mx - SX;
			dragFromY = my - SY;

		}else {
			ScanDrag = false;
		}
		
		//PatrolBoat
		int PBX = PatrolBoat.x;
		int PBY = PatrolBoat.y;
		int PBW = PatrolBoat.width;
		int PBH = PatrolBoat.height;

		if (mx >= PBX && mx <= (PBX + PBW) && my >= PBY && my <= (PBY + PBH)) {
			
			layeredPane.moveToFront(PatrolBoat);
			PBcanDrag = true;
			dragFromX = mx - PBX;
			dragFromY = my - PBY;

		}else {
			PBcanDrag = false;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void submit() {
		board.setBackground(Color.GREEN);
		
		
		
		//fixes ship locations
		setBounds();
		
		//sets grid locations relative to the screen instead of parent
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				grid[x][y].setLocation(grid[x][y].getLocationOnScreen().x, grid[x][y].getLocationOnScreen().y);
			}
		}
		
		//checks to see if ships intersect with grid cells. sets values to 1
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				
				if(grid[x][y].getBounds().intersects(AircraftCarrier.getBounds())) {
					values[x][y] = 1;
				} else if (grid[x][y].getBounds().intersects(BattleShip.getBounds())) {
					values[x][y] = 1;
				}else if (grid[x][y].getBounds().intersects(Cruiser.getBounds())) {
					values[x][y] = 1;
				}else if (grid[x][y].getBounds().intersects(Submarine.getBounds())) {
					values[x][y] = 1;
				}else if (grid[x][y].getBounds().intersects(PatrolBoat.getBounds())) {
					values[x][y] = 1;
				}
			}
		}
		
		//Count the number of 1 values
		int count = 0;
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				if (values[x][y] == 1) {
					count++;
				}
			}
		}
		
		//count is = to 17 if all ships are placed correctly
		if(count == 17) {
		
			GameBoard playingBoard = new GameBoard(mode);
			if (this.getTitle().contains("1") && mode == GameMode.OnePlayerMode) {
				playingBoard.player1Ships = Ships;
				playingBoard.player1Values = board.values;
				playingBoard.player2Values = setCpuValues();

				this.setVisible(false);
				playingBoard.setVisible(true);
				playingBoard.beginGame();
			}
			else if (this.getTitle().contains("1") && mode == GameMode.TwoPlayerPassAndPlay) {
				playingBoard.player1Ships = Ships;
				playingBoard.player1Values = board.values;
				this.setTitle("Player 2 Set Ships");
				this.getContentPane().removeAll();
				ShipSetup(mode, 2);
			}
			else {
				playingBoard.player2Ships = Ships;
				playingBoard.player2Values = board.values;
				this.setVisible(false);
				playingBoard.setVisible(true);
				playingBoard.beginGame();
			}
		} else {
			
			//if the ships aren't placed correctly
			board.setBackground(Color.RED);
			System.err.println("Ships placed Incorrectley");
			
		}
		
	}
	
	private int[][] setCpuValues() {
		int[][]values = new int[10][10];
		boolean aircraftCarrierSet = false;
		boolean battleShipSet = false;
		boolean cruiserSet = false;
		boolean submarineSet = false;
		boolean patrolBoatSet = false;
		while (!aircraftCarrierSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) shipVertical = false; //50/50 chance of ship being vertical/horizontal
			else shipVertical = true;
			if (shipVertical == true) {
				if (randomX > 5) continue; //Too far down the grid to place AC vertically, try again
				else {	//Place the AC (this is the first ship to be placed, don't need to check for other ships
					values[randomX][randomY] = 1;
					values[randomX + 1][randomY] = 1;
					values[randomX + 2][randomY] = 1;
					values[randomX + 3][randomY] = 1;
					values[randomX + 4][randomY] = 1;
					aircraftCarrierSet = true;
					System.out.println("");
					System.out.println("");
					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 10; y++) {
							
							//System.out.print(value[x][y] + " ");
							System.out.print(values[x][y]);
						}
						
						System.out.println("");
					}
					System.out.println("Aircraft carrier set vertically");
				}
			}
			else if (shipVertical == false) {
				if (randomY > 5) continue; //Too far across the grid to place AC horizontally, try again
				else {	//Place the AC (again, don't need to check for other ships right now
					values[randomX][randomY] = 1;
					values[randomX][randomY + 1] = 1;
					values[randomX][randomY + 2] = 1;
					values[randomX][randomY + 3] = 1;
					values[randomX][randomY + 4] = 1;
					aircraftCarrierSet = true;
					System.out.println("");
					System.out.println("");
					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 10; y++) {
							
							//System.out.print(value[x][y] + " ");
							System.out.print(values[x][y]);
						}
						
						System.out.println("");
					}
					System.out.println("Aircraft carrier set horizontally");
				}
			}
		}
		while (!battleShipSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) shipVertical = false; //50/50 chance of ship being vertical/horizontal
			else shipVertical = true;
			if (shipVertical == true) {
				if (randomX > 6) continue; //Too far down the grid to place BS vertically, try again
				else {	//Place the BS if there aren't any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX + 1][randomY] == 0
							&& values[randomX + 2][randomY] == 0 && values[randomX + 3][randomY] == 0) {
						values[randomX][randomY] = 1;
						values[randomX + 1][randomY] = 1;
						values[randomX + 2][randomY] = 1;
						values[randomX + 3][randomY] = 1;
						battleShipSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Battleship set vertically");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 6) continue; //Too far across the grid to place BS horizontally, try again
				else {	//Place the BS if there arent any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX][randomY  + 1] == 0
							&& values[randomX][randomY  + 2] == 0 && values[randomX][randomY + 3] == 0) {
						values[randomX][randomY] = 1;
						values[randomX][randomY + 1] = 1;
						values[randomX][randomY + 2] = 1;
						values[randomX][randomY + 3] = 1;
						battleShipSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Battleship set horizontally");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
		}
		while (!cruiserSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) shipVertical = false; //50/50 chance of ship being vertical/horizontal
			else shipVertical = true;
			if (shipVertical == true) {
				if (randomX > 7) continue; //Too far down the grid to place cruiser vertically, try again
				else {	//Place the cruiser if there aren't any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX + 1][randomY] == 0
							&& values[randomX + 2][randomY] == 0) {
						values[randomX][randomY] = 1;
						values[randomX + 1][randomY] = 1;
						values[randomX + 2][randomY] = 1;
						cruiserSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Cruiser set vertically");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 7) continue; //Too far across the grid to place cruiser horizontally, try again
				else {	//Place the cruiser if there arent any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX][randomY + 1] == 0
							&& values[randomX][randomY + 2] == 0) {
						values[randomX][randomY] = 1;
						values[randomX][randomY + 1] = 1;
						values[randomX][randomY + 2] = 1;
						cruiserSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Cruiser set horizontally");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
		}
		while (!submarineSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) shipVertical = false; //50/50 chance of ship being vertical/horizontal
			else shipVertical = true;
			if (shipVertical == true) {
				if (randomX > 7) continue; //Too far down the grid to place sub vertically, try again
				else {	//Place the sub if there aren't any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX + 1][randomY] == 0
							&& values[randomX + 2][randomY] == 0) {
						values[randomX][randomY] = 1;
						values[randomX + 1][randomY] = 1;
						values[randomX + 2][randomY] = 1;
						submarineSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Submarine set vertically");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 7) continue; //Too far across the grid to place sub horizontally, try again
				else {	//Place the sub if there arent any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX][randomY + 1] == 0
							&& values[randomX][randomY + 2] == 0) {
						values[randomX][randomY] = 1;
						values[randomX][randomY + 1] = 1;
						values[randomX][randomY + 2] = 1;
						submarineSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Submarine set horizontally");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
		}
		while (!patrolBoatSet) {
			boolean shipVertical = false;
			int minimum = 0;
			int maximum = 9;
			Random rn = new Random();
			int range = maximum - minimum + 1;
			int randomX =  rn.nextInt(range) + minimum;
			int randomY = rn.nextInt(range) + minimum;
			if (randomX < 5) shipVertical = false; //50/50 chance of ship being vertical/horizontal
			else shipVertical = true;
			if (shipVertical == true) {
				if (randomX > 8) continue; //Too far down the grid to place pb vertically, try again
				else {	//Place the pb if there aren't any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX + 1][randomY] == 0) {
						values[randomX][randomY] = 1;
						values[randomX + 1][randomY] = 1;
						patrolBoatSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Patrol Boat set vertically");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 8) continue; //Too far across the grid to place PB horizontally, try again
				else {	//Place the PB if there arent any ships in the way
					if (values[randomX][randomY] == 0 && values[randomX][randomY + 1] == 0) {
						values[randomX][randomY] = 1;
						values[randomX][randomY + 1] = 1;
						patrolBoatSet = true;
						System.out.println("");
						System.out.println("");
						for (int x = 0; x < 10; x++) {
							for (int y = 0; y < 10; y++) {
								
								//System.out.print(value[x][y] + " ");
								System.out.print(values[x][y]);
							}
							
							System.out.println("");
						}
						System.out.println("Patrol boat set horizontally");
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
		}
		

		return values;

		
	}
	
}
