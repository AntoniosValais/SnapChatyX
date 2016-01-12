package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList.test;

import static org.junit.Assert.*;

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
import gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList.AddBlackListResult;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList.AddToBlackListServlet;

/*
*
* @Author AntoniosValais
*
*/

public class AddToBlackListServletTests
{
	private AddToBlackListServlet servlet;
	
	private Response actualResponse;
	
	private String expectedResponseJSON;
	
	private SnapClient userRequested;
	
	private SnapClient userOnBlackList;
	
	private MultivaluedMap<String, String> parameters;
	
	private Gson gson;
	
	
	@Before
	public void setUp()
	{
		servlet = new AddToBlackListServlet();
		
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		userOnBlackList = new SnapClient();
		
		userOnBlackList.setUsername( "Valais" );
		
		DataAccessObject.DAO.registerSnapClient( userRequested );
		
		DataAccessObject.DAO.registerSnapClient( userOnBlackList );
		
		parameters = new MultivaluedHashMap<String, String>();
		
		gson = new Gson();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( userRequested );
		
		DataAccessObject.DAO.registeredSnapClients.remove( userOnBlackList );
	}
	
	@Test
	public void servletReturnsProperJSONResponseWhenValidAddToBlackListRequested()
	{
		parameters.add( "user", userRequested.getUsername() );
		
		parameters.add( "hatesUser", userOnBlackList.getUsername() );
		
		expectedResponseJSON = gson.toJson( new AddBlackListResult( Boolean.TRUE ) );
		
		actualResponse = servlet.addToBlackList( parameters );
		
		assertEquals( expectedResponseJSON, actualResponse.getEntity() );
	}
	
	@Test
	public void servletResponseEntityIsFailedWhenPassedInvalidUsername()
	{
		parameters.add( "user", "Tonio" );
		
		parameters.add( "removeUser", userOnBlackList.getUsername() );
		
		actualResponse = servlet.addToBlackList( parameters );
		
		InterfaceServletResult expected = new AddBlackListResult( Boolean.FALSE );
		
		assertEquals( gson.toJson( expected ), actualResponse.getEntity() );		
	}
	
	@Test
	public void servletResponseIsBADREQUESTWhenPassedNullParameter()
	{
		actualResponse = servlet.addToBlackList( null );
				
		assertEquals( Status.BAD_REQUEST.getStatusCode() , actualResponse.getStatus() );			
	}
	
}
