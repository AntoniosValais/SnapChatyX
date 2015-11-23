package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
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

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("signup")
public class SignUpResource {

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
	        DBObject dbObjectUser;
	        try{
	                dbObjectUser = (DBObject)JSON.parse(user);

	               try {  
	                      MongoClient mongoClient = new MongoClient("localhost" , 27017);
	                      System.out.println("Connected");

	                      DB db = mongoClient.getDB("snapchatydb");
	                      DBCollection coll = (DBCollection) db.getCollection("user");

	                      coll.insert(dbObjectUser);
	                      mongoClient.close();
	                      String result = "User singed up";
	              		  return Response.status(200).entity(result).build();
	                     

	              } catch (Exception e) {
	            	  
	            	  String result = "Database error";
	         		  return Response.status(500).entity(result).build();
	                     
	              }
	               
	        }catch(JSONParseException e){
	        	 String result = "Cannot parse input";
        		 return Response.status(400).entity(result).build();
	            
	        }
    }
}
