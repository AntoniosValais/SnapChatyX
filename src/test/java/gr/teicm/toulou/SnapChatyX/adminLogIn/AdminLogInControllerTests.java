package gr.teicm.toulou.SnapChatyX.adminLogIn;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

public class AdminLogInControllerTests {

	private AdminLogInController adminLogInController;
	private SnapClient admin;
	private String adminName;
	private String password;
	private Boolean result;
	private InterfaceDataAccessObject dataAccessObject;
	
	@Before
	public void SetUp() {
		
		admin = new SnapClient();
		
		admin.setUsername("Eftixia");
		
		admin.setPassword("a");
		
		adminLogInController = new AdminLogInController();
		
		dataAccessObject = DataAccessObject.DAO;
		
		dataAccessObject.registerSnapClient( admin);
		
	}
	
	@After
	public void CleanUp() {
		
		dataAccessObject.unregisterSnapClient( admin);
		
	}
	
	@Test
	public void testAdminLogInSuccess() {
		
		//SetUp
		adminName = "Eftixia";
		password = "a";
		
		//Execution
		result = adminLogInController.verify(adminName, password);
		
		//Verification
		Assert.assertTrue(result);
	
	}
	
	@Test 
	public void testAdminLogInInValidParam() {
		
		//SetUp
		adminName = "Efi";
		password = "b";
		
		//Execution
		result = adminLogInController.verify(adminName, password);
		
		//Verification
		Assert.assertFalse(result);
	
	}
	
	@Test
	public void testAdminLogInNullParam(){
		
	
		//Execution
		result = adminLogInController.verify(null, null);
			
		//Verification
		Assert.assertFalse(result);
	
	}	
}
