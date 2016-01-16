package gr.teicm.toulou.SnapChatyX.RestTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.SignInResource;
import gr.teicm.toulou.SnapChatyX.SocialSignInResource;

public class SocialSignInTest {
	@Test
    public void testSocialPostUserThatExists() {
        System.out.println("postUser");
        String user = "{username:\"TaniaG\",pass:\"mypass\"}";
        SocialSignInResource instance = new SocialSignInResource();
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
	        SocialSignInResource instance = new SocialSignInResource();
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
		        SocialSignInResource instance = new SocialSignInResource();
		        MockDAO mdao = new MockDAO();
		        String expResult = "{\"result\":\"Cannot parse input\"}";
		        String result = instance.postUser(user);
		        assertEquals(expResult, result);
		        
		    }
	     @Test
		    public void testThatResourceSetCorrectSocialPassword() {
	    	 
	         String user = "{username:\"SocialTestUsername\",pass:\"mypass\"}";
	         SocialSignInResource instance = new SocialSignInResource();
	         MockDAO mdao = new MockDAO();
	         instance.dao = mdao;
	         String expResult = "SocialMediaPasswordPlaceHolder-UltraSecret";
	         String result = instance.postUser(user);
	         assertEquals(expResult, result);
		        
		    }
}
