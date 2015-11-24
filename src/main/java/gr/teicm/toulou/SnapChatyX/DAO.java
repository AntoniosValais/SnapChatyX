package gr.teicm.toulou.SnapChatyX;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DAO implements IDAO {

	MongoClient mongoClient;
	
	DAO()
	{
		try{
			this.mongoClient = new MongoClient("localhost" , 27017);
		}catch(MongoException e){
			System.out.println(e);
		}
	}
	
	public String saveLocation(DBObject location)
	{
			DB db = this.mongoClient.getDB("snapchatydb");
	        DBCollection coll = db.getCollection("signInHistory");
	        
	        DBObject push =new BasicDBObject().append("latitude",location.get("latitude"))
	                                          .append("longtitude",location.get("longtitude"))
	                                          .append("username",location.get("username"));
	        try{
		        coll.update(new BasicDBObject("username",location.get("username")),push);
	    		return "{\"result\":\"success\"}";
		      }catch(MongoException e){
		        System.out.println(e);
	    		return "{\"result\":\"internal error\"}";
		     }
	}
	public String signUpUser(DBObject user)
	{
		try {  
           
		            DB db = this.mongoClient.getDB("snapchatydb");
		            DBCollection coll = (DBCollection) db.getCollection("user");
		
		            coll.insert(user);
		            this.mongoClient.close();
		            
		    		return "{\"result\":\"User singed up\"}";

		    } catch (Exception e) {
		    		return "{\"result\":\"Database error\"}";
		    }
		
	}
	public String signInUser(DBObject user)
	{
			DB db = this.mongoClient.getDB("snapchatydb");
	        DBCollection coll = db.getCollection("user");
    
	        DBObject userObject = coll.findOne(new BasicDBObject("username",user.get("username")));
	        if(userObject != null)
	        {
	                    System.out.println("found a USER");

	            coll = db.getCollection("signInHistory");
	            coll.insert(userObject);
	            
	    		return "{\"result\":\"User exists\"}";
	        }else{
	            System.out.println("DID NOT found a USER");
	            
	    		return "{\"result\":\"User Does Not Exist\"}";
	            
	        }
	}
	
}
