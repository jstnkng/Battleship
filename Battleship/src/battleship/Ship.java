package battleship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Creates a panel to hold each ship.
 */
public class Ship extends JPanel {
	/**
	 * ID for serializable class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates Buffered Image for horizontal orientation.
	 */
	private BufferedImage imageH;
	/**
	 * Creates Buffered Image for vertical orientation.
	 */
	private BufferedImage imageV;
	/**
	 * The height of the image for vertical orientation.
	 */
	private int heightV;
	/**
	 * Returns the height for vertical orientation.
	 * @return the height of vertical image
	 */
	public int getHeightV() {
		return heightV;
	}
	/**
	 * The width of the image for vertical orientation.
	 */
	private int widthV;
	/**
	 * Returns the width for vertical orientation.
	 * @return the width of vertical image
	 */
	public int getWidthV() {
		return widthV;
	}
	/**
	 * The height of the image for horizontal orientation.
	 */
	private int heightH;
	/**
	 * Returns the height for horizontal orientation.
	 * @return the height of horizontal image
	 */
	public int getHeightH() {
		return heightH;
	}
	/**
	 * The width of the image for horizontal orientation.
	 */
	private int widthH;
	/**
	 * Returns the width for horizontal orientation.
	 * @return the width of horizontal image
	 */
	public int getWidthH() {
		return widthH;
	}
	/**
	 * The row in the grid the start of the ship is located.
	 */
	private int row;	
	/**
	 * Returns the row that the start of the ship is located.
	 * @return the ship's row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Sets the ships's row.
	 * @param newRow new row to set
	 */
	public void setRow(final int newRow) {
		row = newRow;
	}
	/**
	 * The column in the grid the start of the ship is located.
	 */
	private int column;
	/**
	 * Returns the column in the grid the start of the ship is located.
	 * @return the ships column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Sets the ship's column.
	 * @param newColumn new column to set
	 */
	public void setColumn(final int newColumn) {
		column = newColumn;
	}
	/**
	 * The value assigned to a specific ship.
	 */
	private int value;
	/**
	 * Returns the value of a specific ship.
	 * @return the ships value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * The path of the horizontal image.
	 */
	private String pathH;
	/**
	 * The path of the vertical image.
	 */
	private String pathV;	
	/**
	 * The length of the ship.
	 */
	private int size;
	/**
	 * Returns the length of the ship.
	 * @return the length of the ship
	 */
	public int getShipSize() {
		return size;
	}
	/**
	 * Boolean to determine if the ship is horizontal.
	 */
	private boolean horizontal = true;
	/**
	 * Returns true if the ship is horizontal
	 * and false if it is vertical.
	 * @return true if ship is horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	/**
	 * Sets the ship to be horizontal or vertical.
	 * @param value either horizontal or vertical
	 */
	public void setHorizontal(final boolean value) {
		horizontal = value;
	}
	/**
	 * Determines the type of ship to create.
	 */
	private ShipType typeOfShip;
	/**
	 * Returns the type of ship to create.
	 * @return the type of ship
	 */
	public ShipType getTypeOfShip() {
		return typeOfShip;
	}
	/**
	 * Creates an image to use for horizontal scaling.
	 */	
	private Image scaledH;
	/**
	 * Creates an image to use for vertical scaling.
	 */
	private Image scaledV;
	/**
	 * Sets the values for the ship depending on ship type
	 * and then calls setImages to import images.
	 * @param type type of ship selected
	 */
	public Ship(final ShipType type) {
		typeOfShip = type;
		if (type == ShipType.AircraftCarrier) {
			pathH = "res/AircraftCarrierH.png";
			pathV = "res/AircraftCarrierV.png";			
//			x = 0;
//			y = 0;
			widthH = 325;
			heightH = 50;
			widthV = 50;
			heightV = 280;		
			size = 5;
			value = 5;
				setImages(pathH, pathV);

		} else if (type == ShipType.Battleship) {
		
			pathH = "res/BattleShipH.png";
			pathV = "res/BattleShipV.png";		
//			x = 0;
//			y = 0;
			widthH = 258;
			heightH = 50;
			widthV = 50;
			heightV = 222;
			size = 4;
			value = 4;
			setImages(pathH, pathV);
		} else if (type == ShipType.Cruiser) {
			pathH = "res/CruiserH.png";
			pathV = "res/CruiserV.png";
//			x = 0;
//			y = 0;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			value = 3;
				setImages(pathH, pathV);
				
		} else if (type == ShipType.Submarine) {
			pathH = "res/SubmarineH.png";
			pathV = "res/SubmarineV.png";
//			x = 0;
//			y = 0;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			value = 2;
			setImages(pathH, pathV);
		} else {
			pathH = "res/PatrolBoatH.png";
			pathV = "res/PatrolBoatV.png";
//			x = 0;
//			y = 0;
			widthH = 124;
			heightH = 50;
			widthV = 50;
			heightV = 106;
			size = 2;
			value = 1;
			setImages(pathH, pathV);		
		}
	}
	/**
	 * Imports the images from the paths and scales them to
	 * the correct height and width depending on orientation.
	 * @param pathH the horizontal image of the ship
	 * @param pathV the vertical image of the ship
	 */
	public void setImages(final String pathH, final String pathV) {
		
		try {
			//gets image from file
			imageH = ImageIO.read(new File(pathH));
			
			//scales the image to panel size
			scaledH = imageH.getScaledInstance(
				widthH, heightH, Image.SCALE_SMOOTH);
			
		} catch (IOException ex) {
			System.err.println("H image not found: " + pathH);
			//handle the exception
		}
		
		try {
			//gets image from file
			imageV = ImageIO.read(new File(pathV));
			
			//scales the image to panel size
			scaledV = imageV.getScaledInstance(
				widthV, heightV, Image.SCALE_SMOOTH);
			
		} catch (IOException ex) {
			//handle the exception
			System.err.println("V image not found: " + pathV);
		}
		
		this.setOpaque(false);
		
	}
	/**
	 * Changes the orientation of the panel and repaints
	 * new images.
	 */
	public void rotate() {
		
		//checks current orientation then changes 
		//the panel width and height to match image orientation
		if (isHorizontal()) {
			this.setHorizontal(false);
			this.setSize(widthV, heightV);
//			width = widthH;
//			height = heightH;S
		} else {
			this.setHorizontal(true);
			this.setSize(widthH, heightH);
//			width = widthV;
//			height = heightV;
		}
		
		repaint();
		
	}
	
	/**
	 * Paints the images in the panel.
	 * @param g graphics to paint
	 */
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		
		if (isHorizontal()) {
			g.drawImage(scaledH, 0, 0, this);
			
		} else {
			g.drawImage(scaledV, 0, 0, this);
		}
		
	}
}
