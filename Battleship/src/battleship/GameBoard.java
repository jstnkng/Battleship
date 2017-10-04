package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JFrame {

//	private JPanel content = new JPanel();
	private Grid player1Board;
	private Grid player2Board;
	public int[][] player1Values = new int[10][10];
	public int [][] player2Values = new int[10][10];
	public Ship[] player1Ships = new Ship[5];
	public Ship[] player2Ships = new Ship[5];
	
	public boolean player1Turn = true;
	public boolean player2Turn = false;
	private ArrayList<Point> cpuShots = new ArrayList<Point>();
	
	public GameMode currentMode;
	
	//Two player constructor
	public GameBoard(GameMode mode) {
		//Boats[0] is AircraftCarrier
		//Boats[1] is Battleship
		//Boats[2] is Cruiser
		//Boats[3] is PatrolBoat
		//Boats[4] is Submarine
		currentMode = mode;
		this.setSize(1200,700);
		this.setLayout(new GridLayout(0,2));
	}
	
	public void beginGame() {
		player1Board = new Grid(10, "Label");
		player1Board.values = player1Values;
		int x2 = 0;
		int y2 = 0;
		for (JLabel[] row  : player1Board.labelGrid) {
			for (JLabel label : row) {
				label.setText(player1Values[y2][x2] + "");
				x2 ++;
			}
			y2 ++;
			x2 = 0;
		}		
		this.add(player1Board);
		
		player2Board = new Grid(10, "Button");
		player2Board.values = player2Values;
		int x = 0;
		int y = 0;
		for (JButton[] row  : player2Board.buttonGrid) {
			for (JButton button : row) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setText(button.getName());
						  try {
							    Image hit = ImageIO.read(new File("res\\waves_reddot.png"));
							    Image miss = ImageIO.read(new File("res\\waves_whitedot.png"));
							    if (button.getText().contains("1")) button.setIcon(new ImageIcon(hit));
							    else if (button.getText().contains("0")) button.setIcon(new ImageIcon(miss));
							  } catch (Exception ex) {
							    System.out.println(ex);
						}
						player2Turn = !player2Turn;
						player1Turn = !player1Turn;
						if (currentMode == GameMode.OnePlayerMode) chooseShot();
					}
				});
				button.setName(player2Values[y][x] + "");
				if (player2Values[y][x] == 1) {
					button.setForeground(Color.RED);
				}
				else button.setForeground(Color.black);
				x ++;
			}
			y ++;
			x = 0;
		}
		this.add(player2Board);
	}
	
	public void cpuFire(Point point) {
		player2Board.setEnabled(false);		
		
		int randomX = (int)point.getX();
		int randomY = (int)point.getY();
		
		player1Board.labelGrid[randomX][randomY].setBackground(Color.white);
		player2Board.setEnabled(true);
		
	}
	
	//Picks the point for the cpu to shoot making sure it always picks a different spot
	public void chooseShot() {
		
		int minimum = 0;
		int maximum = 9;
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int x =  rn.nextInt(range) + minimum;
		int y = rn.nextInt(range) + minimum;
		
		Point point = new Point(x,y);
		
		if(cpuShots.contains(point)) {
			chooseShot();
		}else {
			cpuShots.add(point);
			cpuFire(point);
		}
	}
	
}
