package battleship;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShipSetupFrame extends JFrame{

	
	public ShipSetupFrame() {
		
		//Create a new JFrame
		JFrame frame = new JFrame("Ship Setup");
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the content pane
		JComponent contentPane = new ShipSetupPanel();
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);
		
		//Display
		frame.pack();
		frame.setVisible(true);
		
		
		
		
	}
	
}
