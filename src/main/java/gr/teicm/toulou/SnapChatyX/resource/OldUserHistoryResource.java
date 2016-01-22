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

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;

/**
 * 
 * 
 * @author Stamatios Tsalikis
 */
public class OldUserHistoryResource {
	
	// http://localhost:8080/SnapChatyX/webapi/histories/user-histories/user-history/mitsos
 	@GET
 	@Path("user-history/{username}")
 	@Consumes(MediaType.TEXT_PLAIN)
 	@Produces(MediaType.TEXT_PLAIN)
 	public Response getUserHistoryByUsername(@PathParam("username") final String username) {
 		
 		System.out.println("A GET request made in UserHistoryResource with username: " + username);
 		
 		if (username == null || username.isEmpty()) {
			
			return Response.status(Status.BAD_REQUEST).build();
			
		}
 		
		final IUserHistory userHistory;
		
		userHistory = DataAccessObject.DAO.getUserHistoryByUsername(username);
		
		if (userHistory != null) {
			
			final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			
			final String userHistoryJson = gson.toJson(userHistory);
			
			return Response.ok().entity(userHistoryJson).build();
			
		}
		
		return Response.status(Status.NOT_FOUND).build();
 	}
}
