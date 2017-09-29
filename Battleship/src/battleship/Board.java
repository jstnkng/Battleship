package battleship;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Board extends JFrame {

	 private JPanel contentPane;
	 private PlayerGrid player1Board;
	 private PlayerGrid player2Board;
 
	 
	  /*
	  * @param size;
	  */  
  public Board(final int size) {
	  
    GridLayout mainLayout = new GridLayout(2, 2);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    contentPane = new JPanel();
    contentPane.setLayout(mainLayout);
    contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));



    contentPane.add(player1Board);
    contentPane.add(player2Board);
    contentPane.add(new JButton("hi"));
    setContentPane(contentPane);
  }

}
