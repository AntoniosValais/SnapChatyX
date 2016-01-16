package gr.teicm.toulou.SnapChatyX.userAccountServlet;

import javax.ws.rs.core.Response;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceUserAccountServlet
{
	
	Response getUserAccount( String username );
	
}