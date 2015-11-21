package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

import com.google.gson.JsonElement;

/*
*
* @Author AntoniosValais
*
*/

public class ClientServerMessage
{
	private EnumMessageType messageType;
	private JsonElement data;
	
	public ClientServerMessage()
	{
		
	}

	public EnumMessageType getMessageType()
	{
		return messageType;
	}

	public void setMessageType( String messageType )
	{
		this.messageType = EnumMessageType.valueOf( messageType );
	}

	public JsonElement getData()
	{
		return data;
	}

	public void setData( JsonElement data )
	{
		this.data = data;
	}
	
	

}
