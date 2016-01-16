package gr.teicm.toulou.SnapChatyX.userProfileServlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

/*
*
* @Author AntoniosValais
*
*/

@Path("userProfileServlet")
public class UserProfileServlet implements InterfaceUserProfileServlet
{
	private InterfaceUserProfileController userProfileController;
	
	private Gson gson;
	
	private String responseJSON;
	
	private InterfaceUserProfile requestedUserProfile;
	
	@Override
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response getUserProfile(@PathParam("username") String searchProfileName)
	{
		userProfileController = new UserProfileController();
		
		gson = new Gson();
		
		requestedUserProfile = userProfileController.getUserProfile( searchProfileName );
		
		if( requestedUserProfile != null )
		{
			responseJSON = gson.toJson( requestedUserProfile );
			
			return Response.ok().entity( responseJSON ).build();
		}
		else
		{
			return Response.status( Status.BAD_REQUEST ).build();
		}
	}
}
