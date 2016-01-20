package gr.teicm.toulou.SnapChatyX.userAccountServlet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.UserAccountServlet;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*
*
* @Author AntoniosValais
*
*/

public class UserAccountServletTests
{
	private UserAccountServlet servlet;

	private Response actualServletResponse;
	
	private String responseJSON;
	
	private SnapClient user;
	
	private Gson gson;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		user = new SnapClient();
		
		gson = new Gson();
		
		servlet = new UserAccountServlet();
		
		dataAccessObject = DataAccessObject.DAO;
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( user );	
	}
	
	@Test
	public void servletReturnsOKResponseOnValidUsernameRequest()
	{
		user.setUsername( "Antonios" );
		
		dataAccessObject.registerSnapClient( user );
		
		responseJSON = gson.toJson( user );
		
		actualServletResponse = servlet.getUserAccount( "Antonios" );
		
		assertEquals( Status.OK.getStatusCode() , actualServletResponse.getStatus() );
	}
	
	@Test
	public void servletReturnsNOTFOUNDResponseOnInvalidUsernameRequest()
	{
		actualServletResponse = servlet.getUserAccount( "Valais" );
		
		assertEquals( Status.NOT_FOUND.getStatusCode(), actualServletResponse.getStatus() );
	}
	
	@Test
	public void servletReturnsRequestedUserAccountInfo()
	{
		user.setUsername( "Antonios" );
		
		dataAccessObject.registerSnapClient( user );
		
		responseJSON = gson.toJson( user );
		
		actualServletResponse = servlet.getUserAccount( "Antonios" );
		
		assertEquals( responseJSON, actualServletResponse.getEntity() );
	}
}
