package gr.teicm.toulou.SnapChatyX.model;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

public interface IUserHistory {
	
	String getUsername();
	
	void setUsername(String username);
	
	List<SnapClientTextMessage> getMessageList();
	
	void setMessageList(List<SnapClientTextMessage> messageList);
	
}
