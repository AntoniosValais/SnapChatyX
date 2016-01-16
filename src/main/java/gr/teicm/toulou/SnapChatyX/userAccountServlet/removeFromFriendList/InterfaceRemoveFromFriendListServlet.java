package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceRemoveFromFriendListServlet
{

	Response removeFromFriendList( MultivaluedMap< String, String > parameters );

}