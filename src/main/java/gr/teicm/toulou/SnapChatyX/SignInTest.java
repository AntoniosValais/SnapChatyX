package gr.teicm.toulou.SnapChatyX;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class SignInTest {

	 @Before
	    public void setUp() {
	        String user = "{onoma:\"Tania\",epitheto:\"Giossi\",username:\"TaniaG\",pass:\"mypass\",email:\"tania.giossi@hotmail.com\"}";
	        SignUpResource instance = new SignUpResource();
	        Response result = instance.signUp(user);
	        System.out.println("Test User inserted");
	    }
	    
	    @After
	    public void tearDown() {
	        MongoClient mongoClient = new MongoClient("localhost" , 27017);
	        System.out.println("Connected");

	        DB db = mongoClient.getDB("snapchatydb");
	        DBCollection coll = (DBCollection) db.getCollection("user");

	        coll.findAndRemove(new BasicDBObject("username","TaniaG"));
	        DB hidtorydb = mongoClient.getDB("signInHistory");
	        DBCollection historycoll = (DBCollection) hidtorydb.getCollection("user");

	        historycoll.findAndRemove(new BasicDBObject("username","TaniaG"));
	        
	    }

	    /**
	     * Test of postUser method, of class SigninResource.
	     */
	    @Test
	    public void testPostUserThatExists() {
	        System.out.println("postUser");
	        String user = "{username:\"TaniaG\",pass:\"mypass\"}";
	        SignInResource instance = new SignInResource();
	        String expResult = Response.status(200).entity("User exists").build().toString();
	        String result = instance.postUser(user).toString();
	        assertEquals(expResult, result);
	        
	    }
	     @Test
	    public void testPostUserThatDoesNotExists() {
	        System.out.println("postUser");
	        String user = "{username:\"NonExostedUser\",pass:\"Apass\"}";
	        SignInResource instance = new SignInResource();
	        String expResult = Response.status(500).entity("User Does Not Exist").build().toString();
	        String result = instance.postUser(user).toString();
	        assertEquals(expResult, result);
	        
	    }

}
