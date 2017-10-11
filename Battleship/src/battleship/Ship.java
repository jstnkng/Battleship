package battleship;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 
 */
public class Ship extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private BufferedImage imageH;
	/**
	 * 
	 */
	private BufferedImage imageV;
	/**
	 * 
	 */
	private int heightV;
	/**
	 * @return int
	 */
	public int getHeightV() {
		return heightV;
	}
	/**
	 * @param height new height to set
	 */
	public void setHeightV(final int height) {
		heightV = height;
	}
	/**
	 * 
	 */
	private int widthV;
	/**
	 * @return int
	 */
	public int getWidthV() {
		return widthV;
	}
	/**
	 * @param width new width to set
	 */
	public void setWidthV(final int width) {
		widthV = width;
	}
	/**
	 * 
	 */
	private int heightH;
	/**
	 * @return int
	 */
	public int getHeightH() {
		return heightH;
	}
	/**
	 * @param height new horizontal height
	 */
	public void setHeightH(final int height) {
		heightH = height;
	}
	/**
	 * 
	 */
	private int widthH;
	/**
	 * @return int
	 */
	public int getWidthH() {
		return widthH;
	}
	/**
	 * @param width new horizontal width
	 */
	public void setWidthH(final int width) {
		widthH = width;
	}
	/**
	 * 
	 */
	public int x;
	/**
	 * 
	 */
//	public int getX() {
//		return x;
//	}
//	/**
//	 * 
//	 */
//	public void setX(int newX) {
//		x = newX;
//	}
	/**
	 * 
	 */
	public int y;
	/**
	 * 
	 */
//	public int getY() {
//		return y;
//	}
//	/**
//	 * 
//	 */
//	public void setY(int newY) {
//		y = newY;
//	}
	/**
	 * 
	 */
	private int row;	
	/**
	 * @return int
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param newRow new row to set
	 */
	public void setRow(final int newRow) {
		row = newRow;
	}
	/**
	 * 
	 */
	private int column;
	/**
	 * @return int
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param newColumn new column to set
	 */
	public void setColumn(final int newColumn) {
		column = newColumn;
	}
	/**
	 * 
	 */
	private int value;
	/**
	 * @return int
	 */
	public int getValue() {
		return value;
	}
	/**
	 * 
	 */
	private String pathH;
	/**
	 * 
	 */
	private String pathV;	
	/**
	 * 
	 */
	private int size;
	/**
	 * @return int
	 */
	public int getShipSize() {
		return size;
	}
	/**
	 * @param value how many squares the ship takes up
	 */
	public void setShipSize(final int value) {
		size = value;
	}
	/**
	 * 
	 */
	private boolean horizontal = true;
	/**
	 * @return boolean
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	/**
	 * @param value either horizontal or vertical
	 */
	public void setHorizontal(final boolean value) {
		horizontal = value;
	}
	/**
	 * 
	 */
	private ShipType typeOfShip;
	/**
	 * @return ShipType
	 */
	public ShipType getTypeOfShip() {
		return typeOfShip;
	}
	/**
	 * @param type type of ship
	 */
	public void setTypeOfShip(final ShipType type) {
		typeOfShip = type;
	}
	/**
	 * 
	 */	
	private Image scaledH;
	/**
	 * 
	 */
	private Image scaledV;
	/**
	 * @param type type of ship selected
	 */
	public Ship(final ShipType type) {
		typeOfShip = type;
		if (type == ShipType.AircraftCarrier) {
			pathH = "res\\AC_H.png";
			pathV = "res\\AC_V.png";			
			x = 0;
			y = 0;
			widthH = 325;
			heightH = 50;
			widthV = 50;
			heightV = 280;		
			size = 5;
			value = 5;
			setImages(pathH, pathV);
		} else if (type == ShipType.Battleship) {
		
			pathH = "res\\BS_H.png";
			pathV = "res\\BS_V.png";		
			x = 0;
			y = 0;
			widthH = 258;
			heightH = 50;
			widthV = 50;
			heightV = 222;
			size = 4;
			value = 4;
			setImages(pathH, pathV);
		} else if (type == ShipType.Cruiser) {
			pathH = "res\\C_H.png";
			pathV = "res\\C_V.png";
			x = 0;
			y = 0;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			value = 3;
			setImages(pathH, pathV);
		} else if (type == ShipType.Submarine) {
			pathH = "res\\S_H.png";
			pathV = "res\\S_V.png";
			x = 0;
			y = 0;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			value = 2;
			setImages(pathH, pathV);
		} else if (type == ShipType.PatrolBoat) {
			pathH = "res\\PB_H.png";
			pathV = "res\\PB_V.png";
			x = 0;
			y = 0;
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
			//handle the exception
			System.err.println("H image not found: " + pathH);
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
	 * 
	 */
	public void draw() {

		repaint();
	}
	
	/**
	 * 
	 */
	public void rotate() {
		
		//checks current orientation then changes 
		//the panel width and height to match image orientation
		if (isHorizontal()) {
			this.setSize(widthH, heightH);
//			width = widthH;
//			height = heightH;
		} else {
			this.setSize(widthV, heightV);
//			width = widthV;
//			height = heightV;
		}
		
		repaint();
		
	}
	
	/**
	 * @param g graphics to paint
	 */
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		
		if (isHorizontal()) {
			g.drawImage(scaledH, 0, 0, this);
			
		}
		if (!isHorizontal()) {
			g.drawImage(scaledV, 0, 0, this);
		}
		
	}
}
