package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.entity.AdminEntity;

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
	
	@Test
	public void testGetAllAdminEntitiesSuccess(){
		
		//TODO:COMPLETE THIS TEST
		
		//setup
		
		//execution
		
		//verification
		
		//tear down
	}
		
	@Test
	public void testDeleteAdminEntityByIdSuccess(){
		
		//TODO:COMPLETE THIS TEST
		
		//setup
		
		//execution
		
		//verification
		
		//tear down
		
	}
}
