package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * 
 */
public class Grid extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static JLabel[][] labelGrid = new JLabel[10][10];
	/**
	 * 
	 */
	
	private static JButton[][] buttonGrid = new JButton[10][10];
	/**
	 * 
	 */
	private int[][] values = new int[10][10];
	/**
	 * @return int[][]
	 */
	public int[][] getValues() {
		return values;
	}
	/**
	 * @param newValues array of grid values
	 */
	public void setValues(final int[][] newValues) {
		values = newValues;
	}
	/**
	 * @param boardSize the size of the grid to be made
	 * @param type type of grid to make
	 */
	public Grid(final int boardSize, final String type) {
		
		GridLayout boardLayout = 
				new GridLayout(boardSize + 1, boardSize + 1);

		//create board
		this.setLayout(boardLayout);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(new JLabel());

		//create border
		Border boardBorder = 
				BorderFactory.createLineBorder(Color.BLACK);

		for (int x = 1; x < boardSize + 1; x++) {
			JLabel letterLabel = 
				new JLabel(letterFromNumber(x),
						SwingConstants.CENTER);
			this.add(letterLabel, new Integer(0));
		}

		for (int x = 0; x < boardLayout.getColumns() - 1; x++) {
			this.add(
			 new JLabel("" + (x + 1), SwingConstants.CENTER));
			for (int y = 0; y < boardLayout.getRows() - 1; y++) {
				if (type.equals("Label")) {
					JLabel box = new JLabel();
					box.setBackground(Color.WHITE);
					box.setOpaque(true);
					box.setText(x + "," + y);
					box.setHorizontalAlignment(
							SwingConstants.CENTER);
					box.setForeground(Color.WHITE);
					box.setBorder(boardBorder);
					
					this.add(box, new Integer(0));
					//this.add(box);
					Grid.labelGrid[x][y] = box;
				} else if (type.equals("Button")) {
					JButton box = new JButton();
					//box.setText(x + "," + y);
					box.setHorizontalAlignment(
							SwingConstants.CENTER);
					 try {
				      Image img = 
					  ImageIO.read(
						new File("res\\waves.png"));
					  box.setIcon(new ImageIcon(img));
						  } catch (Exception ex) {
						    System.out.println(ex);
						  }
					box.setBorder(boardBorder);
					this.add(box, new Integer(0));
					Grid.buttonGrid[x][y] = box;
				}
				this.values[x][y] = 0;

				//JButton button = new JButton("");
				//button.setEnabled(false);
				//button.setBackground(Color.DARK_GRAY);
				//board.add(button, new Integer(0));

			}
		}
		

	}
	/**
	 * @return JLabel[][]
	 */
	public static JLabel[][] getLabelGrid() {		
		return labelGrid;		
	}
	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return JLabel
	 */
	public static JLabel getLabel(final int x, final int y) {
		return labelGrid[x][y];
	}
	/**
	 * @return JButton[][]
	 */
	public static JButton[][] getButtonGrid() {
		return buttonGrid;
	}
	/**
	 * @param i number corresponding to letter
	 * @return String
	 */
	private String letterFromNumber(final int i) {
		if (i > 0 && i < 27) {
			return String.valueOf((char) (i + 64));
		} else {
			return null;
		}
	}
}
