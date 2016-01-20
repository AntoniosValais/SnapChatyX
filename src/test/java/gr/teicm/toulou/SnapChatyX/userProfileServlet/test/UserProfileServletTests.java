package gr.teicm.toulou.SnapChatyX.userProfileServlet.test;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.InterfaceUserProfile;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.UserProfile;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.UserProfileServlet;


/*
*
* @Author AntoniosValais
*
*/

public class UserProfileServletTests
{
	private UserProfileServlet servlet;
	
	private Gson gson;
	
	private String expectedJSONResponse;
	
	private Response actualResponse;
	
	private InterfaceUserProfile expectedUserProfile;
	
	private SnapClient user;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		servlet = new UserProfileServlet();
		
		gson = new Gson();
		
		user = new SnapClient();
		
		user.setUsername( "Antonios" );
		
		dataAccessObject = DataAccessObject.DAO;
		
		dataAccessObject.registerSnapClient( user );
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( user );
	}
	
	@Test
	public void servletResponseJSONHasTheInfoOfTheRequestedProfile()
	{
		actualResponse = servlet.getUserProfile( user.getUsername() );
		
		expectedUserProfile = new UserProfile( user );
		
		expectedJSONResponse = gson.toJson( expectedUserProfile );
		
		assertEquals( expectedJSONResponse, actualResponse.getEntity() );
	}
	
	@Test
	public void servletResponseStatusIsOKWithProperRequestedProfile()
	{
		actualResponse = servlet.getUserProfile( user.getUsername() );
		
		assertEquals( Status.OK.getStatusCode(), actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseStatusIsBADREQUESTWithInvalidProfile()
	{
		actualResponse = servlet.getUserProfile( "Tonio" );
		
		assertEquals( Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus() );
	}
	
	@Test
	public void servletResponseStatusIsBADREQUESTWithNullParameter()
	{
		actualResponse = servlet.getUserProfile( null );
		
		assertEquals( Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus() );
	}
}
