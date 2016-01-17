package gr.teicm.toulou.SnapChatyX.model;

import java.util.ArrayList;
import java.util.List;

/*
*
* @Author AntoniosValais
*
*/

public class SnapClient
{
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	
	private Double longitude;
	
	private Double latitude;
	
	private String locationName;
	
	private List< String > friendList;
	
	private List< String > blackList;
	

	public SnapClient()
	{
		locationName = "Wonderland"; //TODO: temporary mexri na pernei timh to locationName
		
		friendList = new ArrayList< String >();
		
		blackList = new ArrayList< String >();
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude( Double longitude )
	{
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude( Double latitude )
	{
		this.latitude = latitude;
	}

	public String getLocationName()
	{
		return locationName;
	}

	public void setLocationName( String locationName )
	{
		this.locationName = locationName;
	}
	
	public Boolean addToFriendList( String snapClient )
	{
		if( snapClient.isEmpty() )
		{
			return Boolean.FALSE;
		}
		
		try
		{
			if( friendList.contains( snapClient ) == false )
			{
				return friendList.add( snapClient );
			}
			
			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}
	
	public Boolean addToBlackList( String snapClient )
	{
		if( snapClient.isEmpty() )
		{
			return Boolean.FALSE;
		}
		
		try
		{
			if( blackList.contains( snapClient ) == false )
			{
				return blackList.add( snapClient );
			}
			
			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}
	
	public Boolean removeFromFriendList( String snapClient )
	{
		if( snapClient.isEmpty() )
		{
			return Boolean.FALSE;
		}
		
		try
		{
			return friendList.remove( snapClient );
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}	
	
	public Boolean removeFromBlackList( String snapClient )
	{
		if( snapClient.isEmpty() )
		{
			return Boolean.FALSE;
		}
		
		try
		{
			return blackList.remove( snapClient );
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}
	
	public List< String > getBlackList()
	{
		return blackList;
	}
	
	public List< String > getFriendList()
	{
		return friendList;
	}
	
}
