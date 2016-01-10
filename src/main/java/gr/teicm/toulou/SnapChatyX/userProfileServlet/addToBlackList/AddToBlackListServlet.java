package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToBlackList;

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
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.BlackListController;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.InterfaceBlackListController;

/*
*
* @Author AntoniosValais
*
*/

@Path("addToBlackList")
public class AddToBlackListServlet implements InterfaceAddToBlackListServlet
{
	private InterfaceBlackListController blackListController;
	
	private Boolean result;
	
	private InterfaceServletResult addToBlackListResult;
	
	private Gson gson;
	
	private String responseJSON;
	
	private String userRequested;
	
	private String userToAddOnBlackList;
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToBlackList(MultivaluedMap<String, String> parameters)
	{
		try
		{
			userRequested = parameters.getFirst( "user" );
			
			userToAddOnBlackList = parameters.getFirst( "hatesUser" );
		}
		catch( NullPointerException e)
		{
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		blackListController = new BlackListController();
		
		result = blackListController.addToBlackList( userRequested, userToAddOnBlackList );
		
		addToBlackListResult = new AddBlackListResult( result );
		
		responseJSON = gson.toJson( addToBlackListResult  );	
		
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
