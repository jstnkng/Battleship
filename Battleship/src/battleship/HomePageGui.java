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
		lblBattleship.setFont(new Font("Tahoma", Font.BOLD, 30));
		GridBagConstraints gbcLblBattleship = new GridBagConstraints();
		gbcLblBattleship.insets = new Insets(0, 0, 5, 0);
		gbcLblBattleship.gridx = 1;
		gbcLblBattleship.gridy = 0;
		contentPane.add(lblBattleship, gbcLblBattleship);
		
		JButton btnPassPlay = new JButton("Pass & Play");
		btnPassPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				ShipSetupFrame p1 = new ShipSetupFrame(
					GameMode.TwoPlayerPassAndPlay, 1);
				p1.setVisible(true);
			}
		});
		
		GridBagConstraints gbBtnPassPlay = new GridBagConstraints();
		gbBtnPassPlay.gridx = 1;
		gbBtnPassPlay.gridy = 1;
		contentPane.add(btnPassPlay, gbBtnPassPlay);
		
		JButton btnOnePlayer = new JButton("One Player");
		btnOnePlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				ShipSetupFrame p1 = new ShipSetupFrame(
						GameMode.OnePlayerMode, 0);
				p1.setVisible(true);
			}
			
		});
		GridBagConstraints gbcBtnOnePlayer = new GridBagConstraints();
		gbcBtnOnePlayer.gridx = 1;
		gbcBtnOnePlayer.gridy = 2;
		contentPane.add(btnOnePlayer, gbcBtnOnePlayer);
	}

}
