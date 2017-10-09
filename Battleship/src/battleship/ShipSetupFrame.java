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
	private int currentPlayer;
	
	private JLabel[][] grid = new JLabel[boardSize][boardSize];
	private int[][] player1Values = new int[boardSize][boardSize];
	private int[][] player2Values = new int[boardSize][boardSize];
	
	private Ship[] player1Ships = new Ship[5];
	private Ship[] player2Ships = new Ship[5];

	public ShipSetupFrame(GameMode currentMode, int player) {
		ShipSetup(currentMode, player);
	}
	
	public void ShipSetup(GameMode currentMode, int player) {
		if (player == 2) {
			this.setTitle("Player 2 Set Ships");
		}
		else this.setTitle("Player 1 Set Ships");
		
		mode = currentMode;
		currentPlayer = player;
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
		player1Ships[0] = AircraftCarrier;
		
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
		player1Ships[1] = BattleShip;
		
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
		player1Ships[2] = Cruiser;
		
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
		player1Ships[4] = Submarine;
		
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
		player1Ships[3] = PatrolBoat;
		
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
		if (currentPlayer == 1) {
			player1Values = board.getValues();
		}

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
		//Set Ships to starting locations
		returnShipToStart(AircraftCarrier);
		returnShipToStart(BattleShip);
		returnShipToStart(Cruiser);
		returnShipToStart(Submarine);
		returnShipToStart(PatrolBoat);
		
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
			checkForShipOverLap(AircraftCarrier);
			checkForShipOffGrid(AircraftCarrier);
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
			checkForShipOverLap(BattleShip);
			checkForShipOffGrid(BattleShip);
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
			checkForShipOverLap(Cruiser);
			checkForShipOffGrid(Cruiser);
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
			checkForShipOverLap(Submarine);
			checkForShipOffGrid(Submarine);
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
			checkForShipOverLap(PatrolBoat);
			checkForShipOffGrid(PatrolBoat);
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
		mx = e.getX();
		my = e.getY();
		
		
		
		//Aircraft Carrier
		int ACX = AircraftCarrier.x;
		int ACY = AircraftCarrier.y;
		int ACW = AircraftCarrier.width;
		int ACH = AircraftCarrier.height;
		
		if (mx >= ACX && mx <= (ACX + ACW) && my >= ACY && my <= (ACY + ACH)) {
			
			snapShipToY(AircraftCarrier);
			snapShipToX(AircraftCarrier);
		}
		
		//BattleShip
		int BSX = BattleShip.x;
		int BSY = BattleShip.y;
		int BSW = BattleShip.width;
		int BSH = BattleShip.height;
		
		if (mx >= BSX && mx <= (BSX + BSW) && my >= BSY && my <= (BSY + BSH)) {
			
			snapShipToY(BattleShip);
			snapShipToX(BattleShip);
		}
		
		//Cruiser
		int CX = Cruiser.x;
		int CY = Cruiser.y;
		int CW = Cruiser.width;
		int CH = Cruiser.height;

		if (mx >= CX && mx <= (CX + CW) && my >= CY && my <= (CY + CH)) {
			
			snapShipToY(Cruiser);
			snapShipToX(Cruiser);
		}
		
		//Submarine
		int SX = Submarine.x;
		int SY = Submarine.y;
		int SW = Submarine.width;
		int SH = Submarine.height;

		if (mx >= SX && mx <= (SX + SW) && my >= SY && my <= (SY + SH)) {
			
			snapShipToY(Submarine);
			snapShipToX(Submarine);
		}
		
		//PatrolBoat
		int PBX = PatrolBoat.x;
		int PBY = PatrolBoat.y;
		int PBW = PatrolBoat.width;
		int PBH = PatrolBoat.height;

		if (mx >= PBX && mx <= (PBX + PBW) && my >= PBY && my <= (PBY + PBH)) {
			
			snapShipToY(PatrolBoat);
			snapShipToX(PatrolBoat);
		}
	}
	public void snapShipToX(Ship shipToSnap) {
		if (shipToSnap.getX() >= 476 && shipToSnap.getX() <= 549) {
			shipToSnap.x = 516;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 0;
		}
		else if (shipToSnap.getX() > 549 && shipToSnap.getX() <= 615) {
			shipToSnap.x = 583; 
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 1;
		}
		else if (shipToSnap.getX() > 615 && shipToSnap.getX() <= 681) {
			shipToSnap.x = 650; 
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 2;
		}
		else if (shipToSnap.getX() > 681 && shipToSnap.getX() <= 747) {
			shipToSnap.x = 717;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 3;
		}
		else if (shipToSnap.getX() > 747 && shipToSnap.getX() <= 813) {
			shipToSnap.x = 784; 
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 4;
		}
		else if (shipToSnap.getX() > 813 && shipToSnap.getX() <= 879) {
			shipToSnap.x = 851; 
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 5;
		}
		else if (shipToSnap.getX() > 879 && shipToSnap.getX() <= 945) {
			shipToSnap.x = 918;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 6;
		}
		else if (shipToSnap.getX() > 945 && shipToSnap.getX() <= 1011) {
			shipToSnap.x = 985;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 7;
		}
		else if (shipToSnap.getX() > 1011 && shipToSnap.getX() <= 1077) {
			shipToSnap.x = 1052;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 8;
		}
		else if (shipToSnap.getX() > 1077 && shipToSnap.getX() <= 1153) {
			shipToSnap.x = 1119;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.column = 9;
		}
		checkForShipOffGrid(shipToSnap);
		if (shipToSnap.getX() < 476 || shipToSnap.getX() > 1153) {
			System.out.println("Ship x: " + shipToSnap.getX());
			returnShipToStart(shipToSnap);
		}
	}
	
	public void snapShipToY(Ship shipToSnap) {
		if (shipToSnap.getY() >= 30 && shipToSnap.getY() <= 105) {
			shipToSnap.y = 76;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 0;
		}
		else if (shipToSnap.getY() > 105 && shipToSnap.getY() <= 163) {
			shipToSnap.y = 134;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 1;
		}
		else if (shipToSnap.getY() > 163 && shipToSnap.getY() <= 221) {
			shipToSnap.y = 192;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 2;
		}
		else if (shipToSnap.getY() > 221 && shipToSnap.getY() <= 279) {
			shipToSnap.y = 250;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 3;
		}
		else if (shipToSnap.getY() > 279 && shipToSnap.getY() <= 337) {
			shipToSnap.y = 308;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 4;
		}
		else if (shipToSnap.getY() > 337 && shipToSnap.getY() <= 395) {
			shipToSnap.y = 366;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 5;
		}
		else if (shipToSnap.getY() > 395 && shipToSnap.getY() <= 453) {
			shipToSnap.y = 424;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 6;
		}
		else if (shipToSnap.getY() > 453 && shipToSnap.getY() <= 511) {
			shipToSnap.y = 482;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 7;
		}
		else if (shipToSnap.getY() > 511 && shipToSnap.getY() <= 569) {
			shipToSnap.y = 540;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 8;
		}
		else if (shipToSnap.getY() > 569 && shipToSnap.getY() <= 615) {
			shipToSnap.y = 598;
			shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
			shipToSnap.row = 9;
		}
		checkForShipOffGrid(shipToSnap);
		if (shipToSnap.getY() < 30 || shipToSnap.getY() > 615) {
			returnShipToStart(shipToSnap);
		}
		checkForShipOverLap(shipToSnap);
	}
	
	public void checkForShipOffGrid(Ship shipToSnap) {
		if ((shipToSnap.getY() + shipToSnap.getHeight()) > 650) {
			returnShipToStart(shipToSnap);
		}
		else if ((shipToSnap.getX() + shipToSnap.getWidth()) > 1200) {
			returnShipToStart(shipToSnap);
		}
	}
	
	public void checkForShipOverLap(Ship shipToSnap) {
		if (shipToSnap.typeOfShip == ShipType.AircraftCarrier) {
			if (shipToSnap.getBounds().intersects(BattleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			if (shipToSnap.getBounds().intersects(PatrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
		else if (shipToSnap.typeOfShip == ShipType.Battleship) {
			if (shipToSnap.getBounds().intersects(AircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			if (shipToSnap.getBounds().intersects(PatrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
		else if (shipToSnap.typeOfShip == ShipType.Cruiser) {
			if (shipToSnap.getBounds().intersects(AircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(BattleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			if (shipToSnap.getBounds().intersects(PatrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
		else if (shipToSnap.typeOfShip == ShipType.Submarine) {
			if (shipToSnap.getBounds().intersects(AircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(BattleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			if (shipToSnap.getBounds().intersects(PatrolBoat.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
		else if (shipToSnap.typeOfShip == ShipType.PatrolBoat) {
			if (shipToSnap.getBounds().intersects(AircraftCarrier.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(BattleShip.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			else if (shipToSnap.getBounds().intersects(Cruiser.getBounds())) {
				returnShipToStart(shipToSnap);
			}
			if (shipToSnap.getBounds().intersects(Submarine.getBounds())) {
				returnShipToStart(shipToSnap);
			}
		}
	}
	
	public void returnShipToStart(Ship shipToSnap) {
		if (shipToSnap.typeOfShip == ShipType.AircraftCarrier) {
			shipToSnap.x = 0;
			shipToSnap.y = 1;
		}
		else if (shipToSnap.typeOfShip == ShipType.Battleship) {
			shipToSnap.x = 0;
			shipToSnap.y = 110;
		}
		else if (shipToSnap.typeOfShip == ShipType.Cruiser) {
			shipToSnap.x = 0;
			shipToSnap.y = 220;
		}
		else if (shipToSnap.typeOfShip == ShipType.Submarine) {
			shipToSnap.x = 0;
			shipToSnap.y = 330;
		}
		else if (shipToSnap.typeOfShip == ShipType.PatrolBoat) {
			shipToSnap.x = 0;
			shipToSnap.y = 432;
		}
		shipToSnap.setBounds(shipToSnap.x, shipToSnap.y, shipToSnap.width, shipToSnap.height);
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
		//setBounds();
		
		//sets grid locations relative to the screen instead of parent
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				grid[x][y].setLocation(grid[x][y].getLocationOnScreen().x, grid[x][y].getLocationOnScreen().y);
			}
		}
		

		setValues(AircraftCarrier);
		setValues(BattleShip);
		setValues(Cruiser);
		setValues(Submarine);
		setValues(PatrolBoat);

		System.out.println("Player 1 Ships");
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				System.out.print(player1Values[x][y]);
			}
			
			System.out.println("");
		}
		
		//Count the number of 1 values
		int count = 0;
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				if (player1Values[x][y] != 0) {
					count++;
				}
			}
		}
		
		//count is = to 17 if all ships are placed correctly
		if(count == 17) {
		
			GameBoard playingBoard = new GameBoard(mode);
			if (this.getTitle().contains("1") && mode == GameMode.OnePlayerMode) {
				playingBoard.player1Ships = player1Ships;
				playingBoard.player1Values = player1Values;
				setCpuValues();
				playingBoard.player2Values = player2Values;
				System.out.println("");
				System.out.println("Player 2 Ships");
				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 10; y++) {
						
						//System.out.print(value[x][y] + " ");
						System.out.print(player2Values[x][y]);
					}
					
					System.out.println("");
				}
				this.setVisible(false);
				playingBoard.setVisible(true);
				playingBoard.beginGame();
			}
			else if (this.getTitle().contains("1") && mode == GameMode.TwoPlayerPassAndPlay) {
				playingBoard.player1Ships = player1Ships;
				playingBoard.player1Values = board.values;
				this.setTitle("Player 2 Set Ships");
				this.getContentPane().removeAll();
				ShipSetup(mode, 2);
			}
			else {
				playingBoard.player2Ships = player1Ships;
				playingBoard.player2Values = board.values;
				this.setVisible(false);
				playingBoard.setVisible(true);
				playingBoard.beginGame();
			}
		} else {
			
			//if the ships aren't placed correctly
			board.setBackground(Color.RED);
			System.out.println("Ships placed Incorrectly");
			
		}
		
	}
	
	public void setValues(Ship shipToSet) {
		for (int s = 0; s < shipToSet.size; s++) {
			if (shipToSet.isHorizontal) {
				player1Values[shipToSet.row][shipToSet.column + s] = shipToSet.value;
			}
			else {
				player1Values[shipToSet.row + s][shipToSet.column] = shipToSet.value;
			}
		}
	}
	
	private void setCpuValues() {
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
					player2Values[randomX][randomY] = AircraftCarrier.value;
					player2Values[randomX + 1][randomY] = AircraftCarrier.value;
					player2Values[randomX + 2][randomY] = AircraftCarrier.value;
					player2Values[randomX + 3][randomY] = AircraftCarrier.value;
					player2Values[randomX + 4][randomY] = AircraftCarrier.value;
					aircraftCarrierSet = true;
				}
			}
			else if (shipVertical == false) {
				if (randomY > 5) continue; //Too far across the grid to place AC horizontally, try again
				else {	//Place the AC (again, don't need to check for other ships right now
					player2Values[randomX][randomY] = AircraftCarrier.value;
					player2Values[randomX][randomY + 1] = AircraftCarrier.value;
					player2Values[randomX][randomY + 2] = AircraftCarrier.value;
					player2Values[randomX][randomY + 3] = AircraftCarrier.value;
					player2Values[randomX][randomY + 4] = AircraftCarrier.value;
					aircraftCarrierSet = true;
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
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX + 1][randomY] == 0
							&& player2Values[randomX + 2][randomY] == 0 && player2Values[randomX + 3][randomY] == 0) {
						player2Values[randomX][randomY] = BattleShip.value;
						player2Values[randomX + 1][randomY] = BattleShip.value;
						player2Values[randomX + 2][randomY] = BattleShip.value;
						player2Values[randomX + 3][randomY] = BattleShip.value;
						battleShipSet = true;
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 6) continue; //Too far across the grid to place BS horizontally, try again
				else {	//Place the BS if there arent any ships in the way
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX][randomY  + 1] == 0
							&& player2Values[randomX][randomY  + 2] == 0 && player2Values[randomX][randomY + 3] == 0) {
						player2Values[randomX][randomY] = BattleShip.value;
						player2Values[randomX][randomY + 1] = BattleShip.value;
						player2Values[randomX][randomY + 2] = BattleShip.value;
						player2Values[randomX][randomY + 3] = BattleShip.value;
						battleShipSet = true;
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
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX + 1][randomY] == 0
							&& player2Values[randomX + 2][randomY] == 0) {
						player2Values[randomX][randomY] = Cruiser.value;
						player2Values[randomX + 1][randomY] = Cruiser.value;
						player2Values[randomX + 2][randomY] = Cruiser.value;
						cruiserSet = true;
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 7) continue; //Too far across the grid to place cruiser horizontally, try again
				else {	//Place the cruiser if there arent any ships in the way
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX][randomY + 1] == 0
							&& player2Values[randomX][randomY + 2] == 0) {
						player2Values[randomX][randomY] = Cruiser.value;
						player2Values[randomX][randomY + 1] = Cruiser.value;
						player2Values[randomX][randomY + 2] = Cruiser.value;
						cruiserSet = true;
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
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX + 1][randomY] == 0
							&& player2Values[randomX + 2][randomY] == 0) {
						player2Values[randomX][randomY] = Submarine.value;
						player2Values[randomX + 1][randomY] = Submarine.value;
						player2Values[randomX + 2][randomY] = Submarine.value;
						submarineSet = true;
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 7) continue; //Too far across the grid to place sub horizontally, try again
				else {	//Place the sub if there arent any ships in the way
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX][randomY + 1] == 0
							&& player2Values[randomX][randomY + 2] == 0) {
						player2Values[randomX][randomY] = Submarine.value;
						player2Values[randomX][randomY + 1] = Submarine.value;
						player2Values[randomX][randomY + 2] = Submarine.value;
						submarineSet = true;
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
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX + 1][randomY] == 0) {
						player2Values[randomX][randomY] = PatrolBoat.value;
						player2Values[randomX + 1][randomY] = PatrolBoat.value;
						patrolBoatSet = true;
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
			else if (shipVertical == false) {
				if (randomY > 8) continue; //Too far across the grid to place PB horizontally, try again
				else {	//Place the PB if there arent any ships in the way
					if (player2Values[randomX][randomY] == 0 && player2Values[randomX][randomY + 1] == 0) {
						player2Values[randomX][randomY] = PatrolBoat.value;
						player2Values[randomX][randomY + 1] = PatrolBoat.value;
						patrolBoatSet = true;
					}
					else continue; //there is a ship in the way of this one, try a new location
				}
			}
		}
				
	}
	
}
