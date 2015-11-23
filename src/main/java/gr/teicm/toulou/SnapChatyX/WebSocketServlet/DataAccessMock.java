package gr.teicm.toulou.SnapChatyX.WebSocketServlet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

/*
*
* @Author AntoniosValais
*
*/

public enum DataAccessMock implements InterfaceDataAccessObject
{
	DAO;
	
	private Set< Session > sessions;
	private Set< SnapClient > onlineSnapClients;
	private HashMap< SnapClient, Session > snapClientSessionMap; 
	
	
	private DataAccessMock()
	{
		sessions = new HashSet< Session >();
		
		onlineSnapClients = new HashSet< SnapClient >();
		
		SnapClient snapClient1 = new SnapClient();
		SnapClient snapClient2 = new SnapClient();
		
		snapClient1.setUsername( "Antonios" );
		snapClient2.setUsername( "Valais" );
		
		onlineSnapClients.add( snapClient1 );
		onlineSnapClients.add( snapClient2 );
		
		snapClientSessionMap = new HashMap< SnapClient, Session >();
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
			
			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean relateUsernameWithSession(String username, Session session )
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
}
