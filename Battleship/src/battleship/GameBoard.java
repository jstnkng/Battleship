package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 */
public class GameBoard extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Grid player1Board;
	/**
	 * 
	 */
	private Grid player2Board;
	/**
	 * 
	 */
	private int[][] player1Values = new int[10][10];
	/**
	 * @param values values from the grid
	 */
	public void setPlayer1Values(final int[][] values) {
		player1Values = values;
	}
	/**
	 * 
	 */
	private int[][] player2Values = new int[10][10];
	/**
	 * @param values values from the grid
	 */
	public void setPlayer2Values(final int[][] values) {
		player2Values = values;
	}
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	//Only used for setting ship pictures on gameboard
	private Ship[] player1Ships = new Ship[5];
	/**
	 * @param ships array of ship info
	 */
	public void setPlayer1Ships(final Ship[] ships) {
		player1Ships = ships;
	}
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	//only used for setting ship pictures on gameboard
	private Ship[] player2Ships = new Ship[5];
	/**
	 * @param ships array of ship info
	 */
	public void setPlayer2Ships(final Ship[] ships) {
		player2Ships = ships;
	}
	/**
	 * 
	 */
	private boolean player1Turn = true;
	/**
	 * 
	 */
	private boolean player2Turn = false;
	/**
	 * 
	 */
	private ArrayList<Point> cpuShots = new ArrayList<Point>();
	/**
	 * 
	 */
	private int cpuAircraftCarrierHits = 0;
	/**
	 * 
	 */
	private int cpuBattleShipHits = 0;
	/**
	 * 
	 */
	private int cpuSubmarineHits = 0;
	/**
	 * 
	 */
	private int cpuCruiserHits = 0;
	/**
	 * 
	 */
	private int cpuPatrolBoatHits = 0;
	/**
	 * 
	 */	
	private int aircraftCarrierHits = 0;
	/**
	 * 
	 */
	private int battleShipHits = 0;
	/**
	 * 
	 */
	private int submarineHits = 0;
	/**
	 * 
	 */
	private int cruiserHits = 0;
	/**
	 * 
	 */
	private int patrolBoatHits = 0;
	/**
	 * 
	 */
	private GameMode currentMode;
	/**
	 * @param mode current mode being played
	 */	
	public GameBoard(final GameMode mode) {
		currentMode = mode;
		this.setSize(1200, 700);
		this.setLayout(new GridLayout(0, 2));
	}
	/**
	 * 
	 */
	public void beginGame() {
		
		player1Board = new Grid(10, "Label");
		player1Board.setValues(player1Values);
		int x2 = 0;
		int y2 = 0;
		for (JLabel[] row  : Grid.getLabelGrid()) {
			for (JLabel box : row) {
				if (player1Values[y2][x2] == 0) {
				    Image img;
					try {
					img = ImageIO.read(
					new File("res\\waves.png"));
					box.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
					box.setForeground(Color.BLUE);
					box.setBackground(Color.BLUE);
				} else {
					box.setForeground(Color.GRAY);
					box.setBackground(Color.GRAY);
				}
				box.setText(player1Values[y2][x2] + "");
				
				x2++;
			}
			y2++;
			x2 = 0;
		}		
		this.add(player1Board);
		
		player2Board = new Grid(10, "Button");
		player2Board.setValues(player2Values);
		int x = 0;
		int y = 0;
		for (JButton[] row  : Grid.getButtonGrid()) {
			for (JButton button : row) {
				button.addMouseListener(this);
				button.setName(player2Values[y][x] + "");
				if (player2Values[y][x] == 1) {
					button.setForeground(Color.RED);
				} else {
					button.setForeground(Color.black);
				}
				x++;
			}
			y++;
			x = 0;
		}
		this.add(player2Board);
		
	}
	
	@Override
	public void mouseClicked(final MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		playerShot(button);
	}
	/**
	 * @param button button that player clicks on
	 */
	public void playerShot(final JButton button) {
		try {
			Image hit = ImageIO.read(
					new File("res\\waves_reddot.png"));
			Image miss = ImageIO.read(
					new File("res\\waves_whitedot.png"));
			if (!button.getName().contains("shot")) {
				if (button.getName().contains("0")) {
					button.setIcon(new ImageIcon(miss));
					button.setName("shot");
				} else { 
					button.setIcon(new ImageIcon(hit));
					if (button.getName().contains("5")) {
						button.setName("shot");
						aircraftCarrierHits++;
						if (aircraftCarrierHits == 5) {
						JOptionPane.showMessageDialog(
						null, "Aircraft Carrier Sunk");
						}
					} else if (button.getName()
							.contains("4")) {
						button.setName("shot");
						battleShipHits++;
						if (battleShipHits == 4) {
						JOptionPane.showMessageDialog(
						null, "BattleShip Sunk");
						}
					} else if (button.getName()
							.contains("3")) {
						button.setName("shot");
						submarineHits++;
						if (submarineHits == 3) {
						JOptionPane.showMessageDialog(
						null, "Submarine Sunk");
						}
					} else if (button.getName()
							.contains("2")) {
						button.setName("shot");
						cruiserHits++;
						if (cruiserHits == 3) {
						JOptionPane.showMessageDialog(
							null, "Cruiser Sunk");
						}
					} else if (button.getName()
							.contains("1")) {
						button.setName("shot");
						patrolBoatHits++;
						if (patrolBoatHits == 2) {
						JOptionPane.showMessageDialog(
						null, "Patrol Boat Sunk");
						}
					}
				}
			}  else {
				//TODO handle already shot locations
				JOptionPane.showMessageDialog(null,
						"You already shot here");
				return;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (aircraftCarrierHits + battleShipHits + cruiserHits
				+ submarineHits + patrolBoatHits == 17) {
			System.out.println("Player 1 wins!");
			JOptionPane.showMessageDialog(null, "You Win!");
		}
		player2Turn = !player2Turn;
		player1Turn = !player1Turn;
		if (currentMode == GameMode.OnePlayerMode) {
			chooseShot();
		}
	}
		
	/**
	 * @param point the coordinates of the shot
	 */
	public void cpuFire(final Point point) {
		player2Board.setEnabled(false);		
		
		int randomX = (int) point.getX();
		int randomY = (int) point.getY();
		JLabel randomBox = Grid.getLabel(randomX, randomY);
		if (randomBox
				.getText().contains("1")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuPatrolBoatHits++;
			if (cpuPatrolBoatHits == 2) {
				JOptionPane.showMessageDialog(null,
				"Your patrol boat has been sunk");
			}
		} else if (randomBox
				.getText().contains("2")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuSubmarineHits++;
			if (cpuSubmarineHits == 3) {
			JOptionPane.showMessageDialog(null,
					"Your submarine has been Sunk");
			}
		} else if (randomBox
				.getText().contains("3")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuCruiserHits++;
			if (cpuCruiserHits == 3) {
				JOptionPane.showMessageDialog(
				null, "Your cruiser has been sunk");
			}
		} else if (randomBox
				.getText().contains("4")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuBattleShipHits++;
			if (cpuBattleShipHits == 4) {
				JOptionPane.showMessageDialog(null,
					"Your battleship has been sunk");
			}
		} else if (randomBox
				.getText().contains("5")) {
			Image hit;
			try {
				hit = ImageIO.read(
				new File("res\\ship_reddot.png"));
				randomBox
						.setIcon(new ImageIcon(hit));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.RED);
			randomBox
					.setForeground(Color.RED);
			cpuAircraftCarrierHits++;
			if (cpuAircraftCarrierHits == 5) {
				JOptionPane.showMessageDialog(null,
				"Your aircraft carrier has been sunk");
			}
		} else {
			Image miss;
			try {
				miss = ImageIO.read(
				new File("res\\waves_whitedot.png"));
				randomBox
						.setIcon(new ImageIcon(miss));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randomBox
					.setBackground(Color.WHITE);
			randomBox
					.setForeground(Color.WHITE);
		}
		
		if (cpuAircraftCarrierHits + cpuBattleShipHits 
				+ cpuCruiserHits + cpuSubmarineHits
				+ cpuPatrolBoatHits == 17) {
			JOptionPane.showMessageDialog(null, "Player 2 wins");
		}
		player2Board.setEnabled(true);
		
	}
	
	//Picks the point for the cpu to shoot
	//making sure it always picks a different spot
	/**
	 * 
	 */
	public void chooseShot() {
		
		int minimum = 0;
		int maximum = 9;
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int x =  rn.nextInt(range) + minimum;
		int y = rn.nextInt(range) + minimum;
		
		Point point = new Point(x, y);
		
		if (cpuShots.contains(point)) {
			chooseShot();
		} else {
			cpuShots.add(point);
			cpuFire(point);
		}
	}


	@Override
	public void mouseEntered(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
