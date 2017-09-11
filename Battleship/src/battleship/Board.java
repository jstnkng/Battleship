package battleship;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Board extends JFrame {

	private JPanel contentPane;
	private JPanel Player1Board;
	private JPanel Player2Board;
	/**
	 * Create the frame.
	 */
	public Board(int size) {
		GridLayout boardLayout = new GridLayout(size + 1, size + 1);
		GridLayout mainLayout = new GridLayout(0,2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setLayout(mainLayout);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		Player1Board = new JPanel();
		Player1Board.setLayout(boardLayout);
		Player1Board.setBorder(new EmptyBorder(10,10,10,10));
		Player1Board.add(new JLabel());

		for (int x = 1; x < size + 1; x++ ) {
			JLabel letterButton = new JLabel(Letter(x), SwingConstants.CENTER);
			Player1Board.add(letterButton);
		}
		
		for (int x = 0; x < boardLayout.getColumns()-1; x ++) {
			Player1Board.add(new JLabel("" + (x + 1), SwingConstants.CENTER));
			for (int y = 0; y < boardLayout.getRows()-1; y ++) {
				Player1Board.add(new JButton(""));
			}
		}
		
		
		Player2Board = new JPanel();
		Player2Board.setLayout(boardLayout);
		Player2Board.setBorder(new EmptyBorder(10,10,10,10));
		Player2Board.add(new JLabel());

		for (int x = 1; x < size + 1; x++ ) {
			JLabel letterButton = new JLabel(Letter(x), SwingConstants.CENTER);
			Player2Board.add(letterButton);
		}
		
		for (int x = 0; x < boardLayout.getColumns()-1; x ++) {
			Player2Board.add(new JLabel("" + (x + 1), SwingConstants.CENTER));
			for (int y = 0; y < boardLayout.getRows()-1; y ++) {
				Player2Board.add(new JButton(""));
			}
		}
		
		contentPane.add(Player1Board);
		contentPane.add(Player2Board);

		setContentPane(contentPane);
	}
	
	//Get letter from number
	private String Letter(int i) {
	    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}

}
