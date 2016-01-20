package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.test;

import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.BlackListController;

import static org.junit.Assert.*;

import org.junit.After;

/*
*
* @Author AntoniosValais
*
*/

public class BlackListControllerTests
{
	private SnapClient userRequested;
	
	private SnapClient blackListedUser;
	
	private BlackListController controller;
	
	private Boolean actionResult;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void setUp()
	{
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		blackListedUser = new SnapClient();
		
		blackListedUser.setUsername( "Valais" );
		
		dataAccessObject = DataAccessObject.DAO;
		
		dataAccessObject.registerSnapClient( userRequested );
		
		dataAccessObject.registerSnapClient( blackListedUser );
		
		controller = new BlackListController();
	}
	
	@After
	public void cleanUp()
	{
		dataAccessObject.unregisterSnapClient( userRequested );
		
		dataAccessObject.unregisterSnapClient( blackListedUser );
	}
	
	@Test
	public void userInBlackListSuccessfulyRemovedFromBlackList()
	{
		userRequested.addToBlackList( blackListedUser.getUsername() );
		
		actionResult = controller.removeFromBlackList( userRequested.getUsername(), blackListedUser.getUsername() );
		
		assertTrue( actionResult );		
	}
	
	@Test
	public void userInBlackListCanNotBeRemovedTwice()
	{
		userRequested.addToBlackList( blackListedUser.getUsername() );
		
		controller.removeFromBlackList(  userRequested.getUsername(), blackListedUser.getUsername() );
	
		actionResult = controller.removeFromBlackList( userRequested.getUsername(), blackListedUser.getUsername() );

		assertFalse( actionResult );
	}
	
	@Test
	public void cannotRemoveUserThatIsNotInBlackList()
	{
		actionResult = controller.removeFromBlackList( userRequested.getUsername(), blackListedUser.getUsername() );

		assertFalse( actionResult );
	}
	
	@Test
	public void userSuccessfulyAddedOnBlackList()
	{
		actionResult = controller.addToBlackList( userRequested.getUsername(),  blackListedUser.getUsername() );
		
		assertTrue( actionResult );
	}
	
	@Test
	public void userIsRemovedIfHeIsOnFriendListWhenAddedOnBlackList()
	{
		userRequested.addToFriendList( blackListedUser.getUsername() );
		
		controller.addToBlackList( userRequested.getUsername(), blackListedUser.getUsername() );
		
		actionResult = userRequested.getFriendList().contains( blackListedUser );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void addOnBlackListDoesNotCollapseWithUnexcistedUsername()
	{
		actionResult = controller.addToBlackList( "Tonio", blackListedUser.getUsername() );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void removeFromBlackListDoesNotCollapseWithUnexistedUsername()
	{
		actionResult = controller.removeFromBlackList( "Tonio", blackListedUser.getUsername() );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void removeFromBlackListDoesNotCollapseWithNullParameter()
	{
		actionResult = controller.removeFromBlackList( null, null );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void addOnBlackListDoesNotCollapseWithNullParameter()
	{
		actionResult = controller.addToBlackList( null, null );
		
		assertFalse( actionResult );
	}
	
}
