package gr.teicm.toulou.SnapChatyX.userProfileServlet.addToFriendListServlet;

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
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.FriendListController;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList.InterfaceFriendListController;

/*
*
* @Author AntoniosValais
*
*/

@Path("addOnFriendList")
public class AddToFriendListServlet implements InterfaceAddToFriendListServlet
{
	private InterfaceFriendListController friendListController;
	
	private Boolean result;
	
	private InterfaceServletResult addFriendResult;
	
	private Gson gson;
	
	private String responseJSON;
	
	private String userRequested;
	
	private String userToAddFriend;
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToFriendList(MultivaluedMap<String, String> parameters)
	{
		try
		{
			userRequested = parameters.getFirst( "user" );
			
			userToAddFriend = parameters.getFirst( "lovesUser" );
		}
		catch( NullPointerException e)
		{
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		friendListController = new FriendListController();
		
		result = friendListController.addToFriendList( userRequested, userToAddFriend );
		
		addFriendResult = new AddFriendResult( result );
		
		responseJSON = gson.toJson( addFriendResult  );		
		
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
