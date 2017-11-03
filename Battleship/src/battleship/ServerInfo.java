package battleship;

public class ServerInfo {
	
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
