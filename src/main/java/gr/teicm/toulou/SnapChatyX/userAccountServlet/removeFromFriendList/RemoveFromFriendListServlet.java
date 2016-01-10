package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList;

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

@Path("removeFromFriendList")
public class RemoveFromFriendListServlet implements InterfaceRemoveFromFriendListServlet
{
	private InterfaceFriendListController removeFromFriendListController;
	
	private Boolean result;
	
	private InterfaceServletResult removeFriendResult;
	
	private Gson gson;
	
	private String responseJSON;
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromFriendList(MultivaluedMap<String, String> parameters)
	{
		String userRequested = parameters.getFirst( "user" );
		
		String userToRemoveFromFriend = parameters.getFirst( "removeUser" );
		
		gson = new Gson();
				
		removeFromFriendListController = new FriendListController();
		
		result = removeFromFriendListController.removeFromFriendList( userRequested, userToRemoveFromFriend );
		
		removeFriendResult = new RemoveFriendResult( result );
		
		responseJSON = gson.toJson( removeFriendResult  );		
		
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
