package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


	@Path("mockResources")	
	public class SocialMediaLogInMockResource {
		
			@Path("successLogIn")
			@POST
			@Consumes({MediaType.TEXT_PLAIN})
			@Produces(MediaType.APPLICATION_JSON)
		    public String postSocialUserThatExist(String user) {
		        System.out.println("got in mock success resource");
		        
		   		 return "{\"result\":\"User exists\"}";
		      }
			@Path("failLogIn")
			@POST
			@Consumes({MediaType.TEXT_PLAIN})
			@Produces(MediaType.APPLICATION_JSON)
		    public String postSocialUserThatDoesNotExist(String user) {
		        System.out.println("got in mock fail resource");
		        
		   		 return "{\"result\":\"User Does Not Exist\"}";
		      }
			@Path("successSignUp")
			@POST
			@Consumes({MediaType.TEXT_PLAIN})
			@Produces(MediaType.APPLICATION_JSON)
		    public String postSocialUserForSignUp(String user) {
		        System.out.println("got in mock success resource");
		        
		   		 return "{\"result\":\"success\"}";
		      }
			@Path("failSignUp")
			@POST
			@Consumes({MediaType.TEXT_PLAIN})
			@Produces(MediaType.APPLICATION_JSON)
		    public String postSocialUserForSignUpThatHasAlreadySignedUp(String user) {
		        System.out.println("got in mock success resource");
		        
		   		 return "{\"result\":\"internal error\"}";
		      }
	}
	

