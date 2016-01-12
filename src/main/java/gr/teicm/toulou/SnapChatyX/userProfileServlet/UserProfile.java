package gr.teicm.toulou.SnapChatyX.userProfileServlet;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class UserProfile implements InterfaceUserProfile
{
	private String userName;
	
	private String email;
	
	private String location;
	
	public UserProfile( SnapClient snapClient )
	{
		this.userName = snapClient.getUsername();
		
		this.location = snapClient.getLocationName();
		
		this.email = snapClient.getEmail();
	}
	
	@Override
	public String getUserName()
	{
		return userName;
	}

	@Override
	public String getEmail()
	{
		return email;
	}

	@Override
	public String getLocation()
	{
		return location;
	}

}
