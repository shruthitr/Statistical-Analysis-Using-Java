package edu.uic.ids517.f17g305.model;

import javax.faces.bean.*;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private String userName;
	private String password;
	private String dbHost;
	private String dbType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
}
