package gr.teicm.toulou.SnapChatyX.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gr.teicm.toulou.SnapChatyX.dao.UserHistoryDAO;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.service.IUserHistoryService;
import gr.teicm.toulou.SnapChatyX.service.ServiceException;
import gr.teicm.toulou.SnapChatyX.service.UserHistoryService;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
@Path("histories/user-histories")
public class UserHistoryResource {
	
	private IUserHistoryService service;
	
	@GET
	@Path("user-history/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserHistoryByUsername(@PathParam("username") String username) {
		
		System.out.println("A GET request made in UserHistoryResource with username: " + username);
		
		if (username == null || username.isEmpty()) {
			
			return Response.status(Status.BAD_REQUEST).build();
			
		}
		
		IUserHistory userHistory = null;
		
		service = new UserHistoryService(new UserHistoryDAO());
		
		try {
			
			userHistory = service.getUserHistoryByUsername(username);
			
		} catch (ServiceException ex) {
			
			ex.printStackTrace();
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			
		}
		
		if (userHistory != null) {
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			
			String userHistoryJson = gson.toJson(userHistory);
			
			return Response.ok().entity(userHistoryJson).build();
			
		} else {
			
			return Response.status(Status.NOT_FOUND).build();
			
		}
		
	}
	
}
