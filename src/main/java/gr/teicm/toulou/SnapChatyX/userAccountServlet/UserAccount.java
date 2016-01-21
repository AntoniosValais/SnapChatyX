package gr.teicm.toulou.SnapChatyX.userAccountServlet;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class UserAccount implements InterfaceUserAccount
{
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String locationName;
	
	private List< String > friendList;
	
	private List< String > blackList;

	public UserAccount( SnapClient snapClient )
	{
		this.username = snapClient.getUsername();
		
		this.firstName = snapClient.getFirstName();
		
		this.lastName = snapClient.getLastName();
		
		this.email = snapClient.getEmail();
		
		this.locationName = snapClient.getLocationName();
		
		this.friendList = new ArrayList< String >();
		
		this.blackList = new ArrayList< String >();
		
		for( String snapClientOnFriendList : snapClient.getFriendList() )
		{
			this.friendList.add( snapClientOnFriendList );
		}
		
		for( String snapClientOnBlackList : snapClient.getBlackList() )
		{
			this.blackList.add( snapClientOnBlackList  );
		}
	}
	
	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public String getFirstName()
	{
		return firstName;
	}

	@Override
	public String getLastName()
	{
		return lastName;
	}

	@Override
	public String getEmail()
	{
		return email;
	}

	@Override
	public String getLocationName()
	{
		return locationName;
	}

	@Override
	public List< String > getFriendList()
	{
		return friendList;
	}

	@Override
	public List< String > getBlackList()
	{
		return blackList;
	}
}
