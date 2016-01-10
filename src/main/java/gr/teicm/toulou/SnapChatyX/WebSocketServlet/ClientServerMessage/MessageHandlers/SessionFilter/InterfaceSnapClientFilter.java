package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.MessageHandlers.SessionFilter;

import java.util.Set;

/*
*
* @Author AntoniosValais
*
*/

import gr.teicm.toulou.SnapChatyX.model.SnapClient;

public interface InterfaceSnapClientFilter
{
	public Boolean addCorrelatedSnapClientsToSet( Set< SnapClient > connectedWithSnapClients );
}
