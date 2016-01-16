package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.userAccountServlet.InterfaceServletResult;

/*
*
* @Author AntoniosValais
*
*/
@Path("removeFromBlackList")
public class RemoveFromBlackListServlet implements InterfaceRemoveFromBlackListServlet
{
	private InterfaceBlackListController removeFromBlackListController;
	
	private Boolean result;
	
	private InterfaceServletResult removeFromBlackListResult;
	
	private Gson gson;
	
	private String responseJSON;
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromBlackList(MultivaluedMap<String, String> parameters)
	{
		String userRequested = parameters.getFirst( "user" );
		
		String userToRemoveFromBlackList = parameters.getFirst( "removeUser" );
		
		gson = new Gson();
				
		removeFromBlackListController = new BlackListController();
		
		result = removeFromBlackListController.removeFromBlackList( userRequested, userToRemoveFromBlackList );
		
		removeFromBlackListResult = new RemoveFromBlackListResult( result );
		
		responseJSON = gson.toJson( removeFromBlackListResult  );		
		
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
