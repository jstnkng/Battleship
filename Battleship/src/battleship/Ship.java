package battleship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ship extends JPanel{
	private BufferedImage imageH;
	private BufferedImage imageV;
	public int height;
	public int width;
	public int heightV;
	public int widthV;
	public int heightH;
	public int widthH;
	public int x;
	public int y;
	public String pathH;
	public String pathV;
	public String imagePath;
	public int size;
	public boolean isHorizontal = true;
	public ShipType typeOfShip;
	
	private Image scaledH;
	private Image scaledV;
	
	public Ship(ShipType type) {
		typeOfShip = type;
		if (type == ShipType.AircraftCarrier) {
			pathH = "res\\AC_H.png";
			pathV = "res\\AC_V.png";
			
			x = 0;
			y = 0;
			width = 325;
			height = 50;
			widthH = 325;
			heightH = 50;
			widthV = 50;
			heightV = 280;
			size = 5;
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
		else if (type == ShipType.Battleship) {
		
			pathH = "res\\BS_H.png";
			pathV = "res\\BS_V.png";
			
			x = 0;
			y = 0;
			width = 258;
			height = 50;
			widthH = 258;
			heightH = 50;
			widthV = 50;
			heightV = 222;
			size = 4;
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
		else if (type == ShipType.Cruiser) {
			pathH = "res\\C_H.png";
			pathV = "res\\C_V.png";
			//these values can be changed as needed, just used the same numbers you had for the battleship for now
			x = 0;
			y = 0;
			width = 191;
			height = 50;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			//When you rotate a ship, you can just change the image path to either the _h or _v
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
		else if (type == ShipType.PatrolBoat) {
			pathH = "res\\PB_H.png";
			pathV = "res\\PB_V.png";
			//these values can be changed as needed, just used the same numbers you had for the battleship for now
			x = 0;
			y = 100;
			width = 124;
			height = 50;
			widthH = 124;
			heightH = 50;
			widthV = 50;
			heightV = 106;
			size = 2;
			//When you rotate a ship, you can just change the image path to either the _h or _v
			imagePath = pathH;
			SetImages(pathH, pathV);
			
		}
		else if (type == ShipType.Submarine) {
			pathH = "res\\S_H.png";
			pathV = "res\\S_V.png";
			//these values can be changed as needed, just used the same numbers you had for the battleship for now
			x = 0;
			y = 100;
			width = 191;
			height = 50;
			widthH = 191;
			heightH = 50;
			widthV = 50;
			heightV = 164;
			size = 3;
			//When you rotate a ship, you can just change the image path to either the _h or _v
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
	}
	
	public void SetImages(String pathH, String pathV) {
		
		try {
			//gets image from file
			imageH = ImageIO.read(new File(pathH));
			
			//scales the image to panel size
			scaledH = imageH.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
		}catch (IOException ex) {
			//handle the exception
			System.err.println("H image not found: " + pathH);
		}
		
		try {
			//gets image from file
			imageV = ImageIO.read(new File(pathV));
			
			//scales the image to panel size
			scaledV = imageV.getScaledInstance(widthV, heightV, Image.SCALE_SMOOTH);
			
		}catch (IOException ex) {
			//handle the exception
			System.err.println("V image not found: " + pathV);
		}
		
		this.setOpaque(false);
		
	}
	
	public void draw() {

		repaint();
	}
	
	public void rotate() {
		
		//checks current orientation then changes the panel width and height to match image orientation
		if (isHorizontal == true) {
			width = widthH;
			height = heightH;
		} else {
			width = widthV;
			height = heightV;
		}
		
		repaint();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		
		if(isHorizontal == true) {
			g.drawImage(scaledH, 0, 0, this);
			
		}
		if (isHorizontal == false) {
			g.drawImage(scaledV, 0, 0, this);
		}
		
	}
}
