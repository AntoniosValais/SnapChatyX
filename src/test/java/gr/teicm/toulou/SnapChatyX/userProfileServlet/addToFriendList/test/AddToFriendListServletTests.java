package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToFriendList.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.addToFriendListServlet.AddFriendResult;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.addToFriendListServlet.AddToFriendListServlet;

/*
*
* @Author AntoniosValais
*
*/

public class AddToFriendListServletTests
{
	private AddToFriendListServlet servlet;
	
	private Response actualResponse;
	
	private String expectedResponseJSON;
	
	private SnapClient userRequested;
	
	private SnapClient userOnFriendList;
	
	private MultivaluedMap<String, String> parameters;
	
	private Gson gson;

	@Before
	public void setUp()
	{
		servlet = new AddToFriendListServlet();
		
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		userOnFriendList = new SnapClient();
		
		userOnFriendList.setUsername( "Valais" );
		
		DataAccessObject.DAO.registerSnapClient( userRequested );
		
		DataAccessObject.DAO.registerSnapClient( userOnFriendList );
		
		parameters = new MultivaluedHashMap<String, String>();
		
		gson = new Gson();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( userRequested );
		
		DataAccessObject.DAO.registeredSnapClients.remove( userOnFriendList );
	}
	
	@Test
	public void servletReturnsProperJSONResponseWhenValidAddToFriendListRequested()
	{
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "lovesUser", userOnFriendList.getUsername() );
		
		expectedResponseJSON = gson.toJson( new AddFriendResult( Boolean.TRUE ) );
		
		actualResponse = servlet.addToFriendList( parameters );
		
		assertEquals( expectedResponseJSON, actualResponse.getEntity() );
	}
	
	@Test
	public void servletResponseIsBADREQUESTWhenPassedNullParameter()
	{
		actualResponse = servlet.addToFriendList( null );
				
		assertEquals( Status.BAD_REQUEST.getStatusCode() , actualResponse.getStatus() );			
	}
	
	@Test
	public void servletResponseEntityIsFailedWhenPassedInvalidUsername()
	{
		parameters.add( "user", "Tonio" );
		
		parameters.add( "removeUser", userOnFriendList.getUsername() );
		
		actualResponse = servlet.addToFriendList( parameters );
		
		InterfaceServletResult expected = new AddFriendResult( Boolean.FALSE );
		
		assertEquals( gson.toJson( expected ), actualResponse.getEntity() );		
	}
}
