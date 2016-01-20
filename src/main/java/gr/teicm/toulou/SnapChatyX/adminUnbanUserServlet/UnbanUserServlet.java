package gr.teicm.toulou.SnapChatyX.adminUnbanUserServlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.banListServlet.BanListController;
import gr.teicm.toulou.SnapChatyX.banListServlet.InterfaceBanListController;

@Path("unbanUser")
public class UnbanUserServlet {
	
	private InterfaceBanListController banListController;
	private Gson gson;
	private String responseJSON;
	private UnbanUserResult unbanUserResult;
	private Boolean result;
	private String username;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response unbanUser(MultivaluedMap<String, String> parameters)
	{
		
		try{
			username = parameters.getFirst( "user" );
		}
		catch( NullPointerException e){
			
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		banListController = new BanListController();
		
		result = banListController.unbanUser( username );
		
		unbanUserResult = new UnbanUserResult( result );
		
		responseJSON = gson.toJson( unbanUserResult  );		
		
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
