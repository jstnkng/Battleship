package battleship;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Creates the gui menu for multiplayer servers.
 * @author Sam Carson
 *
 */
public class MultiplayerMenu extends JFrame implements MouseListener, RowSorterListener {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Current game mode being played.
		 */
		private GameMode modeChoice;
		/**
		 * servers.
		 */
		private String[][] servers;
		/**
		 * server name.
		 */
		private String serverName;
		/**
		 * server password.
		 */
		private String serverPassword;
		/**
		 * Arraylist of serverInfo.
		 */
		private ArrayList<ServerInfo> serverList = 
				new ArrayList<ServerInfo>();
		/**
		 * column names.
		 */
		private Object[] columns = {"Server Name", "Private"};
		/**
		 * JTable for servers.
		 */
		private JTable table;
		/**
		 * button for creating server.
		 */
		private JButton btnCreateServer;
		/**
		 * button for joining server.
		 */
		private JButton btnJoinServer;
		/**
		 * table model for the table of servers.
		 */
		private DefaultTableModel model;
		
		/**
		 * creates the menu frame.
		 * @param mode the current mode to be played
		 */
		public MultiplayerMenu(final GameMode mode) {
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
			
			//TODO also sort arraylist to match
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			table.setRowSorter(sorter);
			
			sorter.addRowSorterListener(this);
			
			
			
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
		 * @param panel The panel to add to
		 * @param c The component to add
		 * @param gx the horizontal grid location
		 * @param gy The vertical grid location
		 * @param weighty The vertical weight
		 * @param gridW the grid width of the component
		 * @param fill The fill of its area
		 */
		public void addItem(final JPanel panel, final JComponent c,
				final int gx, final int gy, 
				final double weighty, final int gridW, 
				final int fill) {
			
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
			
			//creating server and putting in server table
			if (e.getSource() == btnCreateServer) {
				JTextField name = new JTextField();
				JTextField password = new JTextField();
				Object[] message = {"Server Name: ", name,
						"Password (Optional): ", password
				};
				
				int n = JOptionPane.showConfirmDialog(null,
						message, "Create Server", 
						JOptionPane.OK_CANCEL_OPTION);
				System.out.println("Create Button pressed");
				
				if (n == JOptionPane.OK_OPTION) {
					serverName = name.getText();
					serverPassword = password.getText();
					ServerInfo server = new ServerInfo(
							serverName, serverPassword, null);
					serverList.add(server);
					String isPrivate = "";
					if (serverList.get(serverList.size() - 1)
							.getPassword().length() > 0) {
						isPrivate = "Yes";
					} else {
						isPrivate = "No";
					}

					model.addRow(new Object[] {
							serverList.get(serverList.size() - 1).getName(), isPrivate});
				}
				
			}
			
			//joining server
			if (e.getSource() == btnJoinServer) {
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					if (serverList.get(row).getPassword().length() > 0) {
						
						JTextField password = new JTextField();
						Object[] message = 
							{"Enter Server Password: ", password};
			
						int n = JOptionPane.showConfirmDialog(null, message,
								"Password", JOptionPane.OK_CANCEL_OPTION);
						if (n == JOptionPane.OK_OPTION) {
							String pass = password.getText();
							if (pass.equals(serverList.get(row).getPassword())) {
								
								System.out.println("Joining server " 
										+ serverList.get(row).getName());
							} else {
								System.out.println("incorrect password");
							}
						}
					} else {
						System.out.println("Joining " 
							+ serverList.get(row)
							.getName());
					}
				}
			}
		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sorterChanged(RowSorterEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
