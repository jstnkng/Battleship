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
		AircraftCarrier = new Ship(ShipType.AircraftCarrier);
		Ships[0] = AircraftCarrier;
		
		//add first ship to layered pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .4;
		c.weighty = .33;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(AircraftCarrier, c, 1);
		
	
		//create second ship
		BattleShip = new Ship(ShipType.Battleship);
		Ships[1] = BattleShip;
		
		//add second ship to layered pane
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .4;
		c.weighty = .33;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(BattleShip, c, 1);
		
		Cruiser = new Ship(ShipType.Cruiser);
		Ships[2] = Cruiser;
		
		PatrolBoat = new Ship(ShipType.PatrolBoat);
		Ships[3] = PatrolBoat;
		
		Submarine = new Ship(ShipType.Submarine);
		Ships[4] = Submarine;
		
		submit = new JButton();
		submit.setText("Submit");
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = .4;
		c.weighty = .33;
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
		
		//add board to the layeredPane
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .6;
		c.weighty = 1;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(board, c, -1);
		
		//add layered pane to the JPanel
		this.add(layeredPane);
		
		addMouseListener(this);
		addMouseMotionListener(this);
//		contentPane.setOpaque(true);
//		this.setContentPane(contentPane);
		
		//Display;
		this.setSize(1200,700);
		this.setVisible(true);	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		if(ACcanDrag) {
			AircraftCarrier.x = mx - dragFromX;
			AircraftCarrier.y = my - dragFromY;
			
			AircraftCarrier.setLocation(AircraftCarrier.x, AircraftCarrier.y);
		}
		
		if(BScanDrag) {
			BattleShip.x = mx - dragFromX;
			BattleShip.y = my - dragFromY;
			
			BattleShip.setLocation(BattleShip.x, BattleShip.y);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (mx >= AircraftCarrier.x && mx <= (AircraftCarrier.x + AircraftCarrier.width) && my >= AircraftCarrier.y && my <= (AircraftCarrier.y + AircraftCarrier.height)) {
			AircraftCarrier.rotate(flip);
			AircraftCarrier.setSize(AircraftCarrier.height, AircraftCarrier.width);
			int temp = AircraftCarrier.height;
			AircraftCarrier.height = AircraftCarrier.width;
			AircraftCarrier.width = temp;
			
			if (flip == true) {
				flip = false;
			}else {
				flip = true;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		//Location for BattleShip
		BattleShip.x = BattleShip.getLocationOnScreen().x;
		BattleShip.y = BattleShip.getLocationOnScreen().y;
		
		if (mx >= AircraftCarrier.x && mx <= (AircraftCarrier.x + BattleShip.width) && my >= AircraftCarrier.y && my <= (AircraftCarrier.y + AircraftCarrier.height)) {
			ACcanDrag = true;
			dragFromX = mx - AircraftCarrier.x;
			dragFromY = my - AircraftCarrier.y;
			
			
		}else {
			ACcanDrag = false;
		}
		
		if (mx >= BattleShip.x && mx <= (BattleShip.x + BattleShip.width) && my >= BattleShip.y && my <= (BattleShip.y + BattleShip.height)) {
			BScanDrag = true;
			dragFromX = mx - BattleShip.x;
			dragFromY = my - BattleShip.y;
		}else {
			BScanDrag = false;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		board.setBackground(Color.CYAN);
		
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
		
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				
				if(grid[x][y].getBounds().intersects(AircraftCarrier.getBounds())) {
					//board.values[x][y] = 1;
				} /*else if (grid[x][y].getBounds().intersects(BattleShip.getBounds())) {
					value[x][y] = 1;
				}*/
				
				
			}
		}
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				
				//System.out.print(value[x][y] + " ");
				System.out.print(grid[x][y].getBounds().toString());
				
			}
			
			System.out.println("");
		}
		
		System.out.print(AircraftCarrier.getBounds().toString());
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
