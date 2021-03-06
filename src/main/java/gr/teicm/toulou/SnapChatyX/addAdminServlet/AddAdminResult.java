package gr.teicm.toulou.SnapChatyX.addAdminServlet;

import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;

public class AddAdminResult implements InterfaceServletResult {
	
	private String result;
	
	private String message;
	
	public AddAdminResult(Boolean result)
	{
		if(result==Boolean.TRUE)
		{
			message = "User has been successfully promoted to admin.";
			
			this.result = "Done";
		}
		
		else
		{
			message = "Request failed.";
			
			this.result = "Error";
		}
	}

	@Override
	public String getResult() {
		return result;
	}


	@Override
	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String getMessage() {
		return message;
	}


	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
