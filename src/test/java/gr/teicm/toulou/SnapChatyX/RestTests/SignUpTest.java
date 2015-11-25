package gr.teicm.toulou.SnapChatyX.RestTests;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.SignUpResource;

public class SignUpTest {

	@Test
	public void correctInputSignUpTest() {
		System.out.println("postUser");
        String user = "{onoma:\"Tania\",epitheto:\"Giossi\",username:\"TaniaG\",pass:\"mypass\",email:\"tania.giossi@hotmail.com\"}";
        SignUpResource instance = new SignUpResource();
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
        SignUpResource instance = new SignUpResource();
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
	        SignUpResource instance = new SignUpResource();
	        String expResult = "{\"result\":\"Cannot parse input\"}";
	        String result = instance.signUp(user);
	        assertEquals(expResult, result);
	        

	    }
}
