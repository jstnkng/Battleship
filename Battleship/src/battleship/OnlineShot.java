package battleship;

import java.awt.Point;
import java.io.Serializable;

public class OnlineShot implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5004957754606286359L;
	/**
	 * 
	 */
	private Point shotLoc;
	/**
	 * 
	 */
	public Point getShotLoc() { return shotLoc;}
	/**
	 * 
	 */
	private Boolean isGameOver = false;
	/**
	 * 
	 */
	public Boolean getIsGameOver() { return isGameOver;}
	
	
	public OnlineShot(Point shot, Boolean gameOver) {
		shotLoc = shot;
		isGameOver = gameOver;
	}
	
	public OnlineShot getOnlineShot() {
		return this;
	}
}
