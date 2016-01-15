package gr.teicm.toulou.SnapChatyX.resource;

import javax.ws.rs.core.Response;

/**
 * 
 * 
 * @since Dec 30, 2015
 * 
 * @author Stamatios Tsalikis
 */
public interface IUserHistoryResource {
	
	Response getUserHistoryByUsername(String username);
	
}
