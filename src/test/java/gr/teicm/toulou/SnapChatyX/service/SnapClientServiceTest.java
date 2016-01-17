package gr.teicm.toulou.SnapChatyX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
		
	@Test
	public void testUpdateSnapClientSuccess()
	{
		//SetUp
		
		SnapClient snapClient1 = new SnapClient();
		snapClient1.setUsername("ketsis");
		snapClient1.setPassword("123");
		snapClient1.setLongitude(53.3425);
		snapClient1.setLatitude(36.3453);
		snapClient1.setLocationName("Kriti");
		snapClient1.setFirstName("Swthrhs");
		snapClient1.setLastName("Koukos");
		snapClient1.setEmail("asd2@gmail.com");
		
		SnapClient friend1 = new SnapClient();
		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList(friend1);
		
		SnapClient black1 = new SnapClient();
		black1.setUsername("Stefos");
		snapClient1.addToBlackList(black1);
		
		SnapClient snapClient2 = new SnapClient();
		snapClient2.setUsername("ketsis");
		snapClient2.setPassword("123");
		snapClient2.setLongitude(53.3425);
		snapClient2.setLatitude(36.3453);
		snapClient2.setLocationName("Kriti");
		snapClient2.setFirstName("Swthrhs");
		snapClient2.setLastName("Koukos");
		snapClient2.setEmail("asd2@gmail.com");
		
		SnapClient friend2 = new SnapClient();
		friend2.setUsername("Pitsos");
		snapClient2.addToFriendList(friend2);
		
		SnapClient black2 = new SnapClient();
		black2.setUsername("Litsos");
		snapClient2.addToBlackList(black2);
		
		SnapClientDAO dao = new SnapClientDAO();
		SnapClientEntity entity = new SnapClientEntity();
		entity.setUsername(snapClient1.getUsername());
		entity.setPassword(snapClient1.getPassword());
		entity.setLongitude(snapClient1.getLongitude());
		entity.setLatitude(snapClient1.getLatitude());
		entity.setLocationName(snapClient1.getLocationName());
		entity.setFirstName(snapClient1.getFirstName());
		entity.setLastName(snapClient1.getLastName());
		entity.setEmail(snapClient1.getEmail());
		
		List<String> fnList = new ArrayList<>();
		for(SnapClient sc : snapClient1.getFriendList())
		{
			fnList.add(sc.getUsername());
		}
		entity.setFriendList(fnList);
		
		List<String> bnList = new ArrayList<>();
		for(SnapClient sc : snapClient1.getBlackList())
		{
			bnList.add(sc.getUsername());
		}
		entity.setBlackList(bnList);
		
		dao.createSnapClientEntity(entity);
		
		//Execution
		boolean successUpdate = target.updateSnapClient(snapClient2);
		
		//Verification
		Assert.assertTrue(successUpdate);
		String id;
		for(SnapClientEntity sc : dao.getAllSnapClients())
		{
			if(snapClient2.getUsername() == sc.getUsername())
			{
				id = sc.getId();
			}
		}
		SnapClientEntity result = dao.getSnapClientEntityById(id);
		Assert.assertNotNull(result);
		Assert.assertEquals(snapClient2.getUsername(), result.getUsername());
		Assert.assertEquals(snapClient2.getPassword(), result.getPassword());
		Assert.assertEquals(snapClient2.getLongitude(), result.getLongitude());
		Assert.assertEquals(snapClient2.getLatitude(), result.getLatitude());
		Assert.assertEquals(snapClient2.getLocationName(), result.getLocationName());
		Assert.assertEquals(snapClient2.getFirstName(), result.getFirstName());
		Assert.assertEquals(snapClient2.getLastName(), result.getLastName());
		Assert.assertEquals(snapClient2.getEmail(), result.getEmail());
		Assert.assertEquals(snapClient2.getFriendList().get(0).getUsername(), result.getFriendList().get(0));
		Assert.assertEquals(snapClient2.getBlackList().get(0).getUsername(), result.getBlackList().get(0));
		
		//TearDown
		
		dao.deleteSnapClientEntity(result);
		Assert.assertNull(dao.getDatastore().exists(result));
	}
	
	@Test
	public void testUpdateSnapClientParamNoEntry()
	{
		//SetUp
		SnapClient snapClient1 = new SnapClient();
		snapClient1.setUsername("ketsis");
		snapClient1.setPassword("123");
		snapClient1.setLongitude(53.3425);
		snapClient1.setLatitude(36.3453);
		snapClient1.setLocationName("Kriti");
		snapClient1.setFirstName("Swthrhs");
		snapClient1.setLastName("Koukos");
		snapClient1.setEmail("asd2@gmail.com");
		
		SnapClient friend1 = new SnapClient();
		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList(friend1);
		
		SnapClient black1 = new SnapClient();
		black1.setUsername("Stefos");
		snapClient1.addToBlackList(black1);
		
		//Execution
		boolean successUpdate = target.updateSnapClient(snapClient1);
		
		//Verification
		Assert.assertFalse(successUpdate);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateSnapClientParamNull()
	{
		//SetUp
				
		//Execution
		target.updateSnapClient(null);
		
		//Verification
		
	}
	
	@Test
	public void testDeleteSnapClientSuccess()
	{
		//SetUp

		SnapClient snapClient1 = new SnapClient();
		snapClient1.setUsername("ketsis");
		snapClient1.setPassword("123");
		snapClient1.setLongitude(53.3425);
		snapClient1.setLatitude(36.3453);
		snapClient1.setLocationName("Kriti");
		snapClient1.setFirstName("Swthrhs");
		snapClient1.setLastName("Koukos");
		snapClient1.setEmail("asd2@gmail.com");
		
		SnapClient friend1 = new SnapClient();
		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList(friend1);
		
		SnapClient black1 = new SnapClient();
		black1.setUsername("Stefos");
		snapClient1.addToBlackList(black1);
		
		SnapClientDAO dao = new SnapClientDAO();
		SnapClientEntity entity = new SnapClientEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setUsername(snapClient1.getUsername());
		entity.setPassword(snapClient1.getPassword());
		entity.setLongitude(snapClient1.getLongitude());
		entity.setLatitude(snapClient1.getLatitude());
		entity.setLocationName(snapClient1.getLocationName());
		entity.setFirstName(snapClient1.getFirstName());
		entity.setLastName(snapClient1.getLastName());
		entity.setEmail(snapClient1.getEmail());
		
		List<String> fnList = new ArrayList<>();
		for(SnapClient sc : snapClient1.getFriendList())
		{
			fnList.add(sc.getUsername());
		}
		entity.setFriendList(fnList);
		
		List<String> bnList = new ArrayList<>();
		for(SnapClient sc : snapClient1.getBlackList())
		{
			bnList.add(sc.getUsername());
		}
		entity.setBlackList(bnList);
		
		dao.createSnapClientEntity(entity);
		
		//Execution
		boolean successDelete = target.deleteSnapClient(snapClient1);
		
		//Verification
		Assert.assertTrue(successDelete);
		Assert.assertNull(dao.getDatastore().exists(entity));
	}
	
	@Test
	public void testDeleteSnapClientParamNoEntry()
	{
		//SetUp
		SnapClient snapClient1 = new SnapClient();
		snapClient1.setUsername("ketsis");
		snapClient1.setPassword("123");
		snapClient1.setLongitude(53.3425);
		snapClient1.setLatitude(36.3453);
		snapClient1.setLocationName("Kriti");
		snapClient1.setFirstName("Swthrhs");
		snapClient1.setLastName("Koukos");
		snapClient1.setEmail("asd2@gmail.com");
		
		SnapClient friend1 = new SnapClient();
		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList(friend1);
		
		SnapClient black1 = new SnapClient();
		black1.setUsername("Stefos");
		snapClient1.addToBlackList(black1);
		
		//Execution
		boolean successDelete = target.deleteSnapClient(snapClient1);
		
		//Verification
		Assert.assertFalse(successDelete);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteSnapClientParamNull()
	{
		//SetUp
				
		//Execution
		target.deleteSnapClient(null);
		
		//Verification
		
	}
	
	
	
}
