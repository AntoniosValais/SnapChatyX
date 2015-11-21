package gr.teicm.toulou.SnapChatyX.WebSocketServlet;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.InterfaceMessageHandler;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.MessageHandlerFactory;



/*
*
* @Author AntoniosValais
*
*/
@ServerEndpoint(value = "/createSession")
public class WebSocketServlet
{
	private static InterfaceDataAccessObject dataAccessObject = DataAccessMock.DAO;
	private static MessageHandlerFactory messageHandlerFactory = new MessageHandlerFactory();
	private static Gson jsonHandler = new Gson();

	@OnOpen
	public void onOpen( Session session )
	{
		dataAccessObject.addSession( session );
		
		session.getAsyncRemote().sendText( "bla bla json!" );
	}

	@OnMessage
	public void onMessage( String message, Session session )
	{
		ClientServerMessage receivedMessage = jsonHandler.fromJson( message, ClientServerMessage.class );
		
		InterfaceMessageHandler messageHandler = messageHandlerFactory.createHandler( receivedMessage );
		
		messageHandler.serve( session );
	}

	@OnClose
	public void onClose( Session session )
	{
		dataAccessObject.removeSession( session );
		
		System.out.println( session.getId() + " left the network!" );
		
		
	}	
}
