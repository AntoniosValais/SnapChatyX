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

@Path("location")
public class LocationResource {

	
		@POST
		@Consumes({MediaType.TEXT_PLAIN})
		@Produces({MediaType.TEXT_PLAIN})
	    public Response postLocation(String location) {
	        System.out.println("got in location " + location);
	        
	        DBObject dbObjectLocation = (DBObject)JSON.parse(location);

	        MongoClient mongoClient = new MongoClient("localhost" , 27017);

	        DB db = mongoClient.getDB("snapchatydb");
	        DBCollection coll = db.getCollection("signInHistory");
	        
	        DBObject push =new BasicDBObject().append("latitude",dbObjectLocation.get("latitude"))
	                                          .append("longtitude",dbObjectLocation.get("longtitude"));
	    try{
	        coll.update(new BasicDBObject("username",dbObjectLocation.get("username")),push);
	        
	        
	        String result = "Got Location";
	        Response re = Response.status(200).entity(result).build();
	        System.out.println(re.toString());
    		return re;
	      }catch(MongoException e){
	        System.out.println(e);
	        
	        String result = "Database error";
	        System.out.println(result);
    		return Response.status(500).entity(result).build();

	     }
	    }
}

