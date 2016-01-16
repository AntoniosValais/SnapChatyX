package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

import com.google.gson.JsonElement;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceClientServerSocketMessage
{

	EnumMessageType getMessageType();

	void setMessageType( String messageType );

	JsonElement getData();

	void setData( JsonElement data );

}