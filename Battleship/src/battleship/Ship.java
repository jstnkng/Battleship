package battleship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ship extends JPanel{
	private BufferedImage imageH;
	private BufferedImage imageV;
	private boolean rotate = false;
	
	
	public Ship(String pathH, String pathV) {
		
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
