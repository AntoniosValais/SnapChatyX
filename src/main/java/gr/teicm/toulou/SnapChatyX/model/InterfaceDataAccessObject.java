package gr.teicm.toulou.SnapChatyX.model;

import java.util.Set;

import javax.websocket.Session;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceDataAccessObject
{

	Boolean addSession( Session session );

	Boolean removeSession( Session session );

	Session getSessionWithID( String sessionID );

	Boolean addOnlineSnapClient( SnapClient snapClient );

	Boolean removeOnlineSnapClient( SnapClient snapClient );

	Boolean relateUsernameWithSession( String username, Session session );

	Set< Session > getAllSessions();
	
	Set< Session > getSessionsConnectedWith( SnapClient snapClient );

	Boolean saveMessage( SnapClientTextMessage userTextMessage );
	
	SnapClient getOnlineSnapClientWithUsername( String username );
	
	SnapClient getRegisteredSnapClientWithUsername( String username );

}