package gr.teicm.toulou.SnapChatyX;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class SignUpTest {

	@Test
	public void test() {
		System.out.println("postUser");
        String user = "{onoma:\"Tania\",epitheto:\"Giossi\",username:\"TaniaG\",pass:\"mypass\",email:\"tania.giossi@hotmail.com\"}";
        SignUpResource instance = new SignUpResource();
        String expResult = Response.status(200).entity("User singed up").build().toString();
        String result = instance.signUp(user).toString();
        assertEquals(expResult, result);
	}
	
	 @Test
	    public void testPostUserWithUnparsableInput() {
	        System.out.println("postUser");
	        String user = "Unparsable Input";
	        SignUpResource instance = new SignUpResource();
	        String expResult = Response.status(400).entity("Cannot parse input").build().toString();
	        String result = instance.signUp(user).toString();
	        assertEquals(expResult, result);
	        

	    }
}
