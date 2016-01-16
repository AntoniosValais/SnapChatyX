package gr.teicm.toulou.SnapChatyX.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.service.IUserHistoryService;

/**
 * 
 * 
 * @since Dec 14, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryResourceTest {
	
	private IUserHistoryResource target;
	
	@Before
	public void setUp() {
		
		target = new UserHistoryResource();
		
	}

	@Test
	public void testGetUserHistoryByUsernameSuccess() throws Exception {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClientTextMessage message1 = new SnapClientTextMessage();
		message1.setSenderUsername(username);
		message1.setMessageText("Hello!");
		message1.setTimeToLive(15);
		message1.setMessageId("1");
		
		final SnapClientTextMessage message2 = new SnapClientTextMessage();
		message2.setSenderUsername(username);
		message2.setMessageText("Thank you!");
		message2.setTimeToLive(10);
		message2.setMessageId("2");
		
		final SnapClientTextMessage message3 = new SnapClientTextMessage();
		message3.setSenderUsername(username);
		message3.setMessageText("Goodbye!");
		message3.setTimeToLive(5);
		message3.setMessageId("3");
		
		final List<SnapClientTextMessage> messageList = new ArrayList<>();		
		messageList.add(message1);
		messageList.add(message2);
		messageList.add(message3);
		
		final IUserHistory modelFixture = new UserHistory(username, messageList);
		
		final IUserHistoryService mockService = mock(IUserHistoryService.class);
		
		target.setService(mockService);
		
		when(mockService.getUserHistoryByUsername(username))
		.thenReturn(modelFixture);
		
		// Execution
		final Response response = target.getUserHistoryByUsername(username);
		
		// Validation
		verify(mockService).getUserHistoryByUsername(username);
		
		final int expectedStatus = 200;
		
		final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		final String expectedEntity = gson.toJson(modelFixture);
		
		final int actualStatus = response.getStatus();
		
		final String actualEntity = response.getEntity().toString();
		
		assertEquals(expectedStatus, actualStatus);
		assertEquals(expectedEntity, actualEntity);
		
	}
	
}
