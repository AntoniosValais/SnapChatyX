package gr.teicm.toulou.SnapChatyX.WebSocketServlet.test;



import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/*
*
* @Author AntoniosValais
*
*/

@ClientEndpoint
public class WebSocketClientMock
{
	Session session;

	public WebSocketClientMock()
	{
		this.session = null;
	}
	
	public String createConnection()
	{
		try
		{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			
			URI WebSocketServletUri = new URI( "ws://localhost:8080/SnapChatyX/createSession" );
			this.session = container.connectToServer( WebSocketClientMock.class, WebSocketServletUri );
			
			return this.session.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}		
	}
	
	public Boolean closeConnection()
	{
		try
		{
			this.session.close();
			
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}

	@OnOpen
	public String onOpen( Session session )
	{
		this.session = session;
		
		return session.getId();
	}

	@OnClose
	public String onClose( Session session, CloseReason reason )
	{
		System.out.println( "H sindesh eklise!" + reason.getReasonPhrase() );
		this.session = null;
		
		return session.getId();

	}

	@OnMessage
	public String onMessage( String message, Session session )
	{
		System.out.println( message );
		
		return message;
	}

	public Boolean sendMessage( String message )
	{
		try
		{
			message = "{\"messageType\":\"SnapTextMessage\",\"data\":{\"senderUsername\":\"Tonio\",\"messageText\":\""+message+"\",\"timeToLive\":\"1\"}}";
			
			this.session.getBasicRemote().sendText( message );
			
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
		
	}
}
