package gr.teicm.toulou.SnapChatyX.model;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

/**
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistory {
	
	private String username;
	
	private List<SnapClientTextMessage> messageList;
	
	public UserHistory(String username, List<SnapClientTextMessage> messageList) {
		this.messageList = messageList;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<SnapClientTextMessage> getMessageList() {
		return messageList;
	}
	
	public void setMessageList(List<SnapClientTextMessage> messageList) {
		this.messageList = messageList;
	}
	
}
