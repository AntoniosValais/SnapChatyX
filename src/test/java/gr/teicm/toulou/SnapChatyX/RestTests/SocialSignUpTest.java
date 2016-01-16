package gr.teicm.toulou.SnapChatyX.RestTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.SignUpResource;
import gr.teicm.toulou.SnapChatyX.SocialSignInResource;
import gr.teicm.toulou.SnapChatyX.SocialSignUpResource;

public class SocialSignUpTest {
	@Test
	public void correctInputSignUpTest() {
		System.out.println("postUser");
        String user = "{onoma:\"Tania\",epitheto:\"Giossi\",username:\"TaniaG\",pass:\"mypass\",email:\"tania.giossi@hotmail.com\"}";
        SocialSignUpResource instance = new SocialSignUpResource();
        MockDAO mdao = new MockDAO();
        mdao.setSingUpResponse("correct sign up");
        instance.dao = mdao;
        String expResult = "correct sign up";
        String result = instance.signUp(user);
        assertEquals(expResult, result);
	}
	@Test
	public void internalErrorSignUpTest() {
		System.out.println("postUser");
        String user = "{onoma:\"Tania\",epitheto:\"Giossi\",username:\"TaniaG\",pass:\"mypass\",email:\"tania.giossi@hotmail.com\"}";
        SocialSignUpResource instance = new SocialSignUpResource();
        MockDAO mdao = new MockDAO();
        mdao.setSingUpResponse("internal error");
        instance.dao = mdao;
        String expResult = "internal error";
        String result = instance.signUp(user);
        assertEquals(expResult, result);
	}
	
	 @Test
	    public void testPostUserWithUnparsableInput() {
	        System.out.println("postUser");
	        String user = "Unparsable Input";
	        SocialSignUpResource instance = new SocialSignUpResource();
	        String expResult = "{\"result\":\"Cannot parse input\"}";
	        String result = instance.signUp(user);
	        assertEquals(expResult, result);
	        

	    }
	 @Test
	    public void testThatResourceSetCorrectSocialPassword() {
 	 
      String user = "{username:\"SocialTestUsername\",pass:\"mypass\"}";
      SocialSignUpResource instance = new SocialSignUpResource();
      MockDAO mdao = new MockDAO();
      instance.dao = mdao;
      String expResult = "SocialMediaPasswordPlaceHolder-UltraSecret";
      String result = instance.signUp(user);
      assertEquals(expResult, result);
	        
	    }
}
