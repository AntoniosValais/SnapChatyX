package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.FriendListController;

/*
*
* @Author AntoniosValais
*
*/

public class FriendListControllerTests
{
	private SnapClient userRequested;
	
	private SnapClient friendListedUser;
	
	private FriendListController controller;
	
	private Boolean actionResult;
	
	@Before
	public void setUp()
	{
		userRequested = new SnapClient();
		
		userRequested.setUsername( "Antonios" );
		
		friendListedUser = new SnapClient();
		
		friendListedUser.setUsername( "Valais" );

		DataAccessObject.DAO.registerSnapClient( userRequested );

		DataAccessObject.DAO.registerSnapClient( friendListedUser );
		
		controller = new FriendListController();
	}
	
	@After
	public void cleanUp()
	{
		DataAccessObject.DAO.registeredSnapClients.remove( userRequested );

		DataAccessObject.DAO.registeredSnapClients.remove( friendListedUser );
	}
	
	@Test
	public void userInFriendListIsSuccessfullyRemoved()
	{
		userRequested.addToFriendList( friendListedUser.getUsername() );
		
		actionResult = controller.removeFromFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		assertTrue( actionResult );
	}
	
	@Test
	public void removeUserThatIsNotInFriendList()
	{
		actionResult = controller.removeFromFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void removeUserFromFriendListTwice()
	{
		userRequested.addToFriendList( friendListedUser.getUsername() );
		
		controller.removeFromFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		actionResult = controller.removeFromFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		assertFalse( actionResult );	
	}
	
	@Test
	public void userSuccessfulyAddedOnFriendList()
	{
		actionResult = controller.addToFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		assertTrue( actionResult );
	}
	
	@Test
	public void userIsRemovedFromBlackListIfHeIsAddedOnFriendList()
	{
		userRequested.addToBlackList( friendListedUser.getUsername() );
		
		controller.addToFriendList( userRequested.getUsername(), friendListedUser.getUsername() );
		
		actionResult = userRequested.getBlackList().contains( friendListedUser );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void addOnFriendListDoesNotCollapseWithUnexistedUsername()
	{
		actionResult = controller.addToFriendList( "Tonio", friendListedUser.getUsername() );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void addOnFriendListDoesNotCollapseWithNullParameter()
	{
		actionResult = controller.addToFriendList( null, null );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void removeFromFriendListDoesNotCollapseWithUnexistedUsername()
	{
		actionResult = controller.removeFromFriendList( "Tonio", friendListedUser.getUsername() );
		
		assertFalse( actionResult );
	}
	
	@Test
	public void removeFromFriendListDoesNotCollapseWithNullParameter()
	{
		actionResult = controller.removeFromFriendList( null, null );
		
		assertFalse( actionResult );
	}
}
