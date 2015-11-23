package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import javax.websocket.Session;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.DataAccessMock;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;

/*
*
* @Author AntoniosValais
*
*/

public class UserConnectedMessageHandler implements InterfaceMessageHandler
{
	private String username;

	public UserConnectedMessageHandler( ClientServerMessage message )
	{
		this.username = message.getData().getAsJsonObject().get( "username" ).getAsString();
	}
	
	@Override
	public Boolean serve( Session session )
	{
		try
		{
			DataAccessMock.DAO.relateUsernameWithSession( username, session );
			
			return Boolean.TRUE;
		}
		catch( Exception e )
		{
			
			return Boolean.FALSE;
		}
		
	}

}
