package gr.teicm.toulou.SnapChatyX.adminLogIn;




import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/**
 * 
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 */

public class AdminLogInServletTests {

	private SnapClient admin;
	private AdminLogInServlet servlet;
	private MultivaluedMap<String , String> parameters;
	private Response result;	
	private Gson gson;	
	private String expectedResult;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp(){
		
		admin = new SnapClient();
		
		servlet = new AdminLogInServlet();
		
		parameters = new MultivaluedHashMap<String , String >();
		
		gson = new Gson();
		
		dataAccessObject = DataAccessObject.DAO;
			
	}
	
	@After
	public void CleanUp() {
		
		dataAccessObject.unregisterSnapClient( admin );
	}
	
	@Test
	public void testAdminLogInSuccess(){
		
		//SetUp
		admin.setUsername("Eftixia");
		admin.setPassword("a");
		
		dataAccessObject.registerSnapClient( admin );
		
		parameters.add("adminName", "Eftixia");
		parameters.add("password", "a");
		
		//Execution
		result = servlet.verifyAdminLogIn(parameters);
		
		expectedResult = gson.toJson( new AdminLogInResult( Boolean.TRUE ));
		
		//Verification		
		Assert.assertEquals( expectedResult, result.getEntity() );
	}
	
	@Test
	public void testAdminLogInInvalidAdminAndPassword() {
		
		//SetUp
		admin.setUsername("Eftixia");
		admin.setPassword("a");
		
		dataAccessObject.registerSnapClient( admin );
		
		parameters.add("adminName", "Bibo");
		parameters.add("password", "o");
		
		//Execution
		result = servlet.verifyAdminLogIn(parameters);
		
		expectedResult = gson.toJson( new AdminLogInResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
	
	@Test
	public void testAdminlogInNullParam() {
		
		//Execution		
		result = servlet.verifyAdminLogIn(null);
		
		//Verification
		Assert.assertEquals( Status.BAD_REQUEST.getStatusCode(), result.getStatus());
	}
		
}
