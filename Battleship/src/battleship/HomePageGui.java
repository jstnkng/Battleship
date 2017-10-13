package battleship;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Creates the home screen where
 * a player chooses what game mode
 * to play.
 */
public class HomePageGui extends JFrame {

	/**
	 * ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates a panel for the contentPane.
	 */
	private JPanel contentPane;

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
					frame.setSize(1200,700);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbcContentPane = new GridBagLayout();
		gbcContentPane.columnWidths = new int[]{0, 0, 0};
		gbcContentPane.rowHeights = new int[]{0, 0, 0};
		gbcContentPane.columnWeights = new double[]
				{0.0, 1.0, Double.MIN_VALUE};
		gbcContentPane.rowWeights = new double[]
				{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbcContentPane);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setFont(new Font("Tahoma", Font.BOLD, 100));
		GridBagConstraints gbcLblBattleship = new GridBagConstraints();
		gbcLblBattleship.insets = new Insets(0, 0, 5, 0);
		gbcLblBattleship.gridx = 1;
		gbcLblBattleship.gridy = 0;
		gbcLblBattleship.weightx = 1;
		gbcLblBattleship.weighty = .33;
		contentPane.add(lblBattleship, gbcLblBattleship);
		
		JButton btnPassPlay = new JButton("Pass & Play");
		btnPassPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				ShipSetupFrame p1 = new ShipSetupFrame(
					GameMode.TwoPlayerPassAndPlay, 1);
				p1.setLocationRelativeTo(null);
				p1.setVisible(true);
			}
		});
		
		GridBagConstraints gbBtnPassPlay = new GridBagConstraints();
		gbBtnPassPlay.insets = new Insets(50,350,5,350);
		gbBtnPassPlay.fill = gbBtnPassPlay.BOTH;
		gbBtnPassPlay.gridx = 1;
		gbBtnPassPlay.gridy = 2;
		gbBtnPassPlay.weightx = 1;
		gbBtnPassPlay.weighty = .33;
		contentPane.add(btnPassPlay, gbBtnPassPlay);
		
		JButton btnOnePlayer = new JButton("Single Player");
		btnOnePlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				ShipSetupFrame p1 = new ShipSetupFrame(
						GameMode.OnePlayerMode, 0);
				p1.setLocationRelativeTo(null);
				p1.setVisible(true);
			}
			
		});
		GridBagConstraints gbcBtnOnePlayer = new GridBagConstraints();
		gbcBtnOnePlayer.insets = new Insets(50,350,50,350);
		gbcBtnOnePlayer.fill = gbcBtnOnePlayer.BOTH;
		gbcBtnOnePlayer.gridx = 1;
		gbcBtnOnePlayer.gridy = 3;
		gbcBtnOnePlayer.weightx = 1;
		gbcBtnOnePlayer.weighty = .33;
		contentPane.add(btnOnePlayer, gbcBtnOnePlayer);
	}

}
