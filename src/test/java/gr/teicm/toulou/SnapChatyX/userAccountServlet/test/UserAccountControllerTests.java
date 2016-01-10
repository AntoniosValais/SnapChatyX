package gr.teicm.toulou.SnapChatyX.userAccountServlet.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
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
	
	@Before
	public void setUp()
	{
		snapClient = new SnapClient();
		
		controller = new UserAccountController();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( snapClient );		
	}
	
	@Test
	public void controllerReturnsValidUserAccount()
	{
		snapClient.setUsername( "Antonios" );
		
		expectedUserAccount = new UserAccount( snapClient );
		
		DataAccessObject.DAO.registerSnapClient( snapClient );
		
		InterfaceUserAccount returnedUserAccount = controller.getUserAccount( "Antonios" );
		
		assertEquals( expectedUserAccount.getUsername(), returnedUserAccount.getUsername() );
	}
	
	@Test
	public void controllerReturnsNullUserAccount()
	{
		snapClient.setUsername( "Antonios" );
		
		DataAccessObject.DAO.registerSnapClient( snapClient );
		
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
