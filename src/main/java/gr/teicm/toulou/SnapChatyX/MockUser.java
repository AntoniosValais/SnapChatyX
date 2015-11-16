package gr.teicm.toulou.SnapChatyX;

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
	
	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEMail() {
		return eMail;
	}
	
}
