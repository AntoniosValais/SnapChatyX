package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import javax.websocket.Session;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.SessionFilter.SessionFilterController;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class UserTextMessageHandler implements InterfaceMessageHandler
{
	private SnapClientTextMessage userTextMessage;
	
	private Gson jsonHandler;
	
	private ClientServerMessage receivedMessage;
	
	private InterfaceDataAccessObject DAO;
	
	private SessionFilterController sessionFilterController;
	
	public UserTextMessageHandler( ClientServerMessage receivedMessage )
	{
		this.DAO = DataAccessObject.DAO;
		
		this.receivedMessage = receivedMessage;
		
		this.jsonHandler = new Gson();
		
		this.userTextMessage = this.jsonHandler.fromJson( receivedMessage.getData(), SnapClientTextMessage.class );
	
		this.DAO.saveMessage( userTextMessage );
		
		this.receivedMessage.setData( jsonHandler.toJsonTree( userTextMessage ) );
		
		sessionFilterController = new SessionFilterController();
	}

	@Override
	public Boolean serve( Session sessionServed )
	{
		Boolean messageDelivered = Boolean.FALSE;
		
		String snapTextMessage = jsonHandler.toJson( receivedMessage );
	
		String senderUserName = userTextMessage.getSenderUsername();
		
		SnapClient sender = this.DAO.getOnlineSnapClientWithUsername( senderUserName );
		
		for( Session session : this.sessionFilterController.getSessionsConnectedWith( sender ) )
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
