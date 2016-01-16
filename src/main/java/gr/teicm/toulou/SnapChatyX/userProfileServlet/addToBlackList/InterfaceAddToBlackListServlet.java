package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceAddToBlackListServlet
{

	Response addToBlackList( MultivaluedMap< String, String > parameters );

}