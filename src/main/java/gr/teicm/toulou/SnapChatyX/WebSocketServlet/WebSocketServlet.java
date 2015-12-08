package gr.teicm.toulou.SnapChatyX.WebSocketServlet;


import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.InterfaceMessageHandler;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.MessageHandlerFactory;
import gr.teicm.toulou.SnapChatyX.controllers.UserController;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;



/*
*
* @Author AntoniosValais
*
*/
@ServerEndpoint(value = "/createSession")
public class WebSocketServlet
{
	private static InterfaceDataAccessObject dataAccessObject = DataAccessObject.DAO;
	private static MessageHandlerFactory messageHandlerFactory = new MessageHandlerFactory();
	private static Gson jsonHandler = new Gson();

	@OnOpen
	public void onOpen( Session session )
	{
		dataAccessObject.addSession( session );
	}

	@OnMessage
	public void onMessage( String message, Session session )
	{
		ClientServerMessage receivedMessage = jsonHandler.fromJson( message, ClientServerMessage.class );
		
		InterfaceMessageHandler messageHandler = messageHandlerFactory.createHandler( receivedMessage );
		
		Boolean messageIsServed = messageHandler.serve( session );
		
		if (messageIsServed) {
			String username = receivedMessage.getData().getAsJsonObject().get("username").getAsString();
			
			UserController userController = new UserController();
			
			UserHistory userHistory = userController.getUserService().getUserHistoryByUsername(username);
			
			if (userHistory.equals(null)) {
				userController.getUserService().addUserHistory(new UserHistory(username, new ArrayList<>()));
				
				userHistory = userController.getUserService().getUserHistoryByUsername(username);
			}
			
			userHistory.getMessageList().add(receivedMessage);
			
			userController.getUserService().addUserHistory(userHistory);
		} // end of if(messageIsServed)
	}

	@OnClose
	public void onClose( Session session )
	{
		dataAccessObject.removeSession( session );
		
		System.out.println( session.getId() + " left the network!" );
		
		
	}	
}
