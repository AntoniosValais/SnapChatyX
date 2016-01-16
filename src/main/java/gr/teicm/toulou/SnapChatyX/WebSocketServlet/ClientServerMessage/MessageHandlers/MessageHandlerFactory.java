package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;

/*
*
* @Author AntoniosValais
*
*/

public class MessageHandlerFactory
{

	public InterfaceMessageHandler createHandler( ClientServerMessage receivedMessage )
	{
		switch( receivedMessage.getMessageType() )
		{
			case UserConnectedMessage:
			
				return new UserConnectedMessageHandler( receivedMessage );
			
			case SnapTextMessage:
			
				return new UserTextMessageHandler( receivedMessage );
			
			
			default:
				
				break;
		}
		
		return null;
	}
}
