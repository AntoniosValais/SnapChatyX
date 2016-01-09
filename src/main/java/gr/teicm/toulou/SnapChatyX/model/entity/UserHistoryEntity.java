package gr.teicm.toulou.SnapChatyX.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

/**
 * 
 * 
 * @since Dec 30, 2015
 * 
 * @author Stamatios Tsalikis
 */
@Entity("userHistories")
public class UserHistoryEntity{
	
	@Id
	private String id;
	
	private String username;
	
	@Embedded
	private List<SnapClientTextMessage> messageList;
	
	public UserHistoryEntity(final String id, final String username, final List<SnapClientTextMessage> messageList) {
		
		this.id = id;
		
		this.username = username;
		
		this.messageList = messageList;
		
	}
	
	public UserHistoryEntity() {
		
		this(null, null, null);
		
	}
	
	public String getId() {
		
		return this.id;
		
	}
	
	public void setId(final String id) {
		
		this.id = id;
		
	}
	
	public String getUsername() {
		
		return this.username;
		
	}
	
	public void setUsername(final String username) {
		
		this.username = username;
		
	}
	
	public List<SnapClientTextMessage> getMessageList() {
		
		return messageList;
		
	}
	
	public void setMessageList(final List<SnapClientTextMessage> messageList) {
		
		this.messageList = messageList;
		
	}
	
}
