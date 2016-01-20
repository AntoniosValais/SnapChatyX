package gr.teicm.toulou.SnapChatyX.removeAdminServlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.adminListServlet.AdminListController;
import gr.teicm.toulou.SnapChatyX.adminListServlet.InterfaceAdminListController;

@Path("removeAdmin")
public class RemoveAdminServlet {
	
	private InterfaceAdminListController adminListController;
	private Gson gson;
	private String responseJSON;
	private RemoveAdminResult removeAdminResult;
	private Boolean result;
	private String username;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeAdmin(MultivaluedMap<String, String> parameters)
	{
		
		try{
			username = parameters.getFirst( "user" );
		}
		catch( NullPointerException e){
			
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		adminListController = new AdminListController();
		
		result = adminListController.removeAdmin( username );
		
		removeAdminResult = new RemoveAdminResult( result );
		
		responseJSON = gson.toJson( removeAdminResult  );		
		
		if( result == Boolean.FALSE )
		{
			return Response.status( Status.BAD_REQUEST ).entity( responseJSON ).build();
		}
		else
		{
			return Response.ok().entity( responseJSON ).build();
		}
	}

}
