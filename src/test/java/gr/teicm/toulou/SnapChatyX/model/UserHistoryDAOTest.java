package gr.teicm.toulou.SnapChatyX.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

/**
 * 
 * 
 * @since Dec 14, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryDAOTest {
	
	private String testMitsosUsername;
	
	private IUserHistoryDAO dao;
	
	@Before
	public void setUp() {
		
		testMitsosUsername = "test-mitsos";
		
		dao = DataAccessObject.DAO;
		
	}
	
	@Test
	public void testUsernameExistsInUserHistoryListSuccess() {
		
		// set up
		String username = testMitsosUsername;
		
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();		
		messageList.add(message1);
		messageList.add(message2);
		messageList.add(message3);
		
		IUserHistory userHistory = new UserHistory(username, messageList);
		
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			dao.getUserHistoryList().add(userHistory);
			
		}
		
		// test
		Assert.assertTrue(dao.usernameExistsInUserHistoryList(username));
		
		// tear down
		int index = -1;
		
		usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				// identify the entry with the username that was initialized.
				index = dao.getUserHistoryList().indexOf(uh);
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (usernameIsEntry) {
			
			// remove the entry with the username that was initialized.
			dao.getUserHistoryList().remove(index);
			
		}
		
	}
	
	@Test
	public void testUsernameExistsInUserHistoryListParamIsNotEntry() {
		
		// set up
		String username = testMitsosUsername;
		
		// test
		boolean usernameIsEntry = dao.usernameExistsInUserHistoryList(username);
		
		Assert.assertFalse(usernameIsEntry);
		
	}
	
	@Test
	public void testUsernameExistsInUserHistoryListParamIsNullOrEmpty() {
		
		boolean usernameIsNotNull = dao.usernameExistsInUserHistoryList(null);
		
		boolean usernameIsNotEmpty = dao.usernameExistsInUserHistoryList("");
		
		Assert.assertFalse(usernameIsNotNull);
		Assert.assertFalse(usernameIsNotEmpty);
		
	}
	
	@Test
	public void testAddUserHistoryByUsernameSuccess() {
		
		// set up
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(testMitsosUsername);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(testMitsosUsername);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(testMitsosUsername);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();		
		messageList.add(message1);
		messageList.add(message2);
		messageList.add(message3);
		
		IUserHistory expected = new UserHistory(testMitsosUsername, messageList);
		
		// test
		boolean objectAdded = dao.addUserHistoryByUsername(expected);
		
		Assert.assertTrue(objectAdded);
		
		IUserHistory actual = null;
		
		if (objectAdded) {
			
			int index = dao.getUserHistoryList().indexOf(expected);
			
			actual = dao.getUserHistoryList().get(index);
			
		}
		
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected, actual);
		
		// tear down
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(testMitsosUsername)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (usernameIsEntry) {
			
			// identify the entry with the username that was initialized.
			int index = dao.getUserHistoryList().indexOf(actual);
			
			// remove the entry with the username that was initialized.
			dao.getUserHistoryList().remove(index);
			
		}
		
	}
	
	@Test
	public void testAddUserHistoryByUsernameParamNull() {
		boolean objectAdded = dao.addUserHistoryByUsername(null);
		
		Assert.assertFalse(objectAdded);
	}
	
	@Test
	public void testAddUserHistoryByUsernameParamUsernameIsEntry() {
		
		// set up
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(testMitsosUsername);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(testMitsosUsername);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(testMitsosUsername);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList1 = new ArrayList<>();		
		messageList1.add(message1);
		messageList1.add(message2);
		messageList1.add(message3);
		
		UserHistory userHistory1 = new UserHistory(testMitsosUsername, messageList1);
		
		dao.addUserHistoryByUsername(userHistory1);
		
		// test
		SnapClientTextMessage message4 = new SnapClientTextMessage();
		message4.setSenderUsername(testMitsosUsername);
		message4.setMessageText("Yes.");
		message4.setTimeToLive(15);
		message4.setMessageId("4");
		
		SnapClientTextMessage message5 = new SnapClientTextMessage();
		message5.setSenderUsername(testMitsosUsername);
		message5.setMessageText("No.");
		message5.setTimeToLive(10);
		message5.setMessageId("5");
		
		SnapClientTextMessage message6 = new SnapClientTextMessage();
		message6.setSenderUsername(testMitsosUsername);
		message6.setMessageText("Probably.");
		message6.setTimeToLive(5);
		message6.setMessageId("6");
		
		List<SnapClientTextMessage> messageList2 = new ArrayList<>();		
		messageList2.add(message4);
		messageList2.add(message5);
		messageList2.add(message6);
		
		UserHistory userHistory2 = new UserHistory(testMitsosUsername, messageList2);
		
		Assert.assertFalse(dao.addUserHistoryByUsername(userHistory2));
		
		// tear down
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(testMitsosUsername)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			// identify the entry with the username that was initialized.
			int index = dao.getUserHistoryList().indexOf(userHistory1);
			
			// remove the entry with the username that was initialized.
			dao.getUserHistoryList().remove(index);
			
		}
		
	}
	
	@Test
	public void testGetUserHistoryByUsernameSuccess() {
		
		// set up
		String username = testMitsosUsername;
		
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();		
		messageList.add(message1);
		messageList.add(message2);
		messageList.add(message3);
		
		UserHistory expected = new UserHistory(username, messageList);
		
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			dao.getUserHistoryList().add(expected);
			
		}
		
		// test
		IUserHistory actual = dao.getUserHistoryByUsername(username);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected, actual);
		
		// tear down
		usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(testMitsosUsername)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			int index = dao.getUserHistoryList().indexOf(actual);
			
			dao.getUserHistoryList().remove(index);
		}
		
	}
	
	@Test
	public void testGetUserHistoryByUsernameFailure() {
		
		IUserHistory result = dao.getUserHistoryByUsername(testMitsosUsername);
		
		Assert.assertNull(result);
		
	}
	
	@Test
	public void testUpdateUserHistoryByUsernameSuccess() {
		
		// set up
		String username = testMitsosUsername;
		
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList1 = new ArrayList<>();		
		messageList1.add(message1);
		messageList1.add(message2);
		messageList1.add(message3);
		
		UserHistory userHistory = new UserHistory(username, messageList1);
		
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			dao.getUserHistoryList().add(userHistory);
			
		}
		
		// test
		SnapClientTextMessage message4 = new SnapClientTextMessage();
		message4.setSenderUsername(username);
		message4.setMessageText("Yes.");
		message4.setTimeToLive(15);
		message4.setMessageId("4");
		
		SnapClientTextMessage message5 = new SnapClientTextMessage();
		message5.setSenderUsername(username);
		message5.setMessageText("No.");
		message5.setTimeToLive(10);
		message5.setMessageId("5");
		
		SnapClientTextMessage message6 = new SnapClientTextMessage();
		message6.setSenderUsername(username);
		message6.setMessageText("Probably.");
		message6.setTimeToLive(5);
		message6.setMessageId("6");
		
		List<SnapClientTextMessage> messageList2 = new ArrayList<>();		
		messageList2.add(message4);
		messageList2.add(message5);
		messageList2.add(message6);
		
		IUserHistory expected = new UserHistory(username, messageList2);
		
		Assert.assertTrue(dao.updateUserHistoryByUsername(expected));
		
		int index = dao.getUserHistoryList().indexOf(expected);
		
		IUserHistory actual = dao.getUserHistoryList().get(index);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected, actual);
		
		// tear down
		index = -1;
		
		usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				// identify the entry with the username that was initialized.
				index = dao.getUserHistoryList().indexOf(uh);
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (usernameIsEntry) {
			
			// remove the entry with the username that was initialized.
			dao.getUserHistoryList().remove(index);
			
		}
		
	}
	
	@Test
	public void testUpdateUserHistoryByUsernameParamNull() {
		
		boolean objectUpdated = dao.updateUserHistoryByUsername(null);
		
		Assert.assertFalse(objectUpdated);
		
	}
	
	@Test
	public void testUpdateUserHistoryByUsernameParamUsernameIsNotEntry() {
		
		// set up
		String username = testMitsosUsername;
		
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList1 = new ArrayList<>();		
		messageList1.add(message1);
		messageList1.add(message2);
		messageList1.add(message3);
		
		UserHistory userHistory = new UserHistory(username, messageList1);
		
		boolean objectUpdated = dao.updateUserHistoryByUsername(userHistory);
		
		Assert.assertFalse(objectUpdated);
		
	}
	
	@Test
	public void testDeleteUserHistoryByUsernameSuccess() {
		
		// set up
		String username = testMitsosUsername;
		
		SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		List<SnapClientTextMessage> messageList1 = new ArrayList<>();		
		messageList1.add(message1);
		messageList1.add(message2);
		messageList1.add(message3);
		
		UserHistory userHistory = new UserHistory(username, messageList1);
		
		boolean usernameIsEntry = false;
		
		for (IUserHistory uh : dao.getUserHistoryList()) {
			
			if (uh.getUsername().equals(username)) {
				
				usernameIsEntry = true;
				
			}
			
		}
		
		if (! usernameIsEntry) {
			
			dao.getUserHistoryList().add(userHistory);
			
		}
		
		// test
		boolean objectDeleted = dao.deleteUserHistoryByUsername(username);
		
		Assert.assertTrue(objectDeleted);
		
	}
	
	@Test
	public void testDeleteUserHistoryByUsernameParamNull() {
		
		boolean objectDeleted = dao.deleteUserHistoryByUsername(null);
		
		Assert.assertFalse(objectDeleted);
		
	}
	
	@Test
	public void testDeleteUserHistoryByUsernameParamIsNotEntry() {
		
		// set up
		String username = testMitsosUsername;
		
		// test
		boolean objectDeleted = dao.deleteUserHistoryByUsername(username);
		
		Assert.assertFalse(objectDeleted);
		
	}
	
	@After
	public void tearDown() {
		
		testMitsosUsername = null;
		
		dao = null;
		
	}
	
}
