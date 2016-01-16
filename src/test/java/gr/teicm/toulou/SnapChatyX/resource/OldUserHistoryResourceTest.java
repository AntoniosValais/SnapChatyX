package gr.teicm.toulou.SnapChatyX.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.IUserHistoryDAO;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.resource.UserHistoryResource;

/**
 * 
 * 
 * @since Dec 14, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class OldUserHistoryResourceTest {
	
	private String testMitsosUsername;
	
	private IUserHistoryDAO dao;
	
	private UserHistoryResource resource;
	
	@Before
	public void setUp() {
		
		testMitsosUsername = "test-mitsos";
		
		resource = new UserHistoryResource();
		
		dao = DataAccessObject.DAO;
		
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
		
		IUserHistory userHistory = new UserHistory(testMitsosUsername, messageList);
		
		dao.addUserHistoryByUsername(userHistory);
		
	}

	@Test
	public void testGetUserHistoryByUsernameSuccess() {
		
		int expectedStatus = 200;
		
		IUserHistory userHistory = dao.getUserHistoryByUsername(testMitsosUsername);
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		String expectedEntity = gson.toJson(userHistory);
		
		Response response = resource.getUserHistoryByUsername(testMitsosUsername);
		
		int actualStatus = response.getStatus();
		
		String actualEntity = (String) response.getEntity();
		
		Assert.assertEquals(expectedStatus, actualStatus);
		Assert.assertEquals(expectedEntity, actualEntity);
		
		System.out.println();
		System.out.println("--------- UserHistoryResourceTest.java start ---------");
		System.out.println();
		System.out.println("response actual status:");
		System.out.println(actualStatus);
		System.out.println();
		System.out.println("response actual entity:");
		System.out.println(actualEntity);
		System.out.println();
		System.out.println("---------- UserHistoryResourceTest.java end ----------");
		System.out.println();
		
	}
	
	@After
	public void tearDown() {
		
		resource = null;
		
		dao.deleteUserHistoryByUsername(testMitsosUsername);
		dao = null;
		
		testMitsosUsername = null;
		
	}

}