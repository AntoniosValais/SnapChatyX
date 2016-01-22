package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.entity.AdminEntity;

/**
 * @author NTINA
 *
 */
public class AdminEntityDAOTest {

	private static AdminEntityDAO target;
	private static List<AdminEntity> entityList;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		target = new AdminEntityDAO();
		
		entityList=target.getDatastore().find(AdminEntity.class).asList();
		
		if (entityList.size() != 0){
			
			for(AdminEntity entity : entityList){
				
				target.getDatastore().delete(entity);
			}
		}
		
		Assert.assertEquals(0, target.getDatastore().find(AdminEntity.class).asList().size());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		if(entityList.size() != 0){
			
			for(AdminEntity entity : entityList) {
				
				target.getDatastore().save(entity);
			}
			
			Assert.assertNotEquals(0, target.getDatastore().find(AdminEntity.class).asList().size());
	
		}else {
			
			Assert.assertEquals(0, target.getDatastore().find(AdminEntity.class).asList().size());
		}
	}

	@Test
	public void testAddAdminEntitySuccess() {
		
		// setup
		
		AdminEntity entity = new AdminEntity();
		entity.setId("1");
		entity.setUsername("Ntina");
		entity.setUserId("11");
		
		//execution
		
		boolean addDone = target.addAdminEntity(entity);
		
		//verification
		
		Assert.assertTrue(addDone);
		
		
		//tear down 
		
		target.getDatastore().delete(entity);
		
		Assert.assertNull(target.getDatastore().exists(entity));
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAdminEntityParamNull() {
		
		//set up
		
		//execution
		target.addAdminEntity(null);
		
		//verification
		
		//tear down
		
	}
	
	@Test
	public void testGetAdminEntityByIdSuccess() {
		
		// setup
		
		String id = "1";
		AdminEntity entity = new AdminEntity();
		entity.setId(id);
		entity.setUserId("11");
		entity.setUsername("Ntina");
		
		target.getDatastore().save(entity);
		
		//execution
		
		AdminEntity result = target.getAdminEntityById(id);
		
		//verification
		
		Assert.assertNotNull(result);
		Assert.assertEquals(entity.getId(), result.getId());
		Assert.assertEquals(entity.getUsername(), result.getUsername());
		Assert.assertEquals(entity.getUserId(), result.getUserId());
		
		//tear down
		
		target.getDatastore().delete(result);
		
		Assert.assertNull(target.getDatastore().exists(result));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAdminByIdParamNull() {
		
		//set up
		
		//execution
		target.getAdminEntityById(null);
		
		//verification
		
		//tear down
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAdminByIdParamEmpty() {
		
		//set up
		
		//execution
		target.getAdminEntityById("");
		
		//verification
		
		//tear down
		
	}
	
	@Test
	public void testGetAllAdminEntitiesSuccess() {
		
		//setup
		
		String id1 = "1";
		AdminEntity entity1 = new AdminEntity();
		entity1.setId(id1);
		entity1.setUserId("11");
		entity1.setUsername("Ntina");
		
		String id2 = "2";
		AdminEntity entity2 = new AdminEntity();
		entity2.setId(id2);
		entity2.setUserId("22");
		entity2.setUsername("Mitsos");
		
		target.getDatastore().save(entity1);
		target.getDatastore().save(entity2);
		
		//execution
		
		List<AdminEntity> resultList = target.getAdminEntities();
		
		//verification
		
		Assert.assertNotNull(resultList);
		Assert.assertEquals(entity1.getId(), resultList.get(0).getId());
		Assert.assertEquals(entity2.getId(), resultList.get(1).getId());
		
		//tear down
		
		for (AdminEntity e : resultList) {
			target.getDatastore().delete(e);
		}
		
		for (AdminEntity e : resultList) {
			Assert.assertNull(target.getDatastore().exists(e));
		}
		
	}
	
	@Test
	public void testDeleteAdminEntityByIdSuccess() {
		
		//setup
		
		String id = "1";
		AdminEntity entity = new AdminEntity();
		entity.setId(id);
		entity.setUserId("11");
		entity.setUsername("Ntina");
		
		target.getDatastore().save(entity);
		
		//execution
		
		boolean deleteDone = target.deleteAdminEntityById(id);
		
		//verification
		
		Assert.assertTrue(deleteDone);
		
		Assert.assertNull(target.getDatastore().exists(entity));
		
		//tear down
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteAdminEntityByIdParamNull() {
		
		//set up
		
		//execution
		target.deleteAdminEntityById(null);
		
		//verification
		
		//tear down
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteAdminEntityByIdParamEmpty() {
		
		//set up
		
		//execution
		target.deleteAdminEntityById("");
		
		//verification
		
		//tear down
		
	}
	
}
