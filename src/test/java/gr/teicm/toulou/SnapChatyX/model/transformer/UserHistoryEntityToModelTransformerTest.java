package gr.teicm.toulou.SnapChatyX.model.transformer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * 
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryEntityToModelTransformerTest {
	
	private static IUserHistoryEntityToModelTransformer target;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		target =  new UserHistoryEntityToModelTransformer();
	}
	
	@Test
	public void testTransformSuccess() {
		
		// SetUp
		final List<SnapClientTextMessage> entityMessageList = new ArrayList<>();
		
		final SnapClientTextMessage messageFixture = new SnapClientTextMessage();
		
		messageFixture.setSenderUsername("mitsos");
		messageFixture.setMessageText("Hello!");
		messageFixture.setMessageId(UUID.randomUUID().toString());
		messageFixture.setTimeToLive(10);
		
		entityMessageList.add(messageFixture);
		
		final UserHistoryEntity entityFixture =
				new UserHistoryEntity(UUID.randomUUID().toString(), "mitsos", entityMessageList);
		
		// Exectution
		final IUserHistory result = target.transform(entityFixture);
		
		// Verification
		assertNotNull(result);		
		assertEquals(entityFixture.getUsername(), result.getUsername());
		assertEquals(entityFixture.getMessageList().get(0).getMessageText(), result.getMessageList().get(0).getMessageText());
		
		// TearDown
		
	}
	
}
