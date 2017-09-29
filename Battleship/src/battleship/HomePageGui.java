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
import java.io.IOException;

public class HomePageGui extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setFont(new Font("Tahoma", Font.BOLD, 30));
		GridBagConstraints gbc_lblBattleship = new GridBagConstraints();
		gbc_lblBattleship.insets = new Insets(0, 0, 5, 0);
		gbc_lblBattleship.gridx = 1;
		gbc_lblBattleship.gridy = 0;
		contentPane.add(lblBattleship, gbc_lblBattleship);
		
		JButton btnPassPlay = new JButton("Pass & Play");
		btnPassPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ShipSetupFrame p2 = new ShipSetupFrame(GameMode.TwoPlayerPassAndPlay, 2);
				ShipSetupFrame p1 = new ShipSetupFrame(GameMode.TwoPlayerPassAndPlay, 1);						
			}
		});
		
		GridBagConstraints gbc_btnPassPlay = new GridBagConstraints();
		gbc_btnPassPlay.gridx = 1;
		gbc_btnPassPlay.gridy = 1;
		contentPane.add(btnPassPlay, gbc_btnPassPlay);
		
		JButton btnOnePlayer = new JButton("One Player");
		btnOnePlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShipSetupFrame p1 = new ShipSetupFrame(GameMode.OnePlayerMode, 0);						
			}
		});
		GridBagConstraints gbc_btnOnePlayer = new GridBagConstraints();
		gbc_btnOnePlayer.gridx = 1;
		gbc_btnOnePlayer.gridy = 2;
		contentPane.add(btnOnePlayer, gbc_btnOnePlayer);
	}

}
