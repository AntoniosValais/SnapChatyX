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

@Path("signin")
public class SignInResource {
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
    public Response postUser(String user) {
       System.out.println("signin");
        DBObject dbObjectUser = (DBObject)JSON.parse(user);

        MongoClient mongoClient = new MongoClient("localhost" , 27017);

        DB db = mongoClient.getDB("snapchatydb");
        DBCollection coll = db.getCollection("user");
        
        System.out.println("Before query" + dbObjectUser.get("username"));
        
        DBObject userObject = coll.findOne(new BasicDBObject("username",dbObjectUser.get("username")));
        System.out.println("{username:\""+ dbObjectUser.get("username")+"\"}");
        System.out.println("done query" + userObject);
        if(userObject != null)
        {
                    System.out.println("found a USER");

            mongoClient = new MongoClient("localhost" , 27017);

            db = mongoClient.getDB("snapchatydb");
            coll = db.getCollection("signInHistory");
            coll.insert(userObject);
            
            String result = "User exists";
    		return Response.status(200).entity(result).build();
        }else{
            System.out.println("DID NOT found a USER");

            String result = "User Does Not Exist";
    		return Response.status(500).entity(result).build();
            
        }
    }
}