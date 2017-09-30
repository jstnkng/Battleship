package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JFrame {

//	private JPanel content = new JPanel();
	private Grid player1Board;
	private Grid player2Board;
	public int[][] player1Values = new int[10][10];
	public int [][] player2Values = new int[10][10];
	public Ship[] player1Ships = new Ship[5];
	public Ship[] player2Ships = new Ship[5];
	
	
	//Two player constructor
	public GameBoard(GameMode mode) {
		//Boats[0] is AircraftCarrier
		//Boats[1] is Battleship
		//Boats[2] is Cruiser
		//Boats[3] is PatrolBoat
		//Boats[4] is Submarine
		this.setSize(1200,700);
		this.setLayout(new GridLayout(0,2));
	}
	
	public void beginGame() {
		player1Board = new Grid(10, "Label");
		player1Board.values = player1Values;
		int x2 = 0;
		int y2 = 0;
		for (JLabel[] row  : player1Board.labelGrid) {
			for (JLabel label : row) {
				label.setText(player1Values[y2][x2] + "");
				x2 ++;
			}
			y2 ++;
			x2 = 0;
		}		
		this.add(player1Board);
		
		player2Board = new Grid(10, "Button");
		player2Board.values = player2Values;
		int x = 0;
		int y = 0;
		for (JButton[] row  : player2Board.buttonGrid) {
			for (JButton button : row) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setText(button.getName());
						button.setBackground(Color.GRAY);
						
					}
				});
				button.setName(player2Values[y][x] + "");
				if (player2Values[y][x] == 1) {
					button.setForeground(Color.RED);
				}
				else button.setForeground(Color.black);
				x ++;
			}
			y ++;
			x = 0;
		}
		this.add(player2Board);
	}
	
}
