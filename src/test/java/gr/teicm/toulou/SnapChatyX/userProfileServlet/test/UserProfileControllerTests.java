package gr.teicm.toulou.SnapChatyX.userProfileServlet.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.InterfaceUserProfile;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.UserProfile;
import gr.teicm.toulou.SnapChatyX.userProfileServlet.UserProfileController;

/*
*
* @Author AntoniosValais
*
*/

public class UserProfileControllerTests
{
	private UserProfileController controller;

	private SnapClient user;
	
	private InterfaceUserProfile expectedUserProfile;
	
	private InterfaceUserProfile actualUserProfile;
	
	@Before
	public void setUp()
	{
		user = new SnapClient();
		
		user.setUsername( "Antonios" );
		
		DataAccessObject.DAO.registerSnapClient( user );
		
		controller = new UserProfileController();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( user );
	}
	
	@Test
	public void returnedUserProfileIsTheRequested()
	{
		expectedUserProfile = new UserProfile( user );
		
		actualUserProfile = controller.getUserProfile( user.getUsername() );
		
		assertEquals( expectedUserProfile.getUserName(), actualUserProfile.getUserName() );
		
		assertEquals( expectedUserProfile.getLocation(), actualUserProfile.getLocation() );
	}
	
	@Test
	public void controllerDoesNotCollapseWithNullParameter()
	{
		actualUserProfile = controller.getUserProfile( null );
		
		assertNull( actualUserProfile );
	}
	
	@Test
	public void controllerReturnsNullOnInvalidUsername()
	{
		actualUserProfile = controller.getUserProfile( "Tonio" );
		
		assertNull( actualUserProfile );
	}
}
