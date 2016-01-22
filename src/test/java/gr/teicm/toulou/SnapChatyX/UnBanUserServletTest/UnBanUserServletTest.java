package gr.teicm.toulou.SnapChatyX.UnBanUserServletTest;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.adminUnbanUserServlet.UnbanUserResult;
import gr.teicm.toulou.SnapChatyX.adminUnbanUserServlet.UnbanUserServlet;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/**
 * 
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 * 
 */

public class UnBanUserServletTest {
	private SnapClient user;
	private UnbanUserServlet servlet;
	private MultivaluedMap<String , String> parameters;
	private Response result;	
	private Gson gson;	
	private String expectedResult;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp(){
		
		user = new SnapClient();
		
		servlet = new UnbanUserServlet();
		
		parameters = new MultivaluedHashMap<String , String >();
		
		gson = new Gson();
		
		dataAccessObject = DataAccessObject.DAO;
			
	}
	
	@After
	public void CleanUp() {
		
		dataAccessObject.unregisterSnapClient( user );
		
		dataAccessObject.removeUserFromBanList( user.getUsername() );
	}
	
	@Test
	public void testToRemoveFromBanListABannedUser(){
		
		//SetUp
		user.setUsername("Joyce");
		
		dataAccessObject.registerSnapClient(  user );
		dataAccessObject.addUserToBanList( user.getUsername() );
		
		parameters.add("user", "Joyce");
		
		//Execution
		result = servlet.unbanUser( parameters );
		
		expectedResult = gson.toJson( new UnbanUserResult( Boolean.TRUE ));
		
		//Verification		
		Assert.assertEquals( expectedResult, result.getEntity() );
	}
	
	@Test
	public void testToUnBanAUserThatDoesNotExistInDataBase() {
		
		//SetUp
		parameters.add("user", "Dina");
		
		//Execution
		result = servlet.unbanUser(parameters);
		
		expectedResult = gson.toJson( new UnbanUserResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
	
	@Test
	public void testToPassNullParametersOnUnBannedUserServlet() {
		
		//Execution		
		result = servlet.unbanUser(null);
		
		//Verification
		Assert.assertEquals( Status.BAD_REQUEST.getStatusCode(), result.getStatus());
	}
	
	@Test
	public void testToPassEmptyUsernameOnServlet() {
		
		//SetUp
		parameters.add("user", "");
		
		//Execution
		result = servlet.unbanUser(parameters);
		
		expectedResult = gson.toJson( new UnbanUserResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}


}
