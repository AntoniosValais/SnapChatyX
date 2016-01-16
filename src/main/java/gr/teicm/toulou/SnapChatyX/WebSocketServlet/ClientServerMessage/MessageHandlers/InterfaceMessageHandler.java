package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import javax.websocket.Session;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceMessageHandler
{

	Boolean serve( Session session );

}
