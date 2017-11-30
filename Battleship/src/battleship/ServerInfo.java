package battleship;

import java.io.Serializable;

public class ServerInfo implements Serializable {
		
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = -7951921108943135754L;
	/**
	 * server name
	 */
	private String name;
	/**
	 * returns the name of the server
	 */
	public String getName() { return name;}
	/**
	 * server password
	 */
	private String password;
	/**
	 * returns the password of the server
	 */
	public String getPassword() { return password;}
	/**
	 * IP of the server
	 */
	private String IP;
	/**
	 * returns the IP of the server
	 */
	public String getIP() { return IP;}
	/**
	 * type, 0 for server, 1 for disconnecting client
	 */
	private int type;
	/**
	 * returns the type of ServerInfo message
	 */
	public int getType() {return type;}
	/**
	 * id to link to client
	 */
	public int ID;
	/**
	 * sets ID
	 */
	public void setID(int id) {ID = id;}
	/**
	 * returns ID
	 */
	public int getID() {return ID;}
	
	
	/**
	 * constructor 
	 * @param serverName
	 * @param serverPass
	 * @param IPAddress
	 */
	public ServerInfo(String serverName, String serverPass, String IPAddress, int t) {
		name = serverName;
		password = serverPass;
		IP = IPAddress;
		type = t;
	}
	
	/**
	 * returns the ServerInfo object
	 */
	public ServerInfo getServerInfo() {
		return this;
	}
}
