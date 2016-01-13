package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.test;

import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
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
	
	@Before
	public void setUp()
	{
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		blackListedUser = new SnapClient();
		
		blackListedUser.setUsername( "Valais" );
		
		DataAccessObject.DAO.registerSnapClient( userRequested );
		
		DataAccessObject.DAO.registerSnapClient( blackListedUser );
		
		controller = new BlackListController();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( userRequested );
		
		DataAccessObject.DAO.registeredSnapClients.remove( blackListedUser );
	}
	
	@Test
	public void userInBlackListSuccessfulyRemovedFromBlackList()
	{
		userRequested.addToBlackList( blackListedUser );
		
		actionResult = controller.removeFromBlackList( userRequested.getUsername(), blackListedUser.getUsername() );
		
		assertTrue( actionResult );		
	}
	
	@Test
	public void userInBlackListCanNotBeRemovedTwice()
	{
		userRequested.addToBlackList( blackListedUser );
		
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
		userRequested.addToFriendList( blackListedUser );
		
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
