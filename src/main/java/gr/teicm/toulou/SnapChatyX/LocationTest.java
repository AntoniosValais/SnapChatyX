package gr.teicm.toulou.SnapChatyX;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class LocationTest {

	 @Test
	    public void testPostLocation() {
	        System.out.println("postLocation");
	        String location = "{latitude:\"32\",longtitude:\"32\",username:\"TaniaG\"}";
	        LocationResource instance = new LocationResource();
	        String expResult = Response.status(200).entity("Got Location").build().toString();
	        String result = instance.postLocation(location).toString();
	        assertEquals(expResult, result);
	        
	    }
}
