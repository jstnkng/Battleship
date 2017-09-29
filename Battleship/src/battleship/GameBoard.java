package battleship;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameBoard extends JFrame {

//	private JPanel content = new JPanel();
	private Grid player1Board = new Grid(10);
	private Grid player2Board = new Grid(10);
	
	public GameBoard(GameMode mode, int[][] values, Ship[] Ships) {
		//Boats[0] is AircraftCarrier
		//Boats[1] is Battleship
		//Boats[2] is Cruiser
		//Boats[3] is PatrolBoat
		//Boats[4] is Submarine
		this.setSize(1200,700);
		this.setLayout(new GridLayout(0,2));
		if (mode == GameMode.OnePlayerMode) {
			this.add(player1Board);
			this.add(player2Board);
		}
		//this.add(content);
		
	}
}
