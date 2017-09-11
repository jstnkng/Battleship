package battleship;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class HomePageGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageGUI window = new HomePageGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleship.setFont(new Font("Tahoma", Font.PLAIN, 33));
		frame.getContentPane().add(lblBattleship, BorderLayout.NORTH);
		
		JButton btnPlay = new JButton("Play");
		frame.getContentPane().add(btnPlay, BorderLayout.CENTER);
	}

}
