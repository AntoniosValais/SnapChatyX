package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList;

import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;

/*
*
* @Author AntoniosValais
*
*/

public class RemoveFromBlackListResult implements InterfaceServletResult
{
	private String result;
	
	private String message;
	
	public RemoveFromBlackListResult(Boolean result)
	{
		if( result == Boolean.TRUE )
		{
			this.result = "Success!";
			
			this.message = "User has been removed from blackList";
		}
		else
		{
			this.result = "Failed!";
			
			this.message = "User cannot be removed from blackList!!";
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
