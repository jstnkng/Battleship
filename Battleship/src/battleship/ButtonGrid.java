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

public class ButtonGrid extends Grid {

	
	private JButton[][] buttons = new JButton[10][10];
	public JButton[][] getButtons(){
		return buttons;
	}
	
	private int patrolBoatHits;
	public int getPatrolBoatHits() {
		return patrolBoatHits;
	}
	
	private int submarineHits;
	public int getSubmarineHits() {
		return submarineHits;
	}
	
	private int cruiserHits;
	public int getCruiserHits() {
		return cruiserHits;
	}
	
	private int battleShipHits;
	public int getBattleShipHits() {
		return battleShipHits;
	}
	
	private int aircraftCarrierHits;
	public int getAircraftCarrierHits() {
		return aircraftCarrierHits;
	}
	
	public ButtonGrid() {
		for (int y = 0; y < boardLayout.getColumns() - 1; y++) {
			this.add(
			 new JLabel("" + (y + 1), SwingConstants.CENTER));
			for (int x = 0; x < boardLayout.getRows() - 1; x++) {
				
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
					buttons[x][y] = box;
					this.add(box, new Integer(0));
				}

			}
		
	}
	
	public void hit(ShipType type) {
		if (type == ShipType.PatrolBoat) patrolBoatHits++;
		else if (type == ShipType.Submarine) submarineHits++;
		else if (type == ShipType.Cruiser) cruiserHits++;
		else if (type == ShipType.Battleship) battleShipHits++;
		else if (type == ShipType.AircraftCarrier) aircraftCarrierHits++;
	}
	
	public void loadPictures(int[][] values, GameBoard board) {
		int x = 0;
		int y = 0;
		for (JButton[] row  : buttons) {
			for (JButton button : row) {
				System.out.print(values[x][y]);
				button.addMouseListener(board);
				button.setName(values[x][y] + "");
				button.setText(x + "," + y);
				button.setHorizontalAlignment(
						SwingConstants.CENTER);
				if (values[x][y] == 0) {
					 try {
					      Image img = 
						  ImageIO.read(
							new File("res/waves.png"));
						  button.setIcon(new ImageIcon(img));
							  } catch (Exception ex) {
							    System.out.println(ex);
							  }
				}
				button.setBorder(boardBorder);
				y++;
				//x++;
			}
			System.out.println("");
			x++;
			//y++;
			//x = 0;
			y = 0;
		}
	}
	

}
