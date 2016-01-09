package gr.teicm.toulou.SnapChatyX.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * 
 * 
 * @since Jan 8, 2016
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryDAOTest {
	
	@Test
	public void testInsertUserHistorySuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername("mitsos");
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		messageList.add(message);
		
		final UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList);
		
		target.insertUserHistory(entityFixture);
		
		final UserHistoryEntity entityResult = target.datastore().get(UserHistoryEntity.class, entityFixture.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals("The ids must be equal.", entityFixture.getId(), entityResult.getId());
		assertEquals("The usernames must be equal", entityFixture.getUsername(), entityResult.getUsername());
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		target.datastore().delete(entityResult);
		
		assertNull(target.datastore().exists(entityResult));
		
	}
	
	@Test
	public void testFindUserHistoryByIdSuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername("mitsos");
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		messageList.add(message);
		
		final UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList);
		
		target.datastore().save(entityFixture);
		
		final UserHistoryEntity entityResult = target.findUserHistoryById(entityFixture.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals("The ids must be equal.", entityFixture.getId(), entityResult.getId());
		assertEquals("The usernames must be equal", entityFixture.getUsername(), entityResult.getUsername());
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		target.datastore().delete(entityResult);
		
		assertNull(target.datastore().exists(entityResult));
		
	}
	
	@Test
	public void testFindUserHistoryByUsernameSuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername("mitsos");
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		messageList.add(message);
		
		final UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList);
		
		target.datastore().save(entityFixture);
		
		final UserHistoryEntity entityResult = target.findUserHistoryByUsername(entityFixture.getUsername());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals("The ids must be equal.", entityFixture.getId(), entityResult.getId());
		assertEquals("The usernames must be equal", entityFixture.getUsername(), entityResult.getUsername());
		assertEquals(
				"The senderUsernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		
		target.datastore().delete(entityResult);
		
		assertNull(target.datastore().exists(entityResult));
		
	}
	
	@Test
	public void testFindAllUserHistories() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final String username1 = "mitsos";
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername(username1);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 = new UserHistoryEntity(UUID.randomUUID().toString(), username1, messageList1);
		
		target.datastore().save(entityFixture1);
		
		final String username2 = "kitsos";
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername(username2);
		message2.setMessageText("Hi!");
		message2.setTimeToLive(15);
		
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 = new UserHistoryEntity(UUID.randomUUID().toString(), username2, messageList2);
		
		target.datastore().save(entityFixture2);
		
		final List<UserHistoryEntity> entitiesResult = target.findAllUserHistories();
		
		assertNotNull(entitiesResult);
		assertEquals(2, entitiesResult.size());
		
		target.datastore().delete(entityFixture1);
		target.datastore().delete(entityFixture2);
		
		assertNull(target.datastore().exists(entityFixture1));
		assertNull(target.datastore().exists(entityFixture2));
		
	}
	
	@Test
	public void testUpdateUserHistoryParamUserHistorySuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername("mitsos");
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList1);
		
		target.datastore().save(entityFixture1);
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername("mitsos");
		message2.setMessageText("Im fine, how about you?");
		message2.setTimeToLive(15);
		
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 = new UserHistoryEntity(entityFixture1.getId(), entityFixture1.getUsername(), messageList2);
		
		target.updateUserHistory(entityFixture2);
		
		final UserHistoryEntity entityResult = target.datastore().get(UserHistoryEntity.class, entityFixture2.getId());
		
		assertNotNull("The entity result must not be null.", entityResult);
		assertEquals(entityFixture2.getId(), entityResult.getId());
		assertEquals(entityFixture2.getUsername(), entityResult.getUsername());
		assertEquals(
				entityFixture2.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		assertEquals(
				entityFixture2.getMessageList().get(0).getMessageText(),
				entityResult.getMessageList().get(0).getMessageText()
		);
		
		target.datastore().delete(entityResult);
		
		assertNull(target.datastore().exists(entityResult));
		
	}
	
	@Test
	public void testUpdateUserHistoryParamsUsernameAndMessageListSuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList1 = new ArrayList<>();
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		
		message1.setMessageId(UUID.randomUUID().toString());
		message1.setSenderUsername("mitsos");
		message1.setMessageText("Hello!");
		message1.setTimeToLive(10);
		
		messageList1.add(message1);
		
		final UserHistoryEntity entityFixture1 = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList1);
		
		target.datastore().save(entityFixture1);
		
		final List<SnapClientTextMessage> messageList2 = new ArrayList<>();
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		
		message2.setMessageId(UUID.randomUUID().toString());
		message2.setSenderUsername("mitsos");
		message2.setMessageText("Im fine, how about you?");
		message2.setTimeToLive(15);
		
		messageList2.add(message2);
		
		final UserHistoryEntity entityFixture2 = new UserHistoryEntity(entityFixture1.getId(), entityFixture1.getUsername(), messageList2);
		
		target.updateUserHistory(entityFixture2.getUsername(), entityFixture2.getMessageList());
		
		final UserHistoryEntity entityResult = target.datastore().get(UserHistoryEntity.class, entityFixture2.getId());
		
		assertNotNull("The entity result must not be null.", entityResult);
		assertEquals(entityFixture2.getId(), entityResult.getId());
		assertEquals(entityFixture2.getUsername(), entityResult.getUsername());
		assertEquals(
				entityFixture2.getMessageList().get(0).getSenderUsername(),
				entityResult.getMessageList().get(0).getSenderUsername()
		);
		assertEquals(
				entityFixture2.getMessageList().get(0).getMessageText(),
				entityResult.getMessageList().get(0).getMessageText()
		);
		
		target.datastore().delete(entityResult);
		
		assertNull(target.datastore().exists(entityResult));
		
	}
	
	@Test
	public void testRemoveUserHistoryById() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername("mitsos");
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		messageList.add(message);
		
		final UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList);
		
		target.datastore().save(entityFixture);
		
		assertNotNull(target.datastore().exists(entityFixture));
		
		target.removeUserHistoryById(entityFixture.getId());
		
		assertNull(target.datastore().exists(entityFixture));
		
	}
	
	@Test
	public void testRemoveUserHistoryByUsernameSuccess() throws DataAccessException {
		
		final IUserHistoryDAO target = new UserHistoryDAO();
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setSenderUsername("mitsos");
		message.setMessageText("Hello!");
		message.setTimeToLive(10);
		
		messageList.add(message);
		
		final UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", messageList);
		
		target.datastore().save(entityFixture);
		
		assertNotNull(target.datastore().exists(entityFixture));
		
		target.removeUserHistoryByUsername(entityFixture.getUsername());
		
		assertNull(target.datastore().exists(entityFixture));
		
	}
	
}
