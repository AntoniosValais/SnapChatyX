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
	private ClientServerMessage receivedMessage;
	
	public UserTextMessageHandler( ClientServerMessage receivedMessage )
	{
		this.receivedMessage = receivedMessage;
		
		jsonHandler = new Gson();
		
		userTextMessage = jsonHandler.fromJson( receivedMessage.getData(), UserTextMessage.class );
	
		//TODO: send userTextMessage to DAO
	}

	@Override
	public Boolean serve( Session sessionServed )
	{
		Boolean messageDelivered = Boolean.FALSE;
		
		String snapTextMessage = jsonHandler.toJson( receivedMessage );
		
		for(Session session : DataAccessMock.DAO.getAllSessions() )
		{
			try
			{
				session.getAsyncRemote().sendText( snapTextMessage );
				
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
