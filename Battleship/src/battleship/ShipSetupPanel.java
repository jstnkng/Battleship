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
	
	private JLabel[][] grid = new JLabel[boardSize][boardSize];
	
	private Ship[] Ships = new Ship[5];
	
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
		//AircraftCarrier = createAircraftCarrier();
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		board.setBackground(Color.CYAN);
	}
	
	//action on submit press
	public void submit() {
		board.setBackground(Color.GREEN);
		
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				
				if(grid[x][y].getBounds().intersects(AircraftCarrier.getBounds())) {
					board.value[x][y] = 1;
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
		
		GameBoard playingBoard = new GameBoard(GameMode.OnePlayerMode, board.value, Ships );
		playingBoard.setVisible(true);
		
	}
	

	
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
