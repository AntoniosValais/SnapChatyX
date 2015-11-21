package gr.teicm.toulou.SnapChatyX.WebSocketServlet;

import java.util.Set;

import javax.websocket.Session;

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

}