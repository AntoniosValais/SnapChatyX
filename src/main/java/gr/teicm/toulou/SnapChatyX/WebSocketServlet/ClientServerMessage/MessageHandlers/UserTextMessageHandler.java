package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import javax.websocket.Session;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.DataAccessMock;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.UserTextMessage;

/*
*
* @Author AntoniosValais
*
*/

public class UserTextMessageHandler implements InterfaceMessageHandler
{
	private UserTextMessage userTextMessage;
	private Gson jsonHandler;
	
	public UserTextMessageHandler( ClientServerMessage receivedMessage )
	{
		jsonHandler = new Gson();
		
		userTextMessage = jsonHandler.fromJson( receivedMessage.getData(), UserTextMessage.class );
	}

	@Override
	public Boolean serve( Session sessionServed )
	{
		Boolean messageDelivered = Boolean.FALSE;
		
		for(Session session : DataAccessMock.DAO.getAllSessions() )
		{
			try
			{
				session.getAsyncRemote().sendText( userTextMessage.getMessageText() );
				
				messageDelivered = Boolean.TRUE;
			}
			catch( Exception e )
			{
				return Boolean.FALSE;
			}
		}
		
		return messageDelivered;
	}

}
