package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList;

import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;

/*
*
* @Author AntoniosValais
*
*/

public class AddBlackListResult implements InterfaceServletResult
{
	private String result;
	
	private String message;
	
	public AddBlackListResult(Boolean result)
	{
		if( result == Boolean.TRUE )
		{
			this.result = "Success!";
			
			this.message = "User has been added to blacklist";
		}
		else
		{
			this.result = "Failed!";
			
			this.message = "User cannot be added to blacklist!!";
		}
	}

	@Override
	public String getResult()
	{
		return result;
	}

	@Override
	public void setResult( String result )
	{
		this.result = result;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	@Override
	public void setMessage( String message )
	{
		this.message = message;
	}
	
}
