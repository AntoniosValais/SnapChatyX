package gr.teicm.toulou.SnapChatyX.RestTests;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.LocationResource;

public class LocationTest {
	
	 @Test
	    public void testPostLocation() {
	        System.out.println("postLocation");
	        String location = "{latitude:\"32\",longtitude:\"32\",username:\"TaniaG\"}";
	        LocationResource instance = new LocationResource();
	        MockDAO mdao = new MockDAO();
	        mdao.setLocationResponse("success");
	        instance.dao = mdao;
	        String expResult = "success";
	        String result = instance.postLocation(location);
	        assertEquals(expResult, result);
	        
	    }
	 @Test
	    public void testPostLocationWithWrongInput() {
	        System.out.println("postLocation");
	        String location = "{unparsable input}";
	        LocationResource instance = new LocationResource(); 
	        String expResult = "{\"result\":\"Cannot parse input\"}";
	        String result = instance.postLocation(location);
	        assertEquals(expResult, result);
	        
	    }
	 @Test
	    public void testPostLocationForInternalError() {
	        System.out.println("postLocation");
	        String location = "{latitude:\"32\",longtitude:\"32\",username:\"TaniaG\"}";
	        LocationResource instance = new LocationResource();
	        MockDAO mdao = new MockDAO();
	        mdao.setLocationResponse("{\"result\":\"internal error\"}");
	        instance.dao = mdao;
	        String expResult = "{\"result\":\"internal error\"}";
	        String result = instance.postLocation(location);
	        assertEquals(expResult, result);
	        
	    }
}
