package gr.teicm.toulou.SnapChatyX.service;

import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;

public class SnapClientServiceTest {

	private SnapClientService target;
	
	@Before
	public void setUp() {
		 target = new SnapClientService();
	}
	
	@Test
	public void testCreateSnapClientSuccess() {
		
		//SetUp
		SnapClient snapClient = new SnapClient();
		snapClient.setUsername("mitsos");
		snapClient.setPassword("God");
		snapClient.setLongitude(3.14);
		snapClient.setLatitude(45.0);
		snapClient.setLocationName("Serres");
		snapClient.setFirstName("Dimitrios");
		snapClient.setLastName("Papadopoulos");
		
		//Execution
		target.createSnapClient(snapClient);
		
		//Verification
		
		
		//TearDown
	}
		
	
}
