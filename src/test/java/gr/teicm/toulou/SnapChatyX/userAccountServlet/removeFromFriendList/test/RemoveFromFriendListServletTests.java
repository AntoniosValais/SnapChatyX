package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.RemoveFriendResult;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.RemoveFromFriendListServlet;

import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*
*
* @Author AntoniosValais
*
*/

public class RemoveFromFriendListServletTests
{
	private RemoveFromFriendListServlet servlet;
	
	private MultivaluedMap<String, String> parameters;
	
	private Response actualResponse;
	
	private Gson gson;
	
	private String expectedResponseJSON;
	
	private SnapClient userRequested;
	
	private SnapClient friendListedUser;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		servlet = new RemoveFromFriendListServlet();
		
		parameters = new MultivaluedHashMap<String, String>();
		
		gson = new Gson();
		
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		friendListedUser = new SnapClient();
		
		friendListedUser.setUsername( "Valais" );
		
		dataAccessObject = DataAccessObject.DAO;
		
		dataAccessObject.registerSnapClient( userRequested );
		
		dataAccessObject.registerSnapClient( friendListedUser );
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( userRequested );
		
		dataAccessObject.unregisterSnapClient( friendListedUser );		
	}
	
	@Test
	public void servletReturnsProperJSONResponseWhenValidRemoveFromFriendListRequested()
	{
		userRequested.addToFriendList( friendListedUser.getUsername() );
		
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", friendListedUser.getUsername() );
		
		expectedResponseJSON = gson.toJson( new RemoveFriendResult( Boolean.TRUE ) );
		
		actualResponse = servlet.removeFromFriendList( parameters );
		
		assertEquals( expectedResponseJSON, actualResponse.getEntity() );		
	}
	
	@Test
	public void servletReturnsProperResponseStatusWhenValidRemoveFromFriendListRequested()
	{
		userRequested.addToFriendList( friendListedUser.getUsername() );
		
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", friendListedUser.getUsername() );
		
		actualResponse = servlet.removeFromFriendList( parameters );
		
		assertEquals( Status.OK.getStatusCode() , actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseIsBadRequestWhenUserIsNotInFriendList()
	{	
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", friendListedUser.getUsername() );
		
		actualResponse = servlet.removeFromFriendList( parameters );
		
		assertEquals( Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseEntityIsFailedWhenUserIsNotInFriendkList()
	{
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", friendListedUser.getUsername() );
		
		actualResponse = servlet.removeFromFriendList( parameters );
		
		RemoveFriendResult expected = new RemoveFriendResult( Boolean.FALSE );
		
		assertEquals( gson.toJson( expected ), actualResponse.getEntity() );		
	}
	
}
