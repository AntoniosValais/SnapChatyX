package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.SessionFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Session;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class SessionFilterController
{
	private InterfaceDataAccessObject dataAccessObject;
	
	private Set< SnapClient > onlineSnapClients;
	
	private SnapClient snapClient;
	
	private HashMap< SnapClient, Session > snapClientSessionMap;
	
	private List< InterfaceSnapClientFilter > clientFilters;
	
	public SessionFilterController()
	{
		dataAccessObject = DataAccessObject.DAO;
		
		onlineSnapClients = dataAccessObject.getOnlineSnapClients();
		
		snapClientSessionMap = dataAccessObject.getSnapClientSessionMap();
		
		clientFilters = new ArrayList< InterfaceSnapClientFilter >();
			clientFilters.add( new FilterByLocation() );
			clientFilters.add( new FilterByBlackList() );
		
	}
	
	public Set< Session > getSessionsConnectedWith( SnapClient snapClient )
	{
		Set< Session > connectedSessionsWithSnapClient = new HashSet< Session >();
		
		Set< SnapClient > connectedWithSnapClient = new HashSet< SnapClient >();
		
		this.snapClient = snapClient;

		try
		{
			for( InterfaceSnapClientFilter clientFilter : clientFilters )
			{
				clientFilter.addCorrelatedSnapClientsToSet( connectedWithSnapClient );
			}

			connectedWithSnapClient.add( snapClient );
			
			for( SnapClient connectedClient : connectedWithSnapClient )
			{
				Session connectedSession = snapClientSessionMap.get( connectedClient );
				
				connectedSessionsWithSnapClient.add( connectedSession );
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return connectedSessionsWithSnapClient;
	}
	
	private class FilterByLocation implements InterfaceSnapClientFilter
	{

		public FilterByLocation()
		{
			
		}

		@Override
		public Boolean addCorrelatedSnapClientsToSet( Set< SnapClient > connectedWithSnapClients )
		{
			try
			{				
				String snapClientLocation = snapClient.getLocationName();
				
				for( SnapClient onlineSnapClient : onlineSnapClients )
				{
					if( onlineSnapClient.getLocationName().equals( snapClientLocation ) )
					{
						connectedWithSnapClients.add( onlineSnapClient );
					}
				}
				
				return Boolean.TRUE;
			}
			catch( Exception e )
			{
				return Boolean.FALSE;
			}
		}
	}
	
	private class FilterByBlackList implements InterfaceSnapClientFilter
	{
		public FilterByBlackList()
		{
			
		}
		
		@Override
		public Boolean addCorrelatedSnapClientsToSet( Set< SnapClient > connectedWithSnapClients )
		{
			try
			{				
				List< SnapClient > snapClientsOnSendersBlackList = snapClient.getBlackList();
				
				for( SnapClient snapClientOnBlackList : snapClientsOnSendersBlackList )
				{
					if( connectedWithSnapClients.contains( snapClientOnBlackList ) )
					{
						connectedWithSnapClients.remove( snapClientOnBlackList );
					}
				}
				
				for( SnapClient connectedSnapClient : connectedWithSnapClients )
				{
					List< SnapClient > snapClientsOnReceiversBlackList = connectedSnapClient.getBlackList();
					
					if( snapClientsOnReceiversBlackList.contains( snapClient ) )
					{
						connectedWithSnapClients.remove( connectedSnapClient );
					}
				}
				
				return Boolean.TRUE;
			}
			catch( Exception e )
			{
				return Boolean.FALSE;
			}
		}
		
	}
}
