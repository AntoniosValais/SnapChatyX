package gr.teicm.toulou.SnapChatyX.repositories.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.repositories.UserHistoryRepository;

public class UserHistoryRepositoryTest {

	UserHistoryRepository uhr ;
	
	@Before
	 public void setUp() {
		uhr = new UserHistoryRepository();
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		IUserHistory userHistory = new UserHistory( "username",messageList);
    }
	@Test
	public void setUserHistoryByUsername_isNotExistingEntry() {
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		IUserHistory userHistory = new UserHistory( "username",messageList);
		
		boolean result = uhr.setUserHistoryByUsername(userHistory);
		boolean expected = true;
		
		assertEquals(expected,result);
	}
	@Test
	public void setUserHistoryByUsername_isExistingEntry() {
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		IUserHistory userHistory = new UserHistory( "username",messageList);
		uhr.dao.put("username", userHistory);
		
		boolean result = uhr.setUserHistoryByUsername(userHistory);
		boolean expected = true;
		
		assertEquals(expected,result);
	}

	@Test
	public void findUserHistoryByUsernameCorrectTest() {
		
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		IUserHistory userHistory = new UserHistory( "username",messageList);
		uhr.dao.put("username", userHistory);
		
		IUserHistory result = uhr.findUserHistoryByUsername("username");
		IUserHistory expected = userHistory;
		
		assertEquals(expected,result);
	}
	@Test
	public void findUserHistoryByUsernameFailTest() {
		
		
		IUserHistory result = uhr.findUserHistoryByUsername("username");
		IUserHistory expected = null;
		
		assertEquals(expected,result);
	}
	@Test
	public void findAllUserHistoriesEmptyTest() {
		
		
		List<IUserHistory> result = uhr.findAllUserHistories();
		List<IUserHistory> expected = new ArrayList<>();
		
		assertEquals(expected,result);
	}
	@Test
	public void findAllUserHistoriesOneEntryTest() {
		List<SnapClientTextMessage> messageList = new ArrayList<>();
		IUserHistory userHistory = new UserHistory( "username",messageList);
		uhr.dao.put("username", userHistory);
		
		List<IUserHistory> result = uhr.findAllUserHistories();
		List<IUserHistory> expected = new ArrayList<>();
		expected.add(userHistory);
		
		assertEquals(expected,result);
	}
}
