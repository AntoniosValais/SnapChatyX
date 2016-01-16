package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers;

import javax.websocket.Session;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.ClientServerMessage;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;

/*
*
* @Author AntoniosValais
*
*/

public class UserConnectedMessageHandler implements InterfaceMessageHandler
{
	private String username;
	private InterfaceDataAccessObject DAO;

	public UserConnectedMessageHandler( ClientServerMessage message )
	{
		this.DAO = DataAccessObject.DAO;
		
		this.username = message.getData().getAsJsonObject().get( "username" ).getAsString();
	}
	
	@Override
	public Boolean serve( Session session )
	{
		try
		{
			this.DAO.relateUsernameWithSession( username, session );
			
			return Boolean.TRUE;
		}
		catch( Exception e )
		{
			
			return Boolean.FALSE;
		}
		
	}

}
