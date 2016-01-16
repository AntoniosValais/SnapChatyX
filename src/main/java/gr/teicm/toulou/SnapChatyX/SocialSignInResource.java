package gr.teicm.toulou.SnapChatyX;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.IDAO;

@Path("socialsignin")
public class SocialSignInResource {
	
	public IDAO dao = DataAccessObject.DAO;
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
    public String postUser(String user) {
       System.out.println("socialsignin");
       try{
    	   		DBObject dbObjectUser = (DBObject)JSON.parse(user);
                dbObjectUser.put("pass", "SocialMediaPasswordPlaceHolder-UltraSecret");
    	   		return this.dao.signInUser(dbObjectUser);
    	   		
	       }catch(JSONParseException e){
	  		 return "{\"result\":\"Cannot parse input\"}";
	      }
       
    }
}