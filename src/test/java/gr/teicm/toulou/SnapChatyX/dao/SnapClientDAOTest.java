package gr.teicm.toulou.SnapChatyX.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientDAOTest {

	private SnapClientEntityDAO target;
	
	@Before
	public void setUp() {
		target = new SnapClientEntityDAO();
	}
	
	@Test
	public void testCreateSnapClientEntitySuccess() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity();
		
		entity.setId(UUID.randomUUID().toString());
		
		entity.setUsername("orestis");
		
		entity.setFirstName("Orestis");
		
		entity.setLastName("Zagkoumidis");
		
		entity.setPassword("OresZagk");
		
		entity.setEmail("ores@zagk.com");
		
		entity.setLongitude(15.4);
		
		entity.setLatitude(12.1);
		
		entity.setLocationName("Thessaloniki");
		
		List<String> friendList = new ArrayList<String>();
		friendList.add("nikos");
		entity.setFriendList(friendList);
		
		List<String> blackList = new ArrayList<String>();
		blackList.add("petalidis");
		entity.setBlackList(blackList);
		
		// Execution
		boolean creationDone = target.createSnapClientEntity(entity);
		
		// Verification
		final SnapClientEntity entityResult = 
				target.getDatastore().get(SnapClientEntity.class, entity.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertTrue(creationDone);
		
		assertEquals(
				"The ids must be equal.",
				entity.getId(),
				entityResult.getId()
		);
		
		assertEquals(
				"Usernames must be equal.",
				entity.getUsername(),
				entityResult.getUsername()
		);
		
		assertEquals(
				"First Names must be equal.",
				entity.getFirstName(),
				entityResult.getFirstName()
		);
		
		assertEquals(
				"Last Names must be equal.",
				entity.getLastName(),
				entityResult.getLastName()
		);
		
		assertEquals(
				"Password must be equal.",
				entity.getPassword(),
				entityResult.getPassword()
		);
		
		assertEquals(
				"Emails must be equal.",
				entity.getEmail(),
				entityResult.getEmail()
		);
		
		assertEquals(
				"Longitudes must be equal.",
				entity.getLongitude(),
				entityResult.getLongitude()
		);
		
		assertEquals(
				"Latitudes must be equal.",
				entity.getLatitude(),
				entityResult.getLatitude()
		);
		
		assertEquals(
				"LocationNames must be equal.",
				entity.getLocationName(),
				entityResult.getLocationName()
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity.getFriendList().get(0),
				entityResult.getFriendList().get(0)
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity.getBlackList().get(0),
				entityResult.getBlackList().get(0)
		);
		
		// TearDown
		
		target.getDatastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entityResult)
		);
		
	}
	
	@Test
	public void testGetSnapClientEntitySuccess() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity();
		
		entity.setId(UUID.randomUUID().toString());
		
		entity.setUsername("orestis");
		
		entity.setFirstName("Orestis");
		
		entity.setLastName("Zagkoumidis");
		
		entity.setPassword("OresZagk");
		
		entity.setEmail("ores@zagk.com");
		
		entity.setLongitude(15.4);
		
		entity.setLatitude(12.1);
		
		entity.setLocationName("Thessaloniki");
		
		List<String> friendList = new ArrayList<String>();
		friendList.add("nikos");
		entity.setFriendList(friendList);
		
		List<String> blackList = new ArrayList<String>();
		blackList.add("petalidis");
		entity.setBlackList(blackList);
		
		target.save(entity);
		
		//Execution
		SnapClientEntity entityResult = target.getSnapClientEntityById(entity.getId());
		
		//Verification		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entity.getId(),
				entityResult.getId()
		);
		
		assertEquals(
				"Usernames must be equal.",
				entity.getUsername(),
				entityResult.getUsername()
		);
		
		assertEquals(
				"First Names must be equal.",
				entity.getFirstName(),
				entityResult.getFirstName()
		);
		
		assertEquals(
				"Last Names must be equal.",
				entity.getLastName(),
				entityResult.getLastName()
		);
		
		assertEquals(
				"Password must be equal.",
				entity.getPassword(),
				entityResult.getPassword()
		);
		
		assertEquals(
				"Emails must be equal.",
				entity.getEmail(),
				entityResult.getEmail()
		);
		
		assertEquals(
				"Longitudes must be equal.",
				entity.getLongitude(),
				entityResult.getLongitude()
		);
		
		assertEquals(
				"Latitudes must be equal.",
				entity.getLatitude(),
				entityResult.getLatitude()
		);
		
		assertEquals(
				"LocationNames must be equal.",
				entity.getLocationName(),
				entityResult.getLocationName()
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity.getFriendList().get(0),
				entityResult.getFriendList().get(0)
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity.getBlackList().get(0),
				entityResult.getBlackList().get(0)
		);
		
		//TearDown
		target.getDatastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entityResult)
		);	
		
	}
	
	@Test
	public void testGetAllSnapClientsSuccess() {
		
		// SetUp
		SnapClientEntity entity1 = new SnapClientEntity();
		
		entity1.setId(UUID.randomUUID().toString());
		
		entity1.setUsername("Ntina");
		
		entity1.setFirstName("Ntina");
		
		entity1.setLastName("Avgeri");
		
		entity1.setPassword("12345");
		
		entity1.setEmail("konna@mail.com");
		
		entity1.setLongitude(45.9);
		
		entity1.setLatitude(71.9);
		
		entity1.setLocationName("Komotini");
		
		List<String> friendList1 = new ArrayList<String>();
		friendList1.add("teo");
		entity1.setFriendList(friendList1);
		
		List<String> blackList1 = new ArrayList<String>();
		blackList1.add("lantzos");
		entity1.setBlackList(blackList1);
		
		SnapClientEntity entity2 = new SnapClientEntity();
		
		entity2.setId(UUID.randomUUID().toString());
		
		entity2.setUsername("Afroksulanthi");
		
		entity2.setFirstName("Afro");
		
		entity2.setLastName("papadopoulou");
		
		entity2.setPassword("123456");
		
		entity2.setEmail("afro@mail.com");
		
		entity2.setLongitude(23.56);
		
		entity2.setLatitude(12.7);
		
		entity2.setLocationName("Ksanthi");
		
		List<String> friendList = new ArrayList<String>();
		friendList.add("brand");
		entity2.setFriendList(friendList);
		
		List<String> blackList = new ArrayList<String>();
		blackList.add("pitt");
		entity2.setBlackList(blackList);
		
		target.getDatastore().save(entity1);
		target.getDatastore().save(entity2);
		
		// Execution
		List<SnapClientEntity> resultList = target.getAllSnapClients();
		
		// Verification
		assertNotNull(resultList);
		assertEquals(2, resultList.size());
		
		assertNotNull(resultList.get(0));
		assertEquals(entity1.getId(), resultList.get(0).getId());
		assertEquals(entity1.getUsername(), resultList.get(0).getUsername());
		assertEquals(entity1.getPassword(), resultList.get(0).getPassword());
		assertEquals(entity1.getEmail(), resultList.get(0).getEmail());
		assertEquals(entity1.getFirstName(), resultList.get(0).getFirstName());
		assertEquals(entity1.getLastName(), resultList.get(0).getLastName());
		assertEquals(entity1.getLatitude(), resultList.get(0).getLatitude());
		assertEquals(entity1.getLongitude(), resultList.get(0).getLongitude());
		assertEquals(entity1.getLocationName(), resultList.get(0).getLocationName());
		assertEquals(
				entity1.getFriendList().get(0),
				resultList.get(0).getFriendList().get(0)
		);
		assertEquals(
				entity1.getBlackList().get(0),
				resultList.get(0).getBlackList().get(0)
		);
		
		assertNotNull(resultList.get(1));
		assertEquals(entity2.getId(), resultList.get(1).getId());
		assertEquals(entity2.getUsername(), resultList.get(1).getUsername());
		assertEquals(entity2.getPassword(), resultList.get(1).getPassword());
		assertEquals(entity2.getEmail(), resultList.get(1).getEmail());
		assertEquals(entity2.getFirstName(), resultList.get(1).getFirstName());
		assertEquals(entity2.getLastName(), resultList.get(1).getLastName());
		assertEquals(entity2.getLatitude(), resultList.get(1).getLatitude());
		assertEquals(entity2.getLongitude(), resultList.get(1).getLongitude());
		assertEquals(entity2.getLocationName(), resultList.get(1).getLocationName());
		assertEquals(
				entity2.getFriendList().get(0),
				resultList.get(1).getFriendList().get(0)
		);
		assertEquals(
				entity2.getBlackList().get(0),
				resultList.get(1).getBlackList().get(0)
		);
		
		// TearDown
		target.getDatastore().delete(resultList.get(0));
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(resultList.get(0))
		);
		
		target.getDatastore().delete(resultList.get(1));
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(resultList.get(1))
		);
		
	}
	
	@Test
	public void testGetAllSnapClientsNoEntries() {
		
		// SetUp
		
		// Execution
		List<SnapClientEntity> resultList = target.getAllSnapClients();
		
		// Verification
		assertNotNull(resultList);
		assertEquals(0, resultList.size());
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientFriendListSuccess() {
		
		//SetUp
		SnapClientEntity entity1 = new SnapClientEntity();		
		entity1.setId(UUID.randomUUID().toString());		
		entity1.setUsername("orestis");		
		entity1.setFirstName("Orestis");		
		entity1.setLastName("Zagkoumidis");		
		entity1.setPassword("OresZagk");		
		entity1.setEmail("ores@zagk.com");		
		entity1.setLongitude(15.4);		
		entity1.setLatitude(12.1);		
		entity1.setLocationName("Thessaloniki");
		
		List<String> friendList1 = new ArrayList<String>();
		friendList1.add("nikos");
		entity1.setFriendList(friendList1);
		
		List<String> blackList1 = new ArrayList<String>();
		blackList1.add("petalidis");
		entity1.setBlackList(blackList1);
		
		target.save(entity1);
		
		SnapClientEntity entity2 = new SnapClientEntity();		
		entity2.setId(entity1.getId());		
		entity2.setUsername(entity1.getUsername());		
		entity2.setFirstName(entity1.getFirstName());		
		entity2.setLastName(entity1.getLastName());		
		entity2.setPassword(entity1.getPassword());		
		entity2.setEmail(entity1.getEmail());		
		entity2.setLongitude(entity1.getLongitude());		
		entity2.setLatitude(entity1.getLatitude());		
		entity2.setLocationName(entity1.getLocationName());
		
		List<String> friendList2 = new ArrayList<String>();
		friendList2.add("nikos");
		friendList2.add("kitsos");
		entity2.setFriendList(friendList2);
		
		//Execution
		target.updateSnapClientFriendList(entity2);
		
		//Verification
		final SnapClientEntity entityResult = 
				target.getDatastore().get(SnapClientEntity.class, entity2.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entity2.getId(),
				entityResult.getId()
		);
		
		assertEquals(
				"Usernames must be equal.",
				entity2.getUsername(),
				entityResult.getUsername()
		);
		
		assertEquals(
				"First Names must be equal.",
				entity2.getFirstName(),
				entityResult.getFirstName()
		);
		
		assertEquals(
				"Last Names must be equal.",
				entity2.getLastName(),
				entityResult.getLastName()
		);
		
		assertEquals(
				"Password must be equal.",
				entity2.getPassword(),
				entityResult.getPassword()
		);
		
		assertEquals(
				"Emails must be equal.",
				entity2.getEmail(),
				entityResult.getEmail()
		);
		
		assertEquals(
				"Longitudes must be equal.",
				entity2.getLongitude(),
				entityResult.getLongitude()
		);
		
		assertEquals(
				"Latitudes must be equal.",
				entity2.getLatitude(),
				entityResult.getLatitude()
		);
		
		assertEquals(
				"LocationNames must be equal.",
				entity2.getLocationName(),
				entityResult.getLocationName()
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity2.getFriendList().get(0),
				entityResult.getFriendList().get(0)
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity2.getFriendList().get(1),
				entityResult.getFriendList().get(1)
		);		
		
		//TearDown
		target.getDatastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entityResult)
		);
		
	}
	
	@Test
	public void testUpdateSnapClientBlackListSuccess() {
		
		//SetUp
		SnapClientEntity entity1 = new SnapClientEntity();		
		entity1.setId(UUID.randomUUID().toString());		
		entity1.setUsername("orestis");		
		entity1.setFirstName("Orestis");		
		entity1.setLastName("Zagkoumidis");		
		entity1.setPassword("OresZagk");		
		entity1.setEmail("ores@zagk.com");		
		entity1.setLongitude(15.4);		
		entity1.setLatitude(12.1);		
		entity1.setLocationName("Thessaloniki");
		
		List<String> friendList1 = new ArrayList<String>();
		friendList1.add("nikos");
		entity1.setFriendList(friendList1);
		
		List<String> blackList1 = new ArrayList<String>();
		blackList1.add("petalidis");
		entity1.setBlackList(blackList1);
		
		target.save(entity1);
		
		SnapClientEntity entity2 = new SnapClientEntity();		
		entity2.setId(entity1.getId());		
		entity2.setUsername(entity1.getUsername());		
		entity2.setFirstName(entity1.getFirstName());		
		entity2.setLastName(entity1.getLastName());		
		entity2.setPassword(entity1.getPassword());		
		entity2.setEmail(entity1.getEmail());		
		entity2.setLongitude(entity1.getLongitude());		
		entity2.setLatitude(entity1.getLatitude());		
		entity2.setLocationName(entity1.getLocationName());
		
		List<String> blackList2 = new ArrayList<String>();
		blackList2.add("petalidis");
		blackList2.add("mitsos");
		entity2.setBlackList(blackList2);
		
		//Execution
		target.updateSnapClientBlackList(entity2);
		
		//Verification
		final SnapClientEntity entityResult = 
				target.getDatastore().get(SnapClientEntity.class, entity1.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entity2.getId(),
				entityResult.getId()
		);
		
		assertEquals(
				"Usernames must be equal.",
				entity2.getUsername(),
				entityResult.getUsername()
		);
		
		assertEquals(
				"First Names must be equal.",
				entity2.getFirstName(),
				entityResult.getFirstName()
		);
		
		assertEquals(
				"Last Names must be equal.",
				entity2.getLastName(),
				entityResult.getLastName()
		);
		
		assertEquals(
				"Password must be equal.",
				entity2.getPassword(),
				entityResult.getPassword()
		);
		
		assertEquals(
				"Emails must be equal.",
				entity2.getEmail(),
				entityResult.getEmail()
		);
		
		assertEquals(
				"Longitudes must be equal.",
				entity2.getLongitude(),
				entityResult.getLongitude()
		);
		
		assertEquals(
				"Latitudes must be equal.",
				entity2.getLatitude(),
				entityResult.getLatitude()
		);
		
		assertEquals(
				"LocationNames must be equal.",
				entity2.getLocationName(),
				entityResult.getLocationName()
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity2.getBlackList().get(0),
				entityResult.getBlackList().get(0)
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity2.getBlackList().get(1),
				entityResult.getBlackList().get(1)
		);
		
		//TearDown
		target.getDatastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entityResult)
		);
		
	}
	
	@Test
	public void testUpdateSnapClientEntitySuccess() {
		
		//SetUp
		SnapClientEntity entity1 = new SnapClientEntity();		
		entity1.setId(UUID.randomUUID().toString());		
		entity1.setUsername("orestis");		
		entity1.setFirstName("Orestis");		
		entity1.setLastName("Zagkoumidis");		
		entity1.setPassword("OresZagk");		
		entity1.setEmail("ores@zagk.com");		
		entity1.setLongitude(15.4);		
		entity1.setLatitude(12.1);		
		entity1.setLocationName("Thessaloniki");
		
		List<String> friendList1 = new ArrayList<String>();
		friendList1.add("nikos");
		entity1.setFriendList(friendList1);
		
		List<String> blackList1 = new ArrayList<String>();
		blackList1.add("petalidis");
		entity1.setBlackList(blackList1);
		
		target.save(entity1);
		
		SnapClientEntity entity2 = new SnapClientEntity();		
		entity2.setId(entity1.getId());		
		entity2.setUsername(entity1.getUsername());		
		entity2.setFirstName(entity1.getFirstName());		
		entity2.setLastName(entity1.getLastName());		
		entity2.setPassword(entity1.getPassword());		
		entity2.setEmail(entity1.getEmail());		
		entity2.setLongitude(entity1.getLongitude());		
		entity2.setLatitude(entity1.getLatitude());		
		entity2.setLocationName(entity1.getLocationName());
		
		List<String> friendList2 = new ArrayList<String>();
		friendList2.add("nikos");
		friendList2.add("kitsos");
		entity2.setFriendList(friendList2);
		
		List<String> blackList2 = new ArrayList<String>();
		blackList2.add("petalidis");
		blackList2.add("mitsos");
		entity2.setBlackList(blackList2);
		
		//Execution
		target.updateSnapClientEntity(entity2);
		
		//Verification
		final SnapClientEntity entityResult = 
				target.getDatastore().get(SnapClientEntity.class, entity2.getId());
		
		assertNotNull("The entityResult must not be null.", entityResult);
		assertEquals(
				"The ids must be equal.",
				entity2.getId(),
				entityResult.getId()
		);
		
		assertEquals(
				"Usernames must be equal.",
				entity2.getUsername(),
				entityResult.getUsername()
		);
		
		assertEquals(
				"First Names must be equal.",
				entity2.getFirstName(),
				entityResult.getFirstName()
		);
		
		assertEquals(
				"Last Names must be equal.",
				entity2.getLastName(),
				entityResult.getLastName()
		);
		
		assertEquals(
				"Password must be equal.",
				entity2.getPassword(),
				entityResult.getPassword()
		);
		
		assertEquals(
				"Emails must be equal.",
				entity2.getEmail(),
				entityResult.getEmail()
		);
		
		assertEquals(
				"Longitudes must be equal.",
				entity2.getLongitude(),
				entityResult.getLongitude()
		);
		
		assertEquals(
				"Latitudes must be equal.",
				entity2.getLatitude(),
				entityResult.getLatitude()
		);
		
		assertEquals(
				"LocationNames must be equal.",
				entity2.getLocationName(),
				entityResult.getLocationName()
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity2.getFriendList().get(0),
				entityResult.getFriendList().get(0)
		);
		
		assertEquals(
				"FriendLists must be equal.",
				entity2.getFriendList().get(1),
				entityResult.getFriendList().get(1)
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity2.getBlackList().get(0),
				entityResult.getBlackList().get(0)
		);
		
		assertEquals(
				"BlackLists must be equal.",
				entity2.getBlackList().get(1),
				entityResult.getBlackList().get(1)
		);
		
		//TearDown
		target.getDatastore().delete(entityResult);
		
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entityResult)
		);
		
	}
	
	@Test
	public void testDeleteSnapClientEntitySuccess() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity();
		
		entity.setId(UUID.randomUUID().toString());
		
		entity.setUsername("orestis");
		
		entity.setFirstName("Orestis");
		
		entity.setLastName("Zagkoumidis");
		
		entity.setPassword("OresZagk");
		
		entity.setEmail("ores@zagk.com");
		
		entity.setLongitude(15.4);
		
		entity.setLatitude(12.1);
		
		entity.setLocationName("Thessaloniki");
		
		List<String> friendList = new ArrayList<String>();
		friendList.add("nikos");
		entity.setFriendList(friendList);
		
		List<String> blackList = new ArrayList<String>();
		blackList.add("petalidis");
		entity.setBlackList(blackList);
		
		target.save(entity);
		
		assertNotNull("The entity must have been saved.", target.getDatastore().exists(entity));
		
		//Execution
		target.deleteSnapClientEntity(entity);
		
		//Verification
		assertNull(
				"The entity result must have been deleted.",
				target.getDatastore().exists(entity)
		);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSnapClientEntityParamNull() {
		
		// SetUp
		
		// Execution
		target.createSnapClientEntity(null);
		
		// Verification
		
		// TearDown
		
	}
	
	@Test
	public void testGetSnapClientEntityByIdNoEntry() {
		
		//SetUp
		
		//Execution
		SnapClientEntity result = target.getSnapClientEntityById("1");
		
		//Verification
		assertNull(result);
		
		//TearDowwn
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateSnapClientEntityParamNull() {
		
		// SetUp

		// Execution
		target.updateSnapClientEntity(null);
		
		// Verification
		
		// TearDown
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateSnapClientFriendListParamNull() {
		
		// SetUp
		
		// Execution
		target.updateSnapClientFriendList(null);
		
		// Verification
		
		// TearDown
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateSnapClientBlackListParamNull() {
		
		// SetUp
		
		// Execution
		target.updateSnapClientBlackList(null);
		
		// Verification
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientFriendListParamNoEntry() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity(); 
		entity.setUsername("orestis");
		entity.setFriendList(new ArrayList<>());
		entity.setBlackList(new ArrayList<>());
		
		// Execution
		boolean updateDone = target.updateSnapClientFriendList(entity);
		
		// Verification
		assertFalse(updateDone);
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientBlackListParamNoEntry() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity(); 
		entity.setUsername("orestis");
		entity.setFriendList(new ArrayList<>());
		entity.setBlackList(new ArrayList<>());
		
		// Execution
		boolean updateDone = target.updateSnapClientBlackList(entity);
		
		// Verification
		assertFalse(updateDone);
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientEntityParamNoEntry() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity(); 
		entity.setUsername("orestis");
		entity.setFriendList(new ArrayList<>());
		entity.setBlackList(new ArrayList<>());
		
		// Execution
		boolean updateDone = target.updateSnapClientEntity(entity);
		
		// Verification
		assertFalse(updateDone);
		
		// TearDown
		
	}
	
	@Test
	public void testDeleteSnapClientEntityParamNoEntry() {
		
		// SetUp
		SnapClientEntity entity = new SnapClientEntity(); 
		entity.setUsername("orestis");
		entity.setFriendList(new ArrayList<>());
		entity.setBlackList(new ArrayList<>());
		
		// Execution
		boolean updateDone = target.deleteSnapClientEntity(entity);
		
		// Verification
		assertFalse(updateDone);
		
		// TearDown
		
	}
		
}
