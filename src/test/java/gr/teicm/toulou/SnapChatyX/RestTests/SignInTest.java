package gr.teicm.toulou.SnapChatyX.RestTests;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import gr.teicm.toulou.SnapChatyX.SignInResource;
import gr.teicm.toulou.SnapChatyX.SignUpResource;

public class SignInTest {
	    /**
	     * Test of postUser method, of class SigninResource.
	     */
	    @Test
	    public void testPostUserThatExists() {
	        System.out.println("postUser");
	        String user = "{username:\"TaniaG\",pass:\"mypass\"}";
	        SignInResource instance = new SignInResource();
	        MockDAO mdao = new MockDAO();
	        mdao.setSingInResponse("user exists");
	        instance.dao = mdao;
	        String expResult = "user exists";
	        String result = instance.postUser(user);
	        assertEquals(expResult, result);
	        
	    }
	     @Test
	    public void testPostUserThatDoesNotExists() {
	        System.out.println("postUser");
	        String user = "{username:\"NonExostedUser\",pass:\"Apass\"}";
	        SignInResource instance = new SignInResource();
	        MockDAO mdao = new MockDAO();
	        mdao.setSingInResponse("user does not exists");
	        instance.dao = mdao;
	        String expResult = "user does not exists";
	        String result = instance.postUser(user);
	        assertEquals(expResult, result);
	        
	    }
	     @Test
		    public void testPostUnparsableUser() {
		        System.out.println("postUser");
		        String user = "{Unparsable Input}";
		        SignInResource instance = new SignInResource();
		        MockDAO mdao = new MockDAO();
		        String expResult = "{\"result\":\"Cannot parse input\"}";
		        String result = instance.postUser(user);
		        assertEquals(expResult, result);
		        
		    }

}
