package gr.teicm.toulou.SnapChatyX;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import com.mongodb.DBObject;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.SnapClient;

public enum DataAccessObject implements IDAO,InterfaceDataAccessObject
{

	DAO;

	public Set< SnapClient > registeredUsers = new HashSet< SnapClient >();

	// websocket stuff
	private Set< Session > sessions = new HashSet< Session >();
	private Set< SnapClient > onlineSnapClients = new HashSet< SnapClient >();
	private HashMap< SnapClient, Session > snapClientSessionMap = new HashMap< SnapClient, Session >();

	// username,longitude,latitude
	@Override
	public String saveLocation( DBObject location )
	{

		try
		{

			String usernameOnLocationDATA = location.get( "username" ).toString();

			SnapClient snapClientToUpdate = this.getOnlineSnapClientWithUsername( usernameOnLocationDATA );

			snapClientToUpdate.setLatitude( (Double) location.get( "latitude" ) );
			snapClientToUpdate.setLongitude( (Double) location.get( "longitude" ) );

			return "{\"result\":\"success\"}";
		}
		catch(Exception e)
		{
			System.out.println( e );
			return "{\"result\":\"internal error\"}";
		}

	}

	private SnapClient getOnlineSnapClientWithUsername( String usernameOnLocationDATA )
	{
		for(SnapClient onlineSnapClient : onlineSnapClients)
		{
			if(onlineSnapClient.getUsername().equals( usernameOnLocationDATA ))
			{
				return onlineSnapClient;
			}
		}

		return null;
	}

	// onoma,epitheto,username,password,email
	@Override
	public String signUpUser( DBObject user )
	{

		SnapClient newUser = new SnapClient();
		try
		{

			newUser.setUsername( user.get( "username" ).toString() );

			newUser.setPassword( user.get( "password" ).toString() );

			newUser.setFirstName( user.get( "firstName" ).toString() );

			newUser.setLastName( user.get( "lastName" ).toString() );

			newUser.setEmail( user.get( "email" ).toString() );

			this.registeredUsers.add( newUser );

			System.out.println( "signup user " + newUser.getUsername() );

			return "{\"result\":\"success\"}";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "{\"result\":\"internal error\"}";
		}

	}

	// username,password
	@Override
	public String signInUser( DBObject user )
	{

		try
		{
			String username = user.get( "username" ).toString();
			
			String password = user.get( "pass" ).toString();
			
			System.out.println( username + " " + password );

			for( SnapClient registeredUser : this.registeredUsers)
			{
				System.out.println( registeredUser.getUsername() + " " + registeredUser.getPassword() );
				
				if( registeredUser.getUsername().equals( username ) && registeredUser.getPassword().equals( password ))
				{
					System.out.println( username + "exists corrent Log in" );
					
					SnapClient client = new SnapClient();
					
					client.setUsername( username );
					
					this.onlineSnapClients.add( client );
					
					return "{\"result\":\"User exists\"}";
				}
			}
			return "{\"result\":\"User Does Not Exist\"}";
		}
		catch(Exception e)
		{
			System.out.println( e );
			
			return "{\"result\":\"internal error\"}";
		}

	}

	@Override
	public Boolean addSession( Session session )
	{
		try
		{
			this.sessions.add( session );

			return Boolean.TRUE;
		}
		catch(NullPointerException e)
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Boolean removeSession( Session session )
	{
		try
		{
			this.sessions.remove( session );

			return Boolean.TRUE;
		}
		catch(NullPointerException e)
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Session getSessionWithID( String sessionID )
	{
		if(sessions == null || sessions.isEmpty())
		{
			return null;
		}

		for(Session session : sessions)
		{
			if(session.getId().equals( sessionID ))
			{
				return session;
			}
		}

		return null;

	}

	@Override
	public Boolean addOnlineSnapClient( SnapClient snapClient )
	{
		try
		{
			onlineSnapClients.add( snapClient );

			return Boolean.TRUE;
		}
		catch(NullPointerException e)
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Boolean removeOnlineSnapClient( SnapClient snapClient )
	{
		try
		{
			onlineSnapClients.remove( snapClient );

			return Boolean.FALSE;
		}
		catch(NullPointerException e)
		{
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean relateUsernameWithSession( String username, Session session )
	{
		for(SnapClient snapClient : onlineSnapClients)
		{
			if(snapClient.getUsername().equals( username ))
			{
				try
				{
					snapClientSessionMap.put( snapClient, session );

					return Boolean.TRUE;
				}
				catch(Exception e)
				{

					return Boolean.FALSE;
				}

			}
		}

		return Boolean.FALSE;
	}

	@Override
	public Set< Session > getAllSessions()
	{
		return this.sessions;
	}

}
