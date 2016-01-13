package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToFriendListServlet;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceAddToFriendListServlet
{

	Response addToFriendList( MultivaluedMap< String, String > parameters );

}