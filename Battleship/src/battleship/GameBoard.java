package battleship;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class GameBoard extends JFrame {
	//can you see me now
	
	public GameBoard(GameMode mode, int[][] values, BoatInformation[] Boats) {
		
		this.setLayout(new GridLayout(0,2));
		
	}
}
