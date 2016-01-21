package gr.teicm.toulou.SnapChatyX.removeAdminServletTest;

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
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.removeAdminServlet.RemoveAdminResult;
import gr.teicm.toulou.SnapChatyX.removeAdminServlet.RemoveAdminServlet;

public class RemoveAdminServletTest {
	
	private SnapClient admin;
	private RemoveAdminServlet servlet;
	private MultivaluedMap<String , String> parameters;
	private Response result;	
	private Gson gson;	
	private String expectedResult;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp(){
		
		admin = new SnapClient();
		
		servlet = new RemoveAdminServlet();
		
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
	public void testRemoveAdminSuccessfully(){
		
		//SetUp
		admin.setUsername("Stefos");
		
		dataAccessObject.registerSnapClient(  admin );
		dataAccessObject.addUserToAdminList( admin.getUsername() );
		
		parameters.add("user", "Stefos");
		
		//Execution
		result = servlet.removeAdmin( parameters );
		
		expectedResult = gson.toJson( new RemoveAdminResult( Boolean.TRUE ));
		
		//Verification		
		Assert.assertEquals( expectedResult, result.getEntity() );
	}
	
	@Test
	public void testRemoveAdminAnNonExistedUsername() {
		
		//SetUp
		parameters.add("user", "tasos");
		
		//Execution
		result = servlet.removeAdmin(parameters);
		
		expectedResult = gson.toJson( new RemoveAdminResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}
	
	@Test
	public void testRemoveAdminWithNullParam() {
		
		//Execution		
		result = servlet.removeAdmin(null);
		
		//Verification
		Assert.assertEquals( Status.BAD_REQUEST.getStatusCode(), result.getStatus());
	}
	
	@Test
	public void testRemoveAdminAnEmptyUsername() {
		
		//SetUp
		parameters.add("user", "");
		
		//Execution
		result = servlet.removeAdmin(parameters);
		
		expectedResult = gson.toJson( new AddAdminResult( Boolean.FALSE ));
		
		//Verification
		Assert.assertEquals( expectedResult, result.getEntity() );
		
	}

}
