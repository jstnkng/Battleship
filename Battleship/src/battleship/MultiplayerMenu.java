package battleship;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
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

/**
 * Creates the gui menu for the battleship servers.
 * @author Sam Carson
 *
 */
public class MultiplayerMenu extends JFrame implements MouseListener, RowSorterListener {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
		 * server IP.
		 */
		private String serverIP;
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
		 * client.
		 */
		private Client client;
		
		/**
		 * creates the menu frame.
		 * 
		 */
		public MultiplayerMenu() {
			//setup the frame
			this.setTitle("Server Menu");
			this.setSize(1200, 700);
			this.setResizable(false);

			
			//create panel
			JPanel panel = new JPanel();
			
			//setLayout and add to frame
			panel.setLayout(new GridBagLayout());
			
			
			
			//Create server table model
			model = new DefaultTableModel(0, columns.length);
			model.setColumnIdentifiers(columns);
			
			//set table to the model
			table = new JTable(model);
			
			//make the table scrollable
			JScrollPane scroller = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			addItem(panel, scroller, 1, 0, 1, 2, GridBagConstraints.BOTH);
			
			//TODO also sort arraylist to match
//			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
//			table.setRowSorter(sorter);
//			
//			sorter.addRowSorterListener(this);
			
			
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
			
			this.add(panel);
			this.setVisible(true);
			
			
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
		
		/**
		 * add server to table.
		 * 
		 * @param server info for server
		 */
		public void append(final ServerInfo server) {
			
			//adds server to the list of servers
			serverList.add(server);
			
			//determines if the server is private
			String isPrivate = "";
			if (serverList.get(serverList.size() - 1)
					.getPassword().length() > 0) {
				isPrivate = "Yes";
			} else {
				isPrivate = "No";
			}
			
			//adds server to the table
			model.addRow(new Object[] {
					serverList.get(serverList.size() - 1).getName() + " " + 
							serverList.get(serverList.size() - 1).getIP(), isPrivate});
			
		}
		
		/**
		 * deletes server from table.
		 * 
		 * @param server info for server
		 */
		public void delete(final ServerInfo server) {
			for (ServerInfo s: serverList) {
				if (s.getIP() == server.getIP()) {
					model.removeRow(serverList.indexOf(s));
					serverList.remove(s);
					break;
				}
			}
		}
		
		/**
		 * Sets the client.
		 * 
		 * @param c Client
		 */
		public void start(Client c) {
			client = c;
			
		}
		
		/**
		 * when connection fails.
		 */
		public void connectionFailed() {
			
		}


		@Override
		public void mousePressed(final MouseEvent e) {
			
			//port for created game server
			int portNum = 5335;
			
			//Create server button is pressed
			if (e.getSource() == btnCreateServer) {
				
				
				serverIP = client.getMyIP();
				
				//create text fields
				JTextField name = new JTextField();
				JTextField password = new JTextField();
				
				//create message for optionpane
				Object[] message = {"Server Name: ", name,
						"Password (Optional): ", password
				};
				
				//open optionpane and set result to n
				int n = JOptionPane.showConfirmDialog(null,
						message, "Create Server", 
						JOptionPane.OK_CANCEL_OPTION);
				System.out.println("Create Button pressed");
				
				//create serverInfo object if OK is pressed
				if (n == JOptionPane.OK_OPTION) {
					serverName = name.getText();
					serverPassword = password.getText();
					ServerInfo server = new ServerInfo(
							serverName, serverPassword, serverIP, 0);
					
					//send to server via client
					client.sendServerInfo(server);
					
					this.setVisible(false);
					
					GameServer gs = new GameServer(server.getIP(), portNum);
					gs.start();
					
				}
				
			}
			
			//join server button is pressed
			if (e.getSource() == btnJoinServer) {
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					
					//if server is password protected prompt user for the password
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
								
								//connect user to host
								String ipToConnect = serverList.get(row).getIP();
								
								this.setVisible(false);
								
								//Join server
								GameClient gc = new GameClient(ipToConnect, portNum);
								gc.start();
								
							} else {
								System.out.println("incorrect password");
							}
						}
						
					} else {
						System.out.println("Joining " 
							+ serverList.get(row)
							.getName());
						
						//connect user to host
						String ipToConnect = serverList.get(row).getIP();
						
						this.setVisible(false);
						
						//Join server
						GameClient gc = new GameClient(ipToConnect, portNum);
						gc.start();
						
						
						
					}
				}
			}
		}
		
		@Override
		protected void processWindowEvent(final WindowEvent e) {
			if (e.getID() == WindowEvent.WINDOW_CLOSING) {
				//close client
				client.disconnect();
				super.processWindowEvent(e);
			}
			super.processWindowEvent(e);

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
		public void mouseReleased(final MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		

		@Override
		public void sorterChanged(final RowSorterEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
