package battleship;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelGrid extends Grid{
	
	private JLabel[][] labels = new JLabel[10][10];
	public JLabel[][] getLabels(){
		return labels;
	}
	
	public JLabel getLabel(int x, int y) {
		return labels[x][y];
	}
	public LabelGrid() {
		for (int y = 0; y < boardLayout.getColumns() - 1; y++) {
			this.add(
			 new JLabel("" + (y + 1), SwingConstants.CENTER));
			for (int x = 0; x < boardLayout.getRows() - 1; x++) {
					JLabel box = new JLabel();
					box.setBackground(Color.WHITE);
					box.setOpaque(true);
					box.setText(x + "," + y);
					box.setHorizontalAlignment(
							SwingConstants.CENTER);
					box.setForeground(Color.WHITE);
					box.setBorder(boardBorder);
					labels[x][y] = box;
					this.add(box, new Integer(0));
				}
			}
	}
	
	public void loadPictures(int[][] values, boolean showShips) {
		int x2 = 0;
		int y2 = 0;
			for (JLabel[] row  : labels) {
				for (JLabel box : row) {
					System.out.print(values[x2][y2]);
					if (values[x2][y2] == 0) {
					    Image img;
						try {
						img = ImageIO.read(
						new File("res\\waves.png"));
						box.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (values[x2][y2] == 70) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res\\waves_whitedot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					} else if (values[x2][y2] > 60 && showShips) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res\\ship_reddot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					} else if (values[x2][y2] == 80 && !showShips) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res\\ship_reddot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					}else if (values[x2][y2] > 60 && values[x2][y2] < 70 && !showShips) {
						 Image img;
							try {
							img = ImageIO.read(
							new File("res\\waves_reddot.png"));
							box.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}
					}else if (showShips) {
						box.setForeground(Color.GRAY);
						box.setBackground(Color.GRAY);
					}else {
						Image img;
						try {
						img = ImageIO.read(
						new File("res\\waves.png"));
						box.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					box.setText(values[x2][y2] + "");
					
					y2++;
					//x2++;
				}
				System.out.println("");
				x2++;
				//y2++;
				y2 = 0;
				//x2 = 0;
			}
	}

}
