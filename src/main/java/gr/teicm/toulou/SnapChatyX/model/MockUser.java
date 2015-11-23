package gr.teicm.toulou.SnapChatyX.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MockUser {

	private String name;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private String eMail;
	
	public MockUser(String name, String lastName, String userName, String password, String eMail) {
		this.name = name;
		
		this.lastName = lastName;
		
		this.userName = userName;
		
		this.password = password;
		
		this.eMail = eMail;
	}
	
	public MockUser() {
		this(null, null, null, null, null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getEMail() {
		return eMail;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
}
