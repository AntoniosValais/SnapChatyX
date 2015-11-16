package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("signup")
public class MockSignUpResource {

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void sendSignUpJSON(String aJSON) {
    	Gson gson = new Gson();
    	MockUser mockUser = gson.fromJson(aJSON, MockUser.class);
    	
    	// TODO: Create client side request.

    	//String name = "";
    	
    	//String lastName = "";
    	
    	//String userName = "";
    	
    	//String password = "";
    	
    	//String eMail = "";
    	
        //MockUser user = new MockUser(name, lastName, userName, password, eMail);
        
        System.out.println(mockUser.getName());
        System.out.println(mockUser.getLastName());
        System.out.println(mockUser.getUserName());
        System.out.println(mockUser.getPassword());
        System.out.println(mockUser.getEMail());
    }
	
}
