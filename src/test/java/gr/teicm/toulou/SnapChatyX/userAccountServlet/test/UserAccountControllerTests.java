package gr.teicm.toulou.SnapChatyX.userAccountServlet.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceUserAccount;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.UserAccount;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.UserAccountController;

/*
*
* @Author AntoniosValais
*
*/

public class UserAccountControllerTests
{
	private SnapClient snapClient;
	
	private InterfaceUserAccount expectedUserAccount;
	
	private UserAccountController controller;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		snapClient = new SnapClient();
		
		controller = new UserAccountController();
		
		dataAccessObject = DataAccessObject.DAO;
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( snapClient );		
	}
	
	@Test
	public void controllerReturnsValidUserAccount()
	{
		snapClient.setUsername( "Antonios" );
		
		expectedUserAccount = new UserAccount( snapClient );
		
		dataAccessObject.registerSnapClient( snapClient );
		
		InterfaceUserAccount returnedUserAccount = controller.getUserAccount( "Antonios" );
		
		assertEquals( expectedUserAccount.getUsername(), returnedUserAccount.getUsername() );
	}
	
	@Test
	public void controllerReturnsNullUserAccount()
	{
		snapClient.setUsername( "Antonios" );
		
		dataAccessObject.registerSnapClient( snapClient );
		
		InterfaceUserAccount returnedUserAccount = controller.getUserAccount( "Valais" );
		
		assertNull( returnedUserAccount );
	}
	
	@Test
	public void controllerReturnsNullOnEmptyRegisteredList()
	{
		InterfaceUserAccount returnedUserAccount = controller.getUserAccount( "Antonios" );
		
		assertNull( returnedUserAccount );
	}
}
