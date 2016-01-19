
package gr.teicm.toulou.SnapChatyX.adminLogIn;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;

/**
 * 
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 * 
 */

@Path("adminLogIn")
public class AdminLogInServlet {

	private InterfaceAdminLogInController adminLogInController;
	private Gson gson;
	private String responseJSON;
	private AdminLogInResult adminLogInResult;
	private Boolean result;
	private String adminName;
	private String password;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyAdminLogIn(MultivaluedMap<String, String> parameters)
	{
		
		try{
			adminName = parameters.getFirst( "adminName" );
			
			password = parameters.getFirst( "password" );
		}
		catch( NullPointerException e){
			
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		gson = new Gson();
				
		adminLogInController = new AdminLogInController();
		
		result = adminLogInController.verify( adminName, password );
		
		adminLogInResult = new AdminLogInResult( result );
		
		responseJSON = gson.toJson( adminLogInResult  );		
		
		if( result == Boolean.FALSE )
		{
			return Response.status( Status.UNAUTHORIZED ).entity( responseJSON ).build();
		}
		else
		{
			return Response.ok().entity( responseJSON ).build();
		}
	}	
}
