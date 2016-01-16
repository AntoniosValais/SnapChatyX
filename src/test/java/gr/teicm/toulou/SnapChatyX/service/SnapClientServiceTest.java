package gr.teicm.toulou.SnapChatyX.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.dao.SnapClientDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

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
		snapClient.setEmail("dim@pap.cm");
		
		SnapClientDAO dao = new SnapClientDAO();
		SnapClientEntity entity = new SnapClientEntity();
		entity.setUsername("mitsos");
		entity.setPassword("God");
		entity.setLongitude(3.14);
		entity.setLatitude(45.0);
		entity.setLocationName("Serres");
		entity.setFirstName("Dimitrios");
		entity.setLastName("Papadopoulos");
		entity.setEmail("dim@pap.cm");
		
		dao.getDatastore().save(entity);
		
		//Execution
		target.createSnapClient(snapClient);
		
		//Verification	
		SnapClientEntity result = dao.getDatastore().find(SnapClientEntity.class, "username", snapClient.getUsername()).get();
		Assert.assertNotNull(result);
		Assert.assertEquals(snapClient.getUsername(), result.getUsername());
		Assert.assertEquals(snapClient.getPassword(), result.getPassword());
		Assert.assertEquals(snapClient.getLongitude(), result.getLongitude());
		Assert.assertEquals(snapClient.getLatitude(), result.getLatitude());
		Assert.assertEquals(snapClient.getLocationName(), result.getLocationName());
		Assert.assertEquals(snapClient.getFirstName(), result.getFirstName());
		Assert.assertEquals(snapClient.getLastName(), result.getLastName());
		Assert.assertEquals(snapClient.getEmail(), result.getEmail());
		
		//TearDown
		dao.getDatastore().delete(result);
		Assert.assertNotNull(dao.getDatastore().exists(result));
		
	}
		
	
}
