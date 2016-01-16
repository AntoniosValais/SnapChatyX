package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceRemoveFromBlackListServlet
{

	Response removeFromBlackList( MultivaluedMap< String, String > parameters );

}