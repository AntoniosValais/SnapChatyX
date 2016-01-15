package gr.teicm.toulou.SnapChatyX.model;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

public class UserHistoryTest {

	
	private UserHistory target;
	
	
	@Before 
	public void setUp() {
		
		target = new UserHistory();
		
	}
	
	
	
	@Test
	public void testGetUsernameSuccess() {
		
		//SetUp
		String username = "mitsos";
		
		target.setUsername(username);
		
		//Execution
		String result = target.getUsername();
		
		//Verification
		Assert.assertNotNull(result);
		Assert.assertEquals(username,result);
		
		//TearDown
		
		
	}
	
	@Test
	public void testSetUsernameSuccess() {
		//SetUp
		String username = "mitsos";
		
		
		//Execution
		target.setUsername(username);
		
		//Verification
		Assert.assertNotNull(target.getUsername());
		Assert.assertEquals(username,target.getUsername());
		
		//TearDown 
			
	}
	
	@Test
	public void testGetMessageListSuccess() {
		
		//SetUp
		List<SnapClientTextMessage> messageList =new ArrayList<>();
		SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId("1");
		messageList.add(message);
		target.setMessageList(messageList); 
		
		//Execution
		List<SnapClientTextMessage> result = target.getMessageList();
		
		//Verification
		Assert.assertNotNull(result);
		Assert.assertEquals(messageList.get(0).getMessageId(),result.get(0).getMessageId());
			
		
		//TearDown
			
	}
	
	@Test
	public void testSetMessageListSuccess() {
		
		//SetUp
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		SnapClientTextMessage message = new SnapClientTextMessage();
		message.setMessageId("1");
		messageList.add(message);
		
		//Execution
		target.setMessageList(messageList); 
		
		//Verification
		List<SnapClientTextMessage> result = target.getMessageList();
		
		Assert.assertNotNull(result);
		Assert.assertEquals(messageList.get(0).getMessageId(),result.get(0).getMessageId());
		
		//TearDown
		
	}
		
}
