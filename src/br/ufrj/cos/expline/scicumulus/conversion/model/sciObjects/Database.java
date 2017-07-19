package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Database {
	private String userName;
	private String name;
	private String password;
	private String path;
	private String port;
	private String server;
	
	public Database() {
		this.userName = "";
		this.name = "";
		this.password = "";
		this.path = "";
		this.port = "";
		this.server = "";
	}
	
	public Database(String userName, String name, String password, String path, String port, String server) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.path = path;
		this.port = port;
		this.server = server;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	
}
