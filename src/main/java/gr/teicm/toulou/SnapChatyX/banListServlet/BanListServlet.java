package gr.teicm.toulou.SnapChatyX.banListServlet;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;



/*
*
* @Author iKetsi
*
*/



@Path("getBanList")
public class BanListServlet {

	private InterfaceBanListController banListController;
	private Gson gson;
	private String responseJSON;
	private List<String> bannedUsers;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBanList()
	{
		banListController = new BanListController();
		
		gson = new Gson();
		
		bannedUsers = banListController.getBanList();
		
		if( bannedUsers != null ){
			
			if( bannedUsers.isEmpty() ){
				return Response.status(Status.NO_CONTENT).build();
			}
			responseJSON = gson.toJson( bannedUsers );
			
			return Response.ok().entity( responseJSON ).build();
		}
		else{
			return Response.status( Status.BAD_REQUEST ).build();
		}
	}
}
