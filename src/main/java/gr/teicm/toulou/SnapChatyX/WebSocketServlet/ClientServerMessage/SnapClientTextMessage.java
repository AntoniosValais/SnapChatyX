package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

/*
*
* @Author AntoniosValais
*
*/

public class SnapClientTextMessage
{
	private String senderUsername;
	private String messageText;
	private Integer timeToLive;
	
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
	
	
}
