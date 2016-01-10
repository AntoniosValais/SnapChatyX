package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

import com.google.gson.JsonElement;

/*
*
* @Author AntoniosValais
*
*/

public class ClientServerMessage implements InterfaceClientServerSocketMessage
{
	private EnumMessageType messageType;
	private JsonElement data;
	
	public ClientServerMessage()
	{
		
	}

	@Override
	public EnumMessageType getMessageType()
	{
		return messageType;
	}

	@Override
	public void setMessageType( String messageType )
	{
		this.messageType = EnumMessageType.valueOf( messageType );
	}

	@Override
	public JsonElement getData()
	{
		return data;
	}

	@Override
	public void setData( JsonElement data )
	{
		this.data = data;
	}
	
	

}
