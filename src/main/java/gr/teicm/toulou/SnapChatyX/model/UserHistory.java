package gr.teicm.toulou.SnapChatyX.model;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

/**
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistory implements IUserHistory {
	
	private String username;
	
	private List<SnapClientTextMessage> messageList;
	
	public UserHistory(String username, List<SnapClientTextMessage> messageList) {
		this.username = username;
		
		this.messageList = messageList;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public List<SnapClientTextMessage> getMessageList() {
		return messageList;
	}
	
	@Override
	public void setMessageList(List<SnapClientTextMessage> messageList) {
		this.messageList = messageList;
	}
	
}
