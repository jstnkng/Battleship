package battleship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	public int x;
	public int y;
	public String pathH;
	public String pathV;
	public String imagePath;
	public int size;
	private boolean rotate = false;
	
	public Ship(ShipType type) {
		if (type == ShipType.AircraftCarrier) {
			pathH = "res\\AC_H.png";
			pathV = "res\\AC_V.png";
			
			x = 0;
			y = 0;
			width = 350;
			height = 50;
			size = 5;
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
		else if (type == ShipType.Battleship) {
			pathH = "res\\BS_H.png";
			pathV = "res\\BS_V.png";
			
			x = 0;
			y = 100;
			width = 300;
			height = 50;
			size = 4;
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
		else if (type == ShipType.Cruiser) {
			pathH = "res\\C_H.png";
			pathV = "res\\C_V.png";
			//these values can be changed as needed, just used the same numbers you had for the battleship for now
			x = 0;
			y = 100;
			width = 300;
			height = 50;
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
			width = 300;
			height = 50;
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
			width = 300;
			height = 50;
			size = 3;
			//When you rotate a ship, you can just change the image path to either the _h or _v
			imagePath = pathH;
			SetImages(pathH, pathV);
		}
	}
	
	public void SetImages(String pathH, String pathV) {
		
		try {
			imageH = ImageIO.read(new File(pathH));
		}catch (IOException ex) {
			//handle the exception
			System.err.println("H image not found: " + pathH);
		}
		
		try {
			imageV = ImageIO.read(new File(pathV));
		}catch (IOException ex) {
			//handle the exception
			System.err.println("V image not found: " + pathV);
		}
		
		//this.setOpaque(false);
		this.setBackground(Color.MAGENTA);
	}
	
	public void draw() {
		repaint();
	}
	
	public void rotate(boolean flip) {
		
		if (flip == true) {
			rotate = true;
			repaint();
		}else {
			rotate = false;
			repaint();
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		
		if(rotate == false) {
			
			g.drawImage(imageH, 0, 0, this);
			
		}
		if (rotate == true) {
			
			g.drawImage(imageV, 0, 0, this);
		}
		
	}
}
