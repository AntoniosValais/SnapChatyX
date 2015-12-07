package gr.teicm.toulou.SnapChatyX.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.websocket.Session;

import com.mongodb.DBObject;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

public enum DataAccessObject implements IDAO,InterfaceDataAccessObject
{

	DAO;

	public Set< SnapClient > registeredSnapClients;

	private Set< Session > sessions;

	private Set< SnapClient > onlineSnapClients;

	private HashMap< SnapClient, Session > snapClientSessionMap;

	private HashMap< SnapClient, List< SnapClientTextMessage > > snapClientTextMessageMap;

	private DataAccessObject()
	{
		registeredSnapClients = new HashSet< SnapClient >();
		
		sessions = new HashSet< Session >();
		
		onlineSnapClients = new HashSet< SnapClient >();
		
		snapClientSessionMap = new HashMap< SnapClient, Session >();
		
		snapClientTextMessageMap = new HashMap< SnapClient, List< SnapClientTextMessage > >();
	}
	
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
		catch( Exception e )
		{
			System.out.println( e );
			return "{\"result\":\"internal error\"}";
		}

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

			this.registeredSnapClients.add( newUser );

			List< SnapClientTextMessage > newUserMessageList = new ArrayList< SnapClientTextMessage >();

			snapClientTextMessageMap.put( newUser, newUserMessageList );

			System.out.println( "signup user " + newUser.getUsername() );

			return "{\"result\":\"success\"}";
		}
		catch( Exception e )
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

			for( SnapClient registeredUser : this.registeredSnapClients )
			{
				System.out.println( registeredUser.getUsername() + " " + registeredUser.getPassword() );

				if( registeredUser.getUsername().equals( username ) && registeredUser.getPassword().equals( password ) )
				{
					System.out.println( username + "exists corrent Log in" );

					SnapClient loggedInSnapClient = this.getRegisteredSnapClientWithUsername( username );
					
					this.onlineSnapClients.add( loggedInSnapClient );

					return "{\"result\":\"User exists\"}";
				}
			}
			return "{\"result\":\"User Does Not Exist\"}";
		}
		catch( Exception e )
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
		catch( NullPointerException e )
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
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Session getSessionWithID( String sessionID )
	{
		if( sessions == null || sessions.isEmpty() )
		{
			return null;
		}

		for( Session session : sessions )
		{
			if( session.getId().equals( sessionID ) )
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
		catch( NullPointerException e )
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
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean relateUsernameWithSession( String username, Session session )
	{
		for( SnapClient snapClient : onlineSnapClients )
		{
			if( snapClient.getUsername().equals( username ) )
			{
				try
				{
					snapClientSessionMap.put( snapClient, session );

					return Boolean.TRUE;
				}
				catch( Exception e )
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

	@Override
	public Boolean saveMessage( SnapClientTextMessage userTextMessage )
	{
		SnapClient snapClient = this.getOnlineSnapClientWithUsername( userTextMessage.getSenderUsername() );

		if( snapClient != null )
		{
			userTextMessage.setMessageId( UUID.randomUUID().toString() );
			
			List< SnapClientTextMessage > snapClientMessageList = snapClientTextMessageMap.get( snapClient );
			
			snapClientMessageList.add( userTextMessage );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public SnapClient getOnlineSnapClientWithUsername( String username )
	{
		try
		{
			for( SnapClient snapClient : onlineSnapClients )
			{
				if( snapClient.getUsername().equals( username ) )
				{
					return snapClient;
				}
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return null;
	}
	
	@Override
	public SnapClient getRegisteredSnapClientWithUsername( String username )
	{
		try
		{
			for( SnapClient snapClient : registeredSnapClients )
			{
				if( snapClient.getUsername().equals( username ) )
				{
					return snapClient;
				}
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return null;
	}

	@Override
	public Set< Session > getSessionsConnectedWith( SnapClient snapClient )
	{
		Set< Session > connectedSessionsWithSnapClient = new HashSet< Session >();

		String snapClientLocation = snapClient.getLocationName();

		try
		{
			for( SnapClient onlineSnapClient : onlineSnapClients )
			{
				if( onlineSnapClient.getLocationName().equals( snapClient.getLocationName() ) )
				{
					Session onlineSnapClientSession = snapClientSessionMap.get( onlineSnapClient );

					connectedSessionsWithSnapClient.add( onlineSnapClientSession );
				}
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return connectedSessionsWithSnapClient;
	}

}
