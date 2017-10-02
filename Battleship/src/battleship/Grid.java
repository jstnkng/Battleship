package battleship;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Grid extends JPanel{
	
	public static JLabel[][] labelGrid = new JLabel[10][10];
	
	public static JButton[][] buttonGrid = new JButton[10][10];
	
	public int[][] values = new int[10][10];

	public Grid (int boardSize, String type){
		
		GridLayout boardLayout = new GridLayout(boardSize + 1, boardSize + 1);

		//create board
		this.setLayout(boardLayout);
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.add(new JLabel());

		//create border
		Border boardBorder = BorderFactory.createLineBorder(Color.BLACK);

		for (int x = 1; x < boardSize + 1; x++ ) {
			JLabel letterLabel = new JLabel(Letter(x), SwingConstants.CENTER);
			this.add(letterLabel, new Integer(0));
		}

		for (int x = 0; x < boardLayout.getColumns()-1; x ++) {
			this.add(new JLabel("" + (x + 1), SwingConstants.CENTER));
			for (int y = 0; y < boardLayout.getRows()-1; y ++) {

				if (type == "Label") {					
					JLabel box = new JLabel();
					box.setBackground(Color.LIGHT_GRAY);
					box.setOpaque(true);
					box.setText(x + "," + y);
					box.setHorizontalAlignment(SwingConstants.CENTER);
					box.setForeground(Color.BLUE);
					box.setBorder(boardBorder);
					this.add(box, new Integer(0));
					this.labelGrid[x][y] = box;
				}
				else if (type == "Button"){
					JButton box = new JButton();
					//box.setText(x + "," + y);
					box.setHorizontalAlignment(SwingConstants.CENTER);
					box.setForeground(Color.BLUE);
					box.setBorder(boardBorder);
					this.add(box, new Integer(0));
					this.buttonGrid[x][y] = box;
				}
				this.values[x][y] = 0;

				//JButton button = new JButton("");
				//button.setEnabled(false);
				//button.setBackground(Color.DARK_GRAY);
				//board.add(button, new Integer(0));

			}
		}
		
		this.setBackground(Color.CYAN);

	}
	
	public static JLabel[][] getLabelGrid(){		
		return labelGrid;		
	}
	
	public static JButton[][] getButtonGrid(){
		return buttonGrid;
	}
	
	public int[][] getValues(){
		return values;
	}
	
	private String Letter(int i) {
		return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}
}
