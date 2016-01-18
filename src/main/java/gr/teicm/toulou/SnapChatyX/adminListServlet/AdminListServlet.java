package gr.teicm.toulou.SnapChatyX.adminListServlet;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;


@Path("getAdminList")
public class AdminListServlet {

	private InterfaceAdminListController adminListController;
	private Gson gson;
	private String responseJSON;
	private List<String> administrators;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdminList()
	{
		adminListController = new AdminListController();
		
		gson = new Gson();
		
		administrators = adminListController.getAdminList();
		
		if( administrators != null ){
			
			if( administrators.isEmpty() ){
				return Response.status(Status.NO_CONTENT).build();
			}
			responseJSON = gson.toJson( administrators );
			
			return Response.ok().entity( responseJSON ).build();
		}
		else{
			return Response.status( Status.BAD_REQUEST ).build();
		}
	}
}

