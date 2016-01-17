package gr.teicm.toulou.SnapChatyX.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * A basic requirement for this test case to operate properly is the MongoDB service
 * of the operating system to have started on localhost and on the default port of
 * MongoDB which is 27017.
 * 
 * @since Jan 8, 2016
 * 
 * @author Stamatios Tsalikis
 * @author Konstantina Avgeri
 */
public class UserHistoryDAOTest {
	
	private static IUserHistoryDAO target;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		target = new UserHistoryDAO();
		
	}
	
	@Test
	public void testInsertUserHistorySuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		messageList.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList);
		
		// Execution
		target.insertUserHistory(entityFixture);
		
		// Verification
		final UserHistoryEntity entityResult =
				target.datastore().get(UserHistoryEntity.class, entityFixture.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entityFixture.getId(),
				entityResult.getId()
		);
		assertEquals(
				"The usernames must be equal.",
				entityFixture.getUsername(),
				entityResult.getUsername()
		);
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		// TearDown
		target.datastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.datastore().exists(entityResult)
		);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInsertUserHistoryParamNull() throws Exception {
		
		//SetUp
		
		//Execution
		target.insertUserHistory(null);
		//Verification
		
		//TearDown
		
	}
	
	@Test(expected = DataAccessException.class)
	public void testInsertUserHistoryParamNullId() throws Exception {
		
		//SetUp
		UserHistoryEntity entity = new UserHistoryEntity();
		
		//Execution
		target.insertUserHistory(entity);
		
		//Verification
		
		//TearDown
		
	}
	
	@Test(expected = DataAccessException.class)
	public void testInsertUserHistoryParamEmptyId() throws Exception {
		
		//SetUp
		UserHistoryEntity entity = new UserHistoryEntity();
		entity.setId("");
		
		//Execution
		target.insertUserHistory(entity);
		
		//Verification
		
		//TearDown
		
	}
	
	@Test
	public void testFindUserHistoryByIdSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		message.setTimeToLive(10);

		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		messageList.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList);
		
		target.datastore().save(entityFixture);
		
		// Execution
		final UserHistoryEntity entityResult =
				target.findUserHistoryById(entityFixture.getId());
		
		// Verification
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entityFixture.getId(),
				entityResult.getId()
		);
		assertEquals(
				"The usernames must be equal.",
				entityFixture.getUsername(),
				entityResult.getUsername()
		);
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		// TearDown
		target.datastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.datastore().exists(entityResult)
		);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindUserHistoryByIdParamNull() throws Exception {
		
		//SetUp
		
		//Execution
		UserHistoryEntity result = target.findUserHistoryById(null);
		
		//Verification
		assertNull(result);
		
		//TearDown
		
	}
	
	@Test(expected = DataAccessException.class)
	public void testFindUserHistoryByIdParamNoEntry() throws Exception {
		
		//SetUp
		
		//Execution
		UserHistoryEntity result = target.findUserHistoryById("1");
		
		//Verification
		assertNull(result);
		
		//TearDown
		
	}
	
	@Test
	public void testFindUserHistoryByUsernameSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		messageList.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList);
		
		target.datastore().save(entityFixture);
		
		// Execution
		final UserHistoryEntity entityResult =
				target.findUserHistoryByUsername(entityFixture.getUsername());
		
		// Verification
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals("The ids must be equal.", entityFixture.getId(), entityResult.getId());
		assertEquals(
				"The usernames must be equal.",
				entityFixture.getUsername(),
				entityResult.getUsername()
		);
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		// TearDown
		target.datastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.datastore().exists(entityResult)
		);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindUserHistoryByUsernameParamNull() throws Exception {
		
		//SetUp
		
		//Execution
		UserHistoryEntity result = target.findUserHistoryByUsername(null);
		
		//Verification
		assertNull(result);
		
		//TearDown
		
	}
	
	@Test(expected = DataAccessException.class)
	public void testFindUserHistoryByUsernameParamNoEntry() throws Exception {
		
		//SetUp
		
		//Execution
		UserHistoryEntity result = target.findUserHistoryByUsername("Marigoula");
		
		//Verification
		assertNull(result);
		
		//TearDown
		
	}
	
	@Test
	public void testFindAllUserHistoriesSuccess() throws Exception {
		
		// SetUp
		final String username1 = "mitsos";
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername(username1);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 =
				new UserHistoryEntity(UUID.randomUUID().toString(), username1, messageList1);
		
		target.datastore().save(entityFixture1);
		
		final String username2 = "kitsos";
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername(username2);
		message2.setMessageText("Hi!");
		message2.setTimeToLive(15);
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 =
				new UserHistoryEntity(UUID.randomUUID().toString(), username2, messageList2);
		
		target.datastore().save(entityFixture2);
		
		// Execution
		final List<UserHistoryEntity> entitiesResult = target.findAllUserHistories();
		
		// Verification
		final int expectedSize = 2;
		
		assertNotNull("The entities result must not be null.", entitiesResult);
		assertEquals(
				"The entities result size must not be less or greater than "
						+ expectedSize + ".",
				expectedSize,
				entitiesResult.size()
		);
		
		// TearDown
		target.datastore().delete(entityFixture1);
		target.datastore().delete(entityFixture2);
		
		assertNull(
				"The first entity must have been deleted.",
				target.datastore().exists(entityFixture1)
		);
		assertNull(
				"The second entity must have been deleted.",
				target.datastore().exists(entityFixture2)
		);
		
	}
	
	@Test
	public void testFindAllUserHistoriesSuccessEmptyList() throws Exception {
		
		//SetUp
		List< UserHistoryEntity > entityList = target.datastore().find(UserHistoryEntity.class).asList();
		
		if(0 < entityList.size()) {
			
			for(UserHistoryEntity entity : entityList) {
				target.datastore().delete(entity);
			}
			
		}
		
		assertEquals(0, target.datastore().find(UserHistoryEntity.class).asList().size());
		
		//Execute
		List< UserHistoryEntity > resultList = target.findAllUserHistories();
		
		//Verification
		assertEquals(0, resultList.size());
		
		//TearDown
		
		if(0 != entityList.size()) {
			
			for(UserHistoryEntity entity : entityList) {
				target.datastore().save(entity);
			}
			
		}
		
	}
	
	@Test
	public void testUpdateUserHistoryParamUserHistorySuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList1);
		
		target.datastore().save(entityFixture1);
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername(username);
		message2.setMessageText("Im fine, how about you?");
		message2.setTimeToLive(15);
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 =
				new UserHistoryEntity(entityFixture1.getId(), entityFixture1.getUsername(), messageList2);
		
		// Execution
		target.updateUserHistory(entityFixture2);
		
		// Verification
		final UserHistoryEntity entityResult =
				target.datastore().get(UserHistoryEntity.class, entityFixture2.getId());
		
		assertNotNull("The entity result must not be null.", entityResult);
		assertEquals(entityFixture2.getId(), entityResult.getId());
		assertEquals(entityFixture2.getUsername(), entityResult.getUsername());
		assertEquals(
				"The sender username of the first message of the result entity must be equal to"
						+ "the sender username of the first message of the second entity.",
				entityFixture2.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		assertEquals(
				"The message test of the first message of the result entity must be equal to"
						+ "the message text of the first message of the second entity.",
				entityFixture2.getMessageList().get(0).getMessageText(),
				entityResult.getMessageList().get(0).getMessageText()
		);
		
		// TearDown
		target.datastore().delete(entityResult);
		
		assertNull(
				"The result entity must have been deleted.",
				target.datastore().exists(entityResult)
		);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateUserHistoryParamUserHistoryNull() throws Exception {
		
		//SetUp
		
		//Execution
		target.updateUserHistory(null);
		
		//Verification
		
		//TearDown
		
	}
	
	@Test
	@Ignore
	public void testUpdateUserHistoryParamsUsernameAndMessageListSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList1);
		
		target.datastore().save(entityFixture1);
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername(username);
		message2.setMessageText("Im fine, how about you?");
		message2.setTimeToLive(15);
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 =
				new UserHistoryEntity(entityFixture1.getId(), entityFixture1.getUsername(), messageList2);
		
		// Execution
		target.updateUserHistory(entityFixture2.getUsername(), entityFixture2.getMessageList());
		
		// Verification
		final UserHistoryEntity entityResult =
				target.datastore().get(UserHistoryEntity.class, entityFixture2.getId());
		
		assertNotNull("The entity result must not be null.", entityResult);
		assertEquals(
				"The result entity id field must be equal to the second entity id field.",
				entityFixture2.getId(),
				entityResult.getId()
		);
		assertEquals(
				"The result entity username field must be equal to the second entity username field.",
				entityFixture2.getUsername(),
				entityResult.getUsername()
		);
		assertEquals(
				"The sender username of the first message of the result entity must be equal to"
						+ "the sender username of the first message of the second entity.",
				entityFixture2.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		assertEquals(
				"The message test of the first message of the result entity must be equal to"
						+ "the message text of the first message of the second entity.",
				entityFixture2.getMessageList().get(0).getMessageText(),
				entityResult.getMessageList().get(0).getMessageText()
		);
		
		// TearDown
		target.datastore().delete(entityResult);
		
		assertNull(
				"The result entity must have been deleted.",
				target.datastore().exists(entityResult)
		);
		
	}
	
	@Test
	public void testRemoveUserHistoryByIdSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		messageList.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList);
		
		target.datastore().save(entityFixture);
		
		assertNotNull("The entity must have been saved.", target.datastore().exists(entityFixture));
		
		// Execution
		target.removeUserHistoryById(entityFixture.getId());
		
		// Verification
		assertNull("The entity must have been deleted.", target.datastore().exists(entityFixture));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveUserHistoryByIdParamNull() throws Exception {
		
		//SetUp
		
		//Execution
		target.removeUserHistoryById(null);
		
		//Verification
		
		//TearDown
		
	}
	
	@Test(expected = DataAccessException.class)
	public void testRemoveUserHistoryByIdParamEmpty() throws Exception {
		
		//SetUp
		UserHistoryEntity entity = new UserHistoryEntity();
		entity.setId("");
		
		//Execution
		target.removeUserHistoryById(entity.getId());
		
		//Verification
		
		//TearDown
		
	}
	
	@Test
	public void testRemoveUserHistoryByUsernameSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		messageList.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messageList);
		
		target.datastore().save(entityFixture);
		
		assertNotNull("The entity must have been saved.", target.datastore().exists(entityFixture));
		
		// Execution
		target.removeUserHistoryByUsername(entityFixture.getUsername());
		
		// Verification
		assertNull("The entity must have been deleted.", target.datastore().exists(entityFixture));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveUserHistoryByUsernameParamNull() throws Exception {
		
		//SetUp
		
		//Execution
		target.removeUserHistoryByUsername(null);
		
		//Verification
		
		//TearDown
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveUserHistoryByUsernameParamEmpty() throws Exception {
		
		//SetUp
		UserHistoryEntity entity = new UserHistoryEntity();
		entity.setUsername("");
		
		//Execution
		target.removeUserHistoryByUsername(entity.getUsername());
		
		//Verification
		
		//TearDown
		
	}
	
}
