package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

/**
 * 
 * 
 * @author AntoniosValais
 */
@Embedded
public class SnapClientTextMessage
{
	private String senderUsername;
	private String messageText;
	
	@Transient
	private Integer timeToLive;
	
	@Transient
	private String messageId;
	
	public SnapClientTextMessage()
	{
		this.senderUsername = new String();
		this.messageText = new String();
	}

	public String getSenderUsername()
	{
		return senderUsername;
	}

	public void setSenderUsername( String senderUsername )
	{
		this.senderUsername = senderUsername;
	}

	public String getMessageText()
	{
		return messageText;
	}

	public void setMessageText( String messageText )
	{
		this.messageText = messageText;
	}

	public Integer getTimeToLive()
	{
		return timeToLive;
	}

	public void setTimeToLive( Integer timeToLive )
	{
		this.timeToLive = timeToLive;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId( String messageId )
	{
		this.messageId = messageId;
	}

	
	
	
}
