package gr.teicm.toulou.SnapChatyX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import gr.teicm.toulou.SnapChatyX.dao.SnapClientDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

public class SnapClientServiceTest {

	private SnapClientService target;
	
	@Before
	public void setUp() {
		 target = new SnapClientService();
	}
	
	@Test
	public void testCreateSnapClientSuccess() {
		
		// SetUp
		final SnapClient snapClient = new SnapClient();
		
		final SnapClientEntity entityFixture = new SnapClientEntity();
		
		final SnapClientModelToEntityTransformer mockTransformer =
				Mockito.mock(SnapClientModelToEntityTransformer.class);
		target.setModelToEntityTransformer(mockTransformer);
		Mockito.when(mockTransformer.transform(snapClient)).thenReturn(entityFixture);
		
		final SnapClientDAO mockDao = Mockito.mock(SnapClientDAO.class);
		target.setDao(mockDao);
		Mockito.when(mockDao.createSnapClientEntity(entityFixture))
		.thenReturn(true);
		
		// Execution
		final boolean creationDone = target.createSnapClient(snapClient);
		
		// Verification
		Mockito.verify(mockTransformer).transform(snapClient);
		Mockito.verify(mockDao).createSnapClientEntity(entityFixture);
		
		Assert.assertTrue(creationDone);
		
		// TearDown
		
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSnapClientPramNull() {
		
		//SetUp
		
		//Execution
		target.createSnapClient(null);

		//Verification
		
		//TearDown
	}
	
	@Test
	public void testGetSnapClientByUsernameSuccess() throws Exception {
		//SetUp
		
		//Execution
		target.getSnapClientByUsername(username);
		
		//Verification
		
		//TearDown
	}
	
	@Test
	public void testGetSnapClientByUsernameParamNull() throws Exception  {
		//SetUp
		
		//Execution
		target.getSnapClientByUsername(null);
		
		//Verification
		
		//TearDown
	}
	
	@Test
	public void testGetSnapClientByUsernameParamEmpty() throws Exception  {
		//SetUp
		
		//Execution
		target.getSnapClientByUsername("");
		
		//Verification
		
		//TearDown
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
		
//		SnapClient friend1 = new SnapClient();
//		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList("Kitsos");
		
//		SnapClient black1 = new SnapClient();
//		black1.setUsername("Stefos");
		snapClient1.addToBlackList("Stefos");
		
		SnapClient snapClient2 = new SnapClient();
		snapClient2.setUsername("ketsis");
		snapClient2.setPassword("123");
		snapClient2.setLongitude(53.3425);
		snapClient2.setLatitude(36.3453);
		snapClient2.setLocationName("Kriti");
		snapClient2.setFirstName("Swthrhs");
		snapClient2.setLastName("Koukos");
		snapClient2.setEmail("asd2@gmail.com");
		
//		SnapClient friend2 = new SnapClient();
//		friend2.setUsername("Pitsos");
		snapClient2.addToFriendList("Pitsos");
		
//		SnapClient black2 = new SnapClient();
//		black2.setUsername("Litsos");
		snapClient2.addToBlackList("Litsos");
		
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
		
//		List<String> fnList = new ArrayList<>();
//		for(SnapClient sc : snapClient1.getFriendList())
//		{
//			fnList.add(sc.getUsername());
//		}
		entity.setFriendList(snapClient1.getFriendList());
		
//		List<String> bnList = new ArrayList<>();
//		for(SnapClient sc : snapClient1.getBlackList())
//		{
//			bnList.add(sc.getUsername());
//		}
		entity.setBlackList(snapClient1.getBlackList());
		
		dao.createSnapClientEntity(entity);
		
		//Execution
		boolean successUpdate = target.updateSnapClient(snapClient2);
		
		//Verification
		Assert.assertTrue(successUpdate);
		String id = null;
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
		Assert.assertEquals(snapClient2.getFriendList().get(0), result.getFriendList().get(0));
		Assert.assertEquals(snapClient2.getBlackList().get(0), result.getBlackList().get(0));
		
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
		
//		SnapClient friend1 = new SnapClient();
//		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList("Kitsos");
		
//		SnapClient black1 = new SnapClient();
//		black1.setUsername("Stefos");
		snapClient1.addToBlackList("Stefos");
		
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
		
//		SnapClient friend1 = new SnapClient();
//		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList("Kitsos");
		
//		SnapClient black1 = new SnapClient();
//		black1.setUsername("Stefos");
		snapClient1.addToBlackList("Stefos");
		
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
		
//		List<String> fnList = new ArrayList<>();
//		for(SnapClient sc : snapClient1.getFriendList())
//		{
//			fnList.add(sc.getUsername());
//		}
		entity.setFriendList(snapClient1.getFriendList());
		
//		List<String> bnList = new ArrayList<>();
//		for(SnapClient sc : snapClient1.getBlackList())
//		{
//			bnList.add(sc.getUsername());
//		}
		entity.setBlackList(snapClient1.getBlackList());
		
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
		
//		SnapClient friend1 = new SnapClient();
//		friend1.setUsername("Kitsos");
		snapClient1.addToFriendList("Kitsos");
		
//		SnapClient black1 = new SnapClient();
//		black1.setUsername("Stefos");
		snapClient1.addToBlackList("Stefos");
		
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
