package battleship;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MultiplayerMenu extends JFrame implements MouseListener {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Current game mode being played.
		 */
		private GameMode modeChoice;
		/**
		 * servers
		 */
		private String[][] servers;
		/**
		 * server name
		 */
		private String serverName;
		/**
		 * server name
		 */
		private String serverPassword;
		/**
		 * column names
		 */
		private String[] columns = {"Name", "Private"};
		/**
		 * JTable for servers
		 */
		private JTable table;
		/**
		 * button for creating server
		 */
		private JButton btnCreateServer;
		/**
		 * button for joining server
		 */
		private JButton btnJoinServer;
		
		private DefaultTableModel model;
		
		/**
		 * creates the menu frame.
		 * @param mode
		 */
		public MultiplayerMenu(GameMode mode) {
			//setup the frame
			this.setTitle("Multiplayer Menu");
			this.setSize(1200, 700);
			this.setResizable(false);
			this.setVisible(true);
			
			modeChoice = mode;
			
			//create panel
			JPanel panel = new JPanel();
			
			//setLayout and add to frame
			panel.setLayout(new GridBagLayout());
			this.add(panel);
			
			
			//Create server table
			model = new DefaultTableModel(0, columns.length);
			model.setColumnIdentifiers(columns);
			
			table = new JTable(model);
			
			JScrollPane scroller = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			addItem(panel, scroller, 1, 0, 1, 2, GridBagConstraints.BOTH);
			
			//make create server button
			btnCreateServer = new JButton();
			btnCreateServer.setText("Create Server");
			
			addItem(panel, btnCreateServer, 1, 1, 1, 1, GridBagConstraints.BOTH);
			btnCreateServer.addMouseListener(this);
			
			//make join button
			btnJoinServer = new JButton();
			btnJoinServer.setText("Join Server");
			
			addItem(panel, btnJoinServer, 2, 1, 1, 1, GridBagConstraints.BOTH);
			btnJoinServer.addMouseListener(this);
			
		}
		
		/**
		 * Used to add the item to the frame
		 * and set its location within the
		 * grid bag layout.
		 * 
		 * @param label The background to add the component to
		 * @param c The component to add
		 * @param gy The vertical grid location
		 * @param weighty The vertical weight
		 * @param fill The fill of its area
		 */
		public void addItem(final JPanel panel, final JComponent c,
				final int gx, final int gy, 
				final double weighty, final int gridW, final int fill) {
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 10, 10, 10);
			gbc.fill = fill;
			gbc.gridx = gx;
			gbc.gridy = gy;
			gbc.gridwidth = gridW;
			gbc.gridheight = 1;
			gbc.weightx = 1;
			gbc.weighty = weighty;
			panel.add(c, gbc);
		}

		@Override
		public void mouseClicked(final MouseEvent e) {
			
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
			if (e.getSource() == btnCreateServer) {
				JTextField name = new JTextField();
				JTextField password = new JTextField();
				Object[] message = {"Server Name: ", name,
									"Password (Optional): ", password
				};
				
				int n = JOptionPane.showConfirmDialog(null, message,
						"Create Server", JOptionPane.OK_CANCEL_OPTION);
				System.out.println("Create Button pressed");
				
				if (n == JOptionPane.OK_OPTION) {
					serverName = name.getText();
					serverPassword = password.getText();
					String isPrivate = "";
					if (serverPassword.length() > 0) {
						isPrivate = "Yes";
					} else {
						isPrivate = "No";
					}

					model.addRow(new String[] {serverName, isPrivate});
				}
				
			}
		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
