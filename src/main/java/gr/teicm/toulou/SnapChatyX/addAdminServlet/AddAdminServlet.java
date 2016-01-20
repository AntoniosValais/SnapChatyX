package gr.teicm.toulou.SnapChatyX.addAdminServlet;

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

@Path("addAdmin")
public class AddAdminServlet {
	
	private InterfaceAdminListController adminListController;
	private Gson gson;
	private String responseJSON;
	private AddAdminResult addAdminResult;
	private Boolean result;
	private String username;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdmin(MultivaluedMap<String, String> parameters)
	{
		
		try{
			username = parameters.getFirst( "user" );
		}
		catch( NullPointerException e){
			
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		adminListController = new AdminListController();
		
		result = adminListController.addAdmin( username );
		
		addAdminResult = new AddAdminResult( result );
		
		responseJSON = gson.toJson( addAdminResult  );		
		
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
