package gr.teicm.toulou.SnapChatyX.model.transformer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

public class UserHistoryEntityToModelTransformerTest {
	
	@Test
	public void testTransformSuccess() {
		
		IUserHistoryEntityToModelTransformer target = new UserHistoryEntityToModelTransformer();
		
		final List<SnapClientTextMessage> entityMessageList = new ArrayList<>();
		
		final SnapClientTextMessage messageFixture = new SnapClientTextMessage();
		
		messageFixture.setSenderUsername("mitsos");
		messageFixture.setMessageText("Hello!");
		messageFixture.setMessageId(UUID.randomUUID().toString());
		messageFixture.setTimeToLive(10);
		
		entityMessageList.add(messageFixture);
		
		UserHistoryEntity entityFixture = new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", entityMessageList);
		
		IUserHistory result = target.transform(entityFixture);
		
		assertNotNull(result);		
		assertEquals(entityFixture.getUsername(), result.getUsername());
		assertEquals(entityFixture.getMessageList().get(0).getMessageText(), result.getMessageList().get(0).getMessageText());
		
	}
	
}
