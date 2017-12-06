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
	 * @return name Name of server
	 */
	public String getName() { return name;}
	/**
	 * server password
	 */
	private String password;
	/**
	 * returns the password of the server
	 * @return password the password of the server
	 */
	public String getPassword() { return password;}
	/**
	 * IP of the server
	 */
	private String IP;
	/**
	 * returns the IP of the server
	 * @return IP Ip address of server
	 */
	public String getIP() { return IP;}
	/**
	 * type, 0 for server, 1 for disconnecting client
	 */
	private int type;
	/**
	 * returns the type of ServerInfo message
	 * @return type Type of server
	 */
	public int getType() {return type;}
	/**
	 * id to link to client
	 */
	public int ID;
	/**
	 * sets ID
	 * @param id ID to set for server
	 */
	public void setID(int id) {ID = id;}
	/**
	 * returns ID
	 * @return ID ID of server
	 */
	public int getID() {return ID;}
	
	
	/**
	 * constructor 
	 * @param serverName name of the server
	 * @param serverPass password of the server
	 * @param IPAddress IPAddress of the server
	 * @param t Type of server
	 * 
	 */
	public ServerInfo(String serverName, String serverPass, String IPAddress, int t) {
		name = serverName;
		password = serverPass;
		IP = IPAddress;
		type = t;
	}
	
	/**
	 * returns the ServerInfo object
	 * @return this Returns itself
	 */
	public ServerInfo getServerInfo() {
		return this;
	}
}
