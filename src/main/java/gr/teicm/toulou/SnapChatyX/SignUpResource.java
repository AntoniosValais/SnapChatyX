package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.IDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("signup")
public class SignUpResource {

	public IDAO dao = DataAccessObject.DAO;
	
    /**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String signUp(String user) {
    	 System.out.println(user);
	        DBObject dbObjectUser;
	        try{
	                dbObjectUser = (DBObject)JSON.parse(user);
	                
	                return this.dao.signUpUser(dbObjectUser);
          
	        }catch(JSONParseException e){
        		 return "{\"result\":\"Cannot parse input\"}";
	        }
    }
}
