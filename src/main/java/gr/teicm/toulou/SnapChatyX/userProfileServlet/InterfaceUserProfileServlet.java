package gr.teicm.toulou.SnapChatyX.userProfileServlet;

import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceUserProfileServlet
{

	Response getUserProfile( String searchProfileName );

}