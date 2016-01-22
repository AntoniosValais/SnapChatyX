package gr.teicm.toulou.SnapChatyX.resource;

import javax.ws.rs.core.Response;

import gr.teicm.toulou.SnapChatyX.service.IUserHistoryService;

/**
 * 
 * 
 * @since Dec 30, 2015
 * 
 * @author Stamatios Tsalikis
 */
public interface IUserHistoryResource {
	
	IUserHistoryService getService();
	
	void setService(final IUserHistoryService service);
	
	Response getUserHistoryByUsername(final String username);
	
}
