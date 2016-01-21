package gr.teicm.toulou.SnapChatyX.removeAdminServlet;

import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;

public class RemoveAdminResult implements InterfaceServletResult {
	
	private String result;
	
	private String message;
	
	public RemoveAdminResult(Boolean result)
	{
		if(result==Boolean.TRUE)
		{
			message = "User has been successfully removed from admin.";
			
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
