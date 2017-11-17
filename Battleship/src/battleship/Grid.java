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
 * Creates a grid of JButtons that make up a board.
 */
public class Grid extends JPanel {
	
	public GridLayout boardLayout;
	public Border boardBorder;
	
	public Grid() {
		boardLayout = new GridLayout(11,11);

		//create board
		this.setLayout(boardLayout);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(new JLabel());

		//create border
		boardBorder = 
				BorderFactory.createLineBorder(Color.BLACK);

		for (int x = 1; x < 11; x++) {
			JLabel letterLabel = 
				new JLabel(letterFromNumber(x),
						SwingConstants.CENTER);
			this.add(letterLabel, new Integer(0));
		}
	}
	/**
	 * ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates a grid of labels that is 10 by 10.
	 */
	private static JLabel[][] labelGrid = new JLabel[10][10];
	/**
	 * Creates a grid of buttons that is 10 by 10.
	 */
	
	private static JButton[][] buttonGrid = new JButton[10][10];
	/**
	 * Creates a grid of ints that is 10 by 10.
	 * Used to hold the position of each ship
	 * in the grid.
	 */
	private int[][] values = new int[10][10];
	/**
	 * Returns the grid of values.
	 * @return int[][]
	 */
	public int[][] getValues() {
		return values;
	}
	/**
	 * Sets the grid of values.
	 * @param newValues array of grid values
	 */
	public void setValues(final int[][] newValues) {
		values = newValues;
	}
	/**
	 * Creates the board of a certain size and type.
	 * Type is either a grid of labels or a grid of buttons.
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

		for (int y = 0; y < boardLayout.getColumns() - 1; y++) {
			this.add(
			 new JLabel("" + (y + 1), SwingConstants.CENTER));
			for (int x = 0; x < boardLayout.getRows() - 1; x++) {
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
						new File("res/waves.png"));
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
	 * Returns the grid of labels.
	 * @return JLabel[][]
	 */
	public JLabel[][] getLabelGrid() {		
		return labelGrid;		
	}
	/**
	 * Returns a label at a certain coordinate.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return JLabel
	 */
	public JLabel getLabel(final int x, final int y) {
		return labelGrid[x][y];
	}
	/**
	 * Returns the grid of buttons.
	 * @return JButton[][]
	 */
	public JButton[][] getButtonGrid() {
		return buttonGrid;
	}
	/**
	 * Used by Grid to label the coordinates of the board.
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
