package battleship;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Creates the home screen where
 * a player chooses what game mode
 * to play.
 */
public class HomePageGui extends JFrame implements MouseListener, 
	ActionListener {

	/**
	 * ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates button for one player mode.
	 */
	private JButton btnOnePlayer;
	/**
	 * Creates button for multiplayer.
	 */
	private JButton btnMultiplayer;
	/**
	 * Creates button for pass and play mode.
	 */
	private JButton btnPassPlay;
	/**
	 * Current difficulty being played.
	 */
	private Difficulty diffChoice;
	/**
	 * Creates a JRadioButton for easy difficulty.
	 */
	private JRadioButton easyButton;
	/**
	 * Creates a JRadioButton for normal difficulty.
	 */
	private JRadioButton normalButton;
	/**
	 * Creates a JRadioButton for easy difficulty.
	 */
	private JRadioButton hardButton;
	
	/**
	 * Launch the application.
	 * @param args command line argument
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageGui frame = new HomePageGui();
					frame.setTitle("Play BattleShip!");
					frame.setSize(1200, 700);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePageGui() {
		//setup frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbcContentPane = new GridBagLayout();
		
		//get image for background
		Image image = getBackgroundImage();
		JLabel background = new JLabel(new ImageIcon(image));
		add(background);
		background.setLayout(gbcContentPane);
		setContentPane(background);
		
		//create label for title
		JLabel lblBattleship = new JLabel("BATTLESHIP");
		lblBattleship.setFont(new Font("Impact", 
				Font.CENTER_BASELINE, 110));
		lblBattleship.setForeground(Color.BLACK);
		
		//add label to panel and Grid bag layout
		addItem(background, lblBattleship, 0, .2, 
				GridBagConstraints.CENTER);
				
		//create button for pass and play
		btnPassPlay = new JButton("Pass & Play");
		btnPassPlay.setBackground(Color.LIGHT_GRAY);
		btnPassPlay.setForeground(Color.BLACK);
		btnPassPlay.addMouseListener(this);
		btnPassPlay.setPreferredSize(new Dimension(400, 50));
		
		btnPassPlay.setBorder(BorderFactory.createLineBorder(
				Color.BLACK));
		
		//Changes button border on mouse hover
		btnPassPlay.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e) {
				if (btnPassPlay.getModel().isRollover()) {
					btnPassPlay.setBorder(
						BorderFactory.
						createEtchedBorder());
				} else {
					btnPassPlay.setBorder(
						BorderFactory.
						createLineBorder(Color.BLACK));
				}
			}
		});
		
		//add pass & play button to panel and Grid bag layout
		addItem(background, btnPassPlay, 1, .1, 
				GridBagConstraints.CENTER);
		
		//create button for single player
		btnOnePlayer = new JButton("Single Player");
		btnOnePlayer.setBackground(Color.LIGHT_GRAY);
		btnOnePlayer.setForeground(Color.BLACK);
		btnOnePlayer.setPreferredSize(new Dimension(400, 50));
		
		btnOnePlayer.setBorder(BorderFactory.createLineBorder(
				Color.BLACK));
		
		//Changes button border on mouse hover
		btnOnePlayer.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e) {
				if (btnOnePlayer.getModel().isRollover()) {
					btnOnePlayer.setBorder(
						BorderFactory.
						createEtchedBorder());
				} else {
					btnOnePlayer.setBorder(
						BorderFactory.
						createLineBorder(Color.BLACK));
				}
			}
		});
		
		btnOnePlayer.addMouseListener(this);
		
		
		//add player button to panel and Grid bag layout
		addItem(background, btnOnePlayer, 3, .1, 
				GridBagConstraints.CENTER);
		
		//create button for multi player
		btnMultiplayer = new JButton("Multiplayer");
		btnMultiplayer.setBackground(Color.LIGHT_GRAY);
		btnMultiplayer.setForeground(Color.BLACK);
		btnMultiplayer.setPreferredSize(new Dimension(400, 50));

		btnMultiplayer.setBorder(BorderFactory.createLineBorder(
				Color.BLACK));

		//Changes button border on mouse hover
		btnMultiplayer.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e) {
				if (btnMultiplayer.getModel().isRollover()) {
					btnMultiplayer.setBorder(
							BorderFactory.
							createEtchedBorder());
				} else {
					btnMultiplayer.setBorder(
							BorderFactory.
							createLineBorder(Color.BLACK));
				}
			}
		});

		btnMultiplayer.addMouseListener(this);


		//add multi player player button to panel and Grid bag layout
		addItem(background, btnMultiplayer, 2, .1, 
				GridBagConstraints.CENTER);
		
		//create strings for radio buttons
		String easy = "Easy";
		String normal = "Normal";
		String hard = "Hard";
		
		//Create the radio buttons.
	    easyButton = new JRadioButton(easy);
	    easyButton.setMnemonic(KeyEvent.VK_B);
	    easyButton.setActionCommand(easy);
	    easyButton.setSelected(true);
	    easyButton.setOpaque(false);
	    easyButton.setForeground(Color.BLACK);
	    
	    normalButton = new JRadioButton(normal);
	    normalButton.setMnemonic(KeyEvent.VK_B);
	    normalButton.setActionCommand(normal);
	    normalButton.setOpaque(false);
	    normalButton.setForeground(Color.BLACK);
	    
	    hardButton = new JRadioButton(hard);
	    hardButton.setMnemonic(KeyEvent.VK_B);
	    hardButton.setActionCommand(hard);
	    hardButton.setOpaque(false);
	    hardButton.setForeground(Color.BLACK);
	    
	    //Group the buttons
	    Box difficultyBox = Box.createHorizontalBox();
	    ButtonGroup difficulty = new ButtonGroup();
	    difficulty.add(easyButton);
	    difficulty.add(normalButton);
	    difficulty.add(hardButton);
	    difficultyBox.add(Box.createVerticalStrut(5));
	    difficultyBox.add(easyButton);
	    difficultyBox.add(Box.createHorizontalStrut(50));
	    difficultyBox.add(normalButton);
	    difficultyBox.add(Box.createHorizontalStrut(50));
	    difficultyBox.add(hardButton);
	    Border diff = BorderFactory.createLineBorder(Color.BLACK);
	    difficultyBox.setBorder(BorderFactory.createTitledBorder(diff,
	    		"Difficulty", TitledBorder.LEFT, 
	    		TitledBorder.DEFAULT_POSITION, easyButton.getFont(),
	    		Color.BLACK));
	    
	    //add group to panel and Grid bag layout
	    addItem(background, difficultyBox, 4, .1, 
	    		GridBagConstraints.CENTER);
	    
	    //add action listeners
	    easyButton.addActionListener(this);
	    normalButton.addActionListener(this);
	    hardButton.addActionListener(this);
	    
	    //defaults difficulty to easy
	    //just like the radio buttons
	    diffChoice = Difficulty.Easy;
	    
	    //create blank JPanel to adjust layout
	    JPanel blank = new JPanel();
	    blank.setOpaque(false);
	    
	    //add blank to panel and Grid bag layout
	    addItem(background, blank, 5, .856, 
	    		GridBagConstraints.BOTH);
	    		
	}
	
	/**
	 * Used to add the item to the frame
	 * and set its location within the
	 * grid bag layout.
	 * 
	 * @param label The background to add the component to
	 * @param c The component to add
	 * @param gy The vertical grid location
	 * @param weighty The vertical weight
	 * @param fill The fill of its area
	 */
	public void addItem(final JLabel label, final JComponent c,
			final int gy, 
			final double weighty, final int fill) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = fill;
		gbc.gridx = 1;
		gbc.gridy = gy;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = weighty;
		label.add(c, gbc);
	}
	
	/**
	 * Gets the image used for the background.
	 * @return Image for the background
	 */
	public Image getBackgroundImage() {
		
		try {
			//gets image from file
			BufferedImage image = ImageIO.read(
					new File("res/background1.jpg"));
			
			//scales the image to panel size
			Image scaledImage = image.getScaledInstance(
				1200, 700, Image.SCALE_SMOOTH);
			
			return scaledImage;
			
		} catch (IOException ex) {
			System.err.println("background image not found");
			//handle the exception
		}
		
		return null;
		
	}
	
	/**
	 * Creates ShipSetupFrame for one player game.
	 * @return ShipSetupFrame
	 */
	public ShipSetupFrame startOnePlayer() {
		ShipSetupFrame p1 = new 
			ShipSetupFrame(GameMode.OnePlayerMode, 1, diffChoice);
		p1.setLocationRelativeTo(null);
		p1.setVisible(true);
		System.out.println("Single Player");
		System.out.println("x: " + btnOnePlayer.getLocationOnScreen().x 
			+ " y: " + btnOnePlayer.getLocationOnScreen().y);
		
		return p1;
	}
	
	/**
	 * Creates ShipSetupFrame for pass and play game.
	 * 
	 */
	public void startPassAndPlay() {	
		ShipSetupFrame p1 = new 
			ShipSetupFrame(GameMode.TwoPlayerPassAndPlay, 1, 
					diffChoice);
		p1.setLocationRelativeTo(null);	
//		GameBoard player1Board = p1.getGameBoard();
//		ShipSetupFrame p2 = new 
//				ShipSetupFrame(GameMode.TwoPlayerPassAndPlay, 2, 
//						diffChoice);
//			p2.setLocationRelativeTo(null);
//			p2.setVisible(true);
//		GameBoard player1Board = new GameBoard();
//		player1Board.setPlayer1Values(p1.getPlayer1Values());
//		GameBoard player2Board = new GameBoard();
//		player2Board.setPlayer1Values(p2.getPlayer1Values());
//		player1Board.setPlayer2Values(player2Board.getPlayer1Values());
//		player2Board.setPlayer2Values(player1Board.getPlayer1Values());
		
//		GameBoardConnector connector = new GameBoardConnector(player1Board, player2Board);
//		connector.BeginGame();
		System.out.println("Pass & Play");
		System.out.println("x: " + btnPassPlay.getLocationOnScreen().x 
			+ " y: " + btnPassPlay.getLocationOnScreen().y);
		
		
	}
	
	/**
	 * Creates multiplayer menu for multiplayer game.
	 * 
	 */
	public void startMultiplayer() {

		int portNum = 5445;
		//aws ip = 13.58.209.10
		String serverAdd = "13.58.209.10";
		MultiplayerMenu gui = new MultiplayerMenu();
		gui.setLocationRelativeTo(null);
		
		//create client
		Client client = new Client(serverAdd, portNum, gui);
		
		gui.start(client);
		
		if (!client.start()) {
			return;
		}
		
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		if (e.getSource() == btnOnePlayer) {
			startOnePlayer();
		}
		if (e.getSource() == btnPassPlay) {
			startPassAndPlay();
		}
		if (e.getSource() == btnMultiplayer) {
			startMultiplayer();
		}
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == easyButton) {
			diffChoice = Difficulty.Easy;
		}
		if (e.getSource() == normalButton) {
			diffChoice = Difficulty.Normal;
		}
		if (e.getSource() == hardButton) {
			diffChoice = Difficulty.Hard;
		}
		
	}

	@Override
	public void mouseEntered(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(final MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(final MouseEvent e) { 
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(final MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	

}
