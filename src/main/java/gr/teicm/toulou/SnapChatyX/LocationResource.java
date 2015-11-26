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
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

@Path("location")
public class LocationResource {

		public IDAO dao = DataAccessObject.DAO;
	
		@POST
		@Consumes({MediaType.TEXT_PLAIN})
		@Produces(MediaType.APPLICATION_JSON)
	    public String postLocation(String location) {
	        System.out.println("got in location " + location);
	        try{
			        DBObject dbObjectLocation = (DBObject)JSON.parse(location);
			    
			        return this.dao.saveLocation(dbObjectLocation);
			}catch(JSONParseException e){
	   		 return "{\"result\":\"Cannot parse input\"}";
	       }
	    }
}

