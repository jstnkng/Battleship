package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ShipSetupPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	private int ACX, ACY;
	private int ACWidth, ACHeight;
	private int BSX, BSY;
	private int BSWidth, BSHeight;
	private int mx, my;
	private int dragFromX, dragFromY;
	private boolean ACcanDrag = false;
	private boolean BScanDrag = false;
	
	private int boardSize = 10;
	
	private JLayeredPane layeredPane;
	private JPanel board;
	
	private Ship AircraftCarrier;
	private Ship BattleShip;
	private boolean flip = true;
	
	private JButton submit;
	
	private JLabel[][] grid = new JLabel[boardSize][boardSize];
	private int[][] value = new int[boardSize][boardSize];
	
	
	public ShipSetupPanel(){
		
		//layout of panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		//make grid layout for layeredPane
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//create layeredPane
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(gridbag);
		
		//create first ship
		AircraftCarrier = createAircraftCarrier();
		
		//add first ship to layered pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .4;
		c.weighty = .33;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(AircraftCarrier, c, 1);
		
	
		//create second ship
		BattleShip = createBattleShip();
		
		//add second ship to layered pane
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .4;
		c.weighty = .33;
		c.fill = GridBagConstraints.BOTH;
		layeredPane.add(BattleShip, c, 1);
		
		//create and add submit button
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
		
		
		//create board
		//board = createBoard();
		board = new Grid(boardSize);
		grid = Grid.getGrid();
		
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
		
		//add Mouse Listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		
		if(ACcanDrag) {
			ACX = mx - dragFromX;
			ACY = my - dragFromY;
			
			AircraftCarrier.setLocation(ACX, ACY);
		}
		
		if(BScanDrag) {
			BSX = mx - dragFromX;
			BSY = my - dragFromY;
			
			BattleShip.setLocation(BSX, BSY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (mx >= ACX && mx <= (ACX + ACWidth) && my >= ACY && my <= (ACY + ACHeight)) {
			AircraftCarrier.rotate(flip);
			AircraftCarrier.setSize(ACHeight, ACWidth);
			int temp = ACHeight;
			ACHeight = ACWidth;
			ACWidth = temp;
			
			if (flip == true) {
				flip = false;
			}else {
				flip = true;
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println(shipLabel.getBounds());
		
		mx = e.getX();
		my = e.getY();
		
		//Location for BattleShip
		BSX = BattleShip.getLocationOnScreen().x;
		BSY = BattleShip.getLocationOnScreen().y;
		
		if (mx >= ACX && mx <= (ACX + BSWidth) && my >= ACY && my <= (ACY + ACHeight)) {
			ACcanDrag = true;
			dragFromX = mx - ACX;
			dragFromY = my - ACY;
			
			
		}else {
			ACcanDrag = false;
		}
		
		if (mx >= BSX && mx <= (BSX + BSWidth) && my >= BSY && my <= (BSY + BSHeight)) {
			BScanDrag = true;
			dragFromX = mx - BSX;
			dragFromY = my - BSY;
		}else {
			BScanDrag = false;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		board.setBackground(Color.CYAN);
	}
	
	//action on submit press
	public void submit() {
		board.setBackground(Color.GREEN);
		
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				
				if(grid[x][y].getBounds().intersects(AircraftCarrier.getBounds())) {
					value[x][y] = 1;
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
		
		
	}
	
	//Make Aircraft Carrier
	private Ship createAircraftCarrier() {
		
		String pathH = "C:\\Users\\jstnd\\git\\Battleship\\Battleship\\src\\battleship\\images\\battleships\\AC_H.png";
		String pathV = "C:\\Users\\jstnd\\git\\Battleship\\Battleship\\src\\battleship\\images\\battleships\\AC_V.png";
		
		//first ship info
		ACX = 0;
		ACY = 0;
		ACWidth = 350;
		ACHeight = 50;
		
		BoatInformation AircraftInfo = new BoatInformation(ACX, ACY, ACWidth, ACHeight, pathH);
		
		//create first ship as paintComponent
		Ship AC = new Ship(pathH, pathV);
		AC.setSize(ACWidth, ACHeight);
		
		return AC;
	}
	
	//Make Battleship
	private Ship createBattleShip() {
		
		String pathH = "C:\\Users\\jstnd\\git\\Battleship\\Battleship\\src\\battleship\\images\\battleships\\BS_H.png";
		String pathV = "C:\\Users\\jstnd\\git\\Battleship\\Battleship\\src\\battleship\\images\\battleships\\BS_V.png";
		
		//second ship info
		BSX = 0;
		BSY = 100;
		BSWidth = 300;
		BSHeight = 50;
		
		BoatInformation BatteshipInfo = new BoatInformation(BSX, BSY, BSWidth, BSHeight, pathH);

		//create second ship as paint component
		Ship BS = new Ship(pathH, pathV);
		BS.setSize(BSWidth, BSHeight);
		
		return BS;

	}
	

	//Make the Board
//	private JPanel createBoard() {
//
//		//make grid layout for board panel
//		GridLayout boardLayout = new GridLayout(boardSize + 1, boardSize + 1);
//
//		//create board
//		JPanel gameBoard = new JPanel();
//		gameBoard.setLayout(boardLayout);
//		gameBoard.setBorder(new EmptyBorder(10,10,10,10));
//		gameBoard.add(new JLabel());
//
//		//create border
//		Border boardBorder = BorderFactory.createLineBorder(Color.BLACK);
//
//		for (int x = 1; x < boardSize + 1; x++ ) {
//			JLabel letterButton = new JLabel(Letter(x), SwingConstants.CENTER);
//			gameBoard.add(letterButton, new Integer(0));
//		}
//
//		for (int x = 0; x < boardLayout.getColumns()-1; x ++) {
//			gameBoard.add(new JLabel("" + (x + 1), SwingConstants.CENTER));
//			for (int y = 0; y < boardLayout.getRows()-1; y ++) {
//
//				JLabel box = new JLabel();
//				box.setBackground(Color.LIGHT_GRAY);
//				box.setOpaque(true);
//				box.setText(x + "," + y);
//				box.setHorizontalAlignment(SwingConstants.CENTER);
//				box.setForeground(Color.BLUE);
//				box.setBorder(boardBorder);
//				gameBoard.add(box, new Integer(0));
//
//				grid[x][y] = box;
//				value[x][y] = 0;
//
//				//JButton button = new JButton("");
//				//button.setEnabled(false);
//				//button.setBackground(Color.DARK_GRAY);
//				//board.add(button, new Integer(0));
//
//			}
//		}
//
//
//		gameBoard.setBackground(Color.CYAN);
//
//		return gameBoard;
//	}
	
	//Get letter from number



	//Create the Frame
	public static void main(String[] args) {
		//Create a new JFrame
		JFrame frame = new JFrame("Ship Setup");
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(1200, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//Create the content pane
		JComponent contentPane = new ShipSetupPanel();
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);

		//Display
		//frame.pack();
		frame.setVisible(true);
	}

	
}
