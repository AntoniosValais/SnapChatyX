package gr.teicm.toulou.SnapChatyX.resources;

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

import gr.teicm.toulou.SnapChatyX.controllers.UserController;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
@Path("histories/user-histories")
public class UserHistoryResource extends UserResource {
	
	public UserHistoryResource() {
		super(new UserController());
	}
	
	@GET
	@Path("user-history/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserHistory(@PathParam("username") String username) {
		if (username.equals(null)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		UserHistory userHistory = controller.getUserService().getUserHistoryByUsername(username);
		
		if (!userHistory.equals(null)) {
			Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			
			String userHistoryJson = gson.toJson(userHistory);
			
			return Response.ok().entity(userHistoryJson).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
