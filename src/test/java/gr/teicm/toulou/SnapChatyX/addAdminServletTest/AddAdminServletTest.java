package gr.teicm.toulou.SnapChatyX.addAdminServletTest;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import gr.teicm.toulou.SnapChatyX.addAdminServlet.AddAdminResult;
import gr.teicm.toulou.SnapChatyX.addAdminServlet.AddAdminServlet;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

public class AddAdminServletTest {
	
	private SnapClient admin;
	private AddAdminServlet servlet;
	private MultivaluedMap<String , String> parameters;
	private Response result;	
	private Gson gson;	
	private String expectedResult;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp(){
		
		admin = new SnapClient();
		
		servlet = new AddAdminServlet();
		
		parameters = new MultivaluedHashMap<String , String >();
		
		gson = new Gson();
		
		dataAccessObject = DataAccessObject.DAO;
			
	}
	
	@After
	public void CleanUp() {
		
		dataAccessObject.unregisterSnapClient( admin );
		
		dataAccessObject.removeUserFromAdminList( admin.getUsername() );
	}
	
	@Test
	public void testAddAdminSuccessfully(){
		
		//SetUp
		admin.setUsername("Stefos");
		
		dataAccessObject.registerSnapClient(  admin );
		
		parameters.add("user", "Stefos");
		
		//Execution
		result = servlet.addAdmin( parameters );
		
		expectedResult = gson.toJson( new AddAdminResult( Boolean.TRUE ));
		
		//Verification		
		Assert.assertEquals( expectedResult, result.getEntity() );
	}
	
	@Test
	public void testAddAdminAnNonExistedUsername() {
		
		//SetUp
		parameters.add("user", "tasos");
		
		//Execution
		result = servlet.addAdmin(parameters);
		
		expectedResult = gson.toJson( new AddAdminResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
	
	@Test
	public void testAddAdminWithNullParam() {
		
		//Execution		
		result = servlet.addAdmin(null);
		
		//Verification
		Assert.assertEquals( Status.BAD_REQUEST.getStatusCode(), result.getStatus());
	}
	
	@Test
	public void testAddAdminAnEmptyUsername() {
		
		//SetUp
		parameters.add("user", "");
		
		//Execution
		result = servlet.addAdmin(parameters);
		
		expectedResult = gson.toJson( new AddAdminResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}

}
