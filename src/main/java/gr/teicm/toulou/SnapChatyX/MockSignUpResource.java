package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("signup")
public class MockSignUpResource { // TODO: delete this resource!
	/**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signUp(String user) {
    	 System.out.println(user);
    	 
    	 String result = "The server got the JSON";
    	 
    	 return Response.status(200).entity(result).build();
    }
}
