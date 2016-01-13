package gr.teicm.toulou.SnapChatyX.userAccountServlet;

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

@Path("userAccount")
public class UserAccountServlet implements InterfaceUserAccountServlet
{
	private InterfaceUserAccountController userAccountController;
	
	private InterfaceUserAccount userAccount;

	private Gson gson;
	
	private String responseJSON;
		
	@Override
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
	public Response getUserAccount(@PathParam("username") String username)
	{
		userAccountController = new UserAccountController();
		
		gson = new Gson();
		
		userAccount = userAccountController.getUserAccount( username );
		
		if( userAccount != null )
		{
			responseJSON = gson.toJson( userAccount );
			
			return Response.ok().entity( responseJSON ).build();
		}
		else
		{
			return Response.status( Status.NOT_FOUND ).build();
		}
	}	
}
