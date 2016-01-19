package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.entity.BannedUserEntity;

/**
 * 
 * 
 * 
 * @author Stefos
 */
public class BannedUserEntityDAOTest {

	private IBannedUserEntityDAO target;
	
	@Before
	public void setUp()
	{
		target = new BannedUserEntityDAO();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBannedUserEntityParamNull()
	{
		//setUp
		
		//execution
		target.addBannedUserEntity(null);
		
		//verification
		
		//tearDown
	}
	
	@Test
	public void testGetAllBannedUserEntitiesNoEntry()
	{
		//setUp
		List<BannedUserEntity> entityList = target.datastore().find(BannedUserEntity.class).asList();
		for(BannedUserEntity entity : entityList)
		{
			target.datastore().delete(entity);
		}
		
		//execution
		List<BannedUserEntity> resultList = target.getAllBannedUserEntities();
		
		//verification
		Assert.assertNotNull(resultList);
		Assert.assertEquals(0, resultList.size());
		
		//tearDown
		for(BannedUserEntity entity : entityList)
		{
			target.datastore().save(entity);
		}
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveBannedUserEntityParamNull()
	{
		//setUp
		
		//execution
		target.removeBannedUserEntity(null);
		
		//verification
		
		//tearDown
	}

}
