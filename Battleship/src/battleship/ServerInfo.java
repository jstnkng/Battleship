package battleship;

import java.io.Serializable;

public class ServerInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	public String getName() { return name;}
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	public String getPassword() { return password;}
	/**
	 * 
	 */
	private String IP;
	/**
	 * 
	 */
	public String getIP() { return IP;}
	
	public ServerInfo(String serverName, String serverPass, String IPAddress) {
		name = serverName;
		password = serverPass;
		IP = IPAddress;
	}
}
