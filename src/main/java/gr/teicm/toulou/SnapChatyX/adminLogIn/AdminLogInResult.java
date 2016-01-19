package gr.teicm.toulou.SnapChatyX.adminLogIn;

/**
 * 
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 * 
 */

public class AdminLogInResult {
	
	private String result;
	private String message;
	
	public AdminLogInResult(Boolean result) {
		
		if(result == Boolean.TRUE) {
			
			this.result = "Success!";
			this.message = "Welcome Admin!";
		}
		else {
			
			this.result = "Error!";
			this.message = "Wrong Admin Name or password!";
				
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
