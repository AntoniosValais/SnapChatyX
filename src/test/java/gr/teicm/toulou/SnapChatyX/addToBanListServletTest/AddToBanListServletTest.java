package gr.teicm.toulou.SnapChatyX.addToBanListServletTest;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.adminBanUserServlet.BanUserResult;
import gr.teicm.toulou.SnapChatyX.adminBanUserServlet.BanUserServlet;
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

public class AddToBanListServletTest {

	private SnapClient user;
	private BanUserServlet servlet;
	private MultivaluedMap<String , String> parameters;
	private Response result;	
	private Gson gson;	
	private String expectedResult;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp(){
		
		user = new SnapClient();
		
		servlet = new BanUserServlet();
		
		parameters = new MultivaluedHashMap<String , String >();
		
		gson = new Gson();
		
		dataAccessObject = DataAccessObject.DAO;
			
	}
	
	@After
	public void CleanUp() {
		
		dataAccessObject.unregisterSnapClient( user );
		
		dataAccessObject.removeUserFromBanList(user.getUsername() );
	}
	
	@Test
	public void banExistedUserWithValidParametersOnPost(){
		
		//SetUp
		user.setUsername("Efi");
		
		dataAccessObject.registerSnapClient(  user );
		
		parameters.add("user", "Efi");
		
		//Execution
		result = servlet.banUser( parameters );
		
		expectedResult = gson.toJson( new BanUserResult( Boolean.TRUE ));
		
		//Verification		
		Assert.assertEquals( expectedResult, result.getEntity() );
	}
	
	@Test
	public void testToBanUnexistedUser() {
		
		//SetUp
		parameters.add("user", "anna");
		
		//Execution
		result = servlet.banUser(parameters);
		
		expectedResult = gson.toJson( new BanUserResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
	
	@Test
	public void testBanUserServletWithNullParameter() {
		
		//Execution		
		result = servlet.banUser(null);
		
		//Verification
		Assert.assertEquals( Status.BAD_REQUEST.getStatusCode(), result.getStatus());
	}
	
	@Test
	public void testPassingEmptyUsernameToBan() {
		
		//SetUp
		parameters.add("user", "");
		
		//Execution
		result = servlet.banUser(parameters);
		
		expectedResult = gson.toJson( new BanUserResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
}
