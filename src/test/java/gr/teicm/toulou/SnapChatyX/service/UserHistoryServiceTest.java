package gr.teicm.toulou.SnapChatyX.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.dao.IUserHistoryDAO;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

public class UserHistoryServiceTest {
	
	private static IUserHistoryService target;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		target = new UserHistoryService();
		
	}
	
	@Test
	public void testGetUserHistoryByUsernameSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message = new SnapClientTextMessage();
		message.setSenderUsername(username);
		message.setMessageText("Hello!");
		
		final List<SnapClientTextMessage> messages = new ArrayList<>();
		messages.add(message);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), username, messages);
		
		final IUserHistoryDAO mockDao = mock(IUserHistoryDAO.class);
		target.setUserHistoryDAO(mockDao);
		
		when(mockDao.findUserHistoryByUsername(username))
		.thenReturn(entityFixture);
		
		// Execution
		final IUserHistory modelResult = target.getUserHistoryByUsername(username);
		
		// Verification
		verify(mockDao).findUserHistoryByUsername(username);
		
		assertNotNull(modelResult);
		assertEquals("The usernames must be equal.",
				entityFixture.getUsername(),
				modelResult.getUsername()
		);
		assertEquals(
				"The sender usernames of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getSenderUsername(),
				modelResult.getMessageList().get(0).getSenderUsername()
		);
		assertEquals(
				"The message texts of the message lists must be equal.",
				entityFixture.getMessageList().get(0).getMessageText(),
				modelResult.getMessageList().get(0).getMessageText()
		);
		
	}

}
