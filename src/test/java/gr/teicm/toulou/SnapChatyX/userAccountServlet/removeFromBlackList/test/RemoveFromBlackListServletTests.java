package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.RemoveFromBlackListResult;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.RemoveFromBlackListServlet;

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


public class RemoveFromBlackListServletTests
{
	private RemoveFromBlackListServlet servlet;
	
	private MultivaluedMap<String, String> parameters;
	
	private Response actualResponse;
	
	private Gson gson;
	
	private String expectedResponseJSON;
	
	private SnapClient userRequested;
	
	private SnapClient blackListedUser;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		servlet = new RemoveFromBlackListServlet();
		
		parameters = new MultivaluedHashMap<String, String>();
		
		gson = new Gson();
		
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		blackListedUser = new SnapClient();
		
		blackListedUser.setUsername( "Valais" );
		
		dataAccessObject = DataAccessObject.DAO;
		
		dataAccessObject.registerSnapClient( userRequested );
		
		dataAccessObject.registerSnapClient( blackListedUser );
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( userRequested );
		
		dataAccessObject.unregisterSnapClient( blackListedUser );		
	}
	
	@Test
	public void servletReturnsProperJSONResponseWhenValidRemoveFromBlackListRequested()
	{
		userRequested.addToBlackList( blackListedUser.getUsername() );
		
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", blackListedUser.getUsername() );
		
		expectedResponseJSON = gson.toJson( new RemoveFromBlackListResult( Boolean.TRUE ) );
		
		actualResponse = servlet.removeFromBlackList( parameters );
		
		assertEquals( expectedResponseJSON, actualResponse.getEntity() );
	}
	
	@Test
	public void servletReturnsProperResponseStatusWhenValidRemoveFromBlackListRequested()
	{
		userRequested.addToBlackList( blackListedUser.getUsername() );
		
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", blackListedUser.getUsername() );
		
		actualResponse = servlet.removeFromBlackList( parameters );
		
		assertEquals( Status.OK.getStatusCode() , actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseIsBadRequestWhenUserIsNotInBlackList()
	{
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", blackListedUser.getUsername() );
		
		actualResponse = servlet.removeFromBlackList( parameters );
		
		assertEquals( Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseEntityIsFailedWhenUserIsNotInBlackList()
	{
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "removeUser", blackListedUser.getUsername() );
		
		actualResponse = servlet.removeFromBlackList( parameters );
		
		RemoveFromBlackListResult expected = new RemoveFromBlackListResult( Boolean.FALSE );
		
		assertEquals( gson.toJson( expected ), actualResponse.getEntity() );		
	}
}
