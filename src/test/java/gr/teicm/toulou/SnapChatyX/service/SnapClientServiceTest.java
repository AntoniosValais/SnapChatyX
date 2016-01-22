package gr.teicm.toulou.SnapChatyX.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import gr.teicm.toulou.SnapChatyX.dao.ISnapClientEntityDAO;
import gr.teicm.toulou.SnapChatyX.dao.SnapClientEntityDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.ISnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

/**
 * 
 * 
 * @author Eutixia Bibo
 * @author Petros Ketsentsidis
 */
public class SnapClientServiceTest {

	private ISnapClientService target;
	
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
		
		final SnapClientEntityDAO mockDao = Mockito.mock(SnapClientEntityDAO.class);
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
		
		// SetUp
		
		// Execution
		target.createSnapClient(null);

		// Verification
		
		// TearDown
	}
	
	@Test
	public void testGetSnapClientByUsernameSuccess() {
		
		// SetUp
		final String username = "Efi";
		final String id = "1";
		
		final SnapClientEntity entityFixture = new SnapClientEntity();
		entityFixture.setUsername(username);
		entityFixture.setId(id);
		
		final List<SnapClientEntity> entityList = new ArrayList<>();
		entityList.add(entityFixture);
		
		final ISnapClientEntityDAO mockDao = Mockito.mock(ISnapClientEntityDAO.class);
		target.setDao(mockDao);
		Mockito.when(mockDao.getAllSnapClients()).thenReturn(entityList);
		Mockito.when(mockDao.getSnapClientEntityById(id)).thenReturn(entityFixture);
		
		final SnapClient modelFixture = new SnapClient();
		modelFixture.setUsername(entityFixture.getUsername());
		
		final ISnapClientEntityToModelTransformer mockTransformer =
				Mockito.mock(ISnapClientEntityToModelTransformer.class);
		target.setEntityToModelTransformer(mockTransformer);
		Mockito.when(mockTransformer.transform(entityFixture)).thenReturn(modelFixture);
		
		
		// Execution
		final SnapClient result = target.getSnapClientByUsername(username);
		
		// Verification
		Mockito.verify(mockDao).getAllSnapClients();
		Mockito.verify(mockDao).getSnapClientEntityById(entityFixture.getId());
		Mockito.verify(mockTransformer).transform(entityFixture);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getUsername(), entityFixture.getUsername());
		
		// TearDown
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetSnapClientByUsernameParamNull() throws Exception  {
		// SetUp
		
		// Execution
		target.getSnapClientByUsername(null);
		
		// Verification
		
		// TearDown
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetSnapClientByUsernameParamEmpty() throws Exception {
		
		// SetUp
		
		// Execution
		target.getSnapClientByUsername("");
		
		// Verification
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientSuccess() {
		
		// SetUp
		final String id = "1";
		final String username = "mitsos";
		
		final SnapClientEntity entityFixture = new SnapClientEntity();
		entityFixture.setId(id);
		entityFixture.setUsername(username);
		
		final ISnapClientEntityDAO mockDao = Mockito.mock(ISnapClientEntityDAO.class);
		target.setDao(mockDao);
		
		final List<SnapClientEntity> entityListFixture = new ArrayList<>();
		entityListFixture.add(entityFixture);
		
		Mockito.when(mockDao.getAllSnapClients()).thenReturn(entityListFixture);
		Mockito.when(mockDao.getSnapClientEntityById(id)).thenReturn(entityFixture);
		Mockito.when(mockDao.updateSnapClientEntity(entityFixture)).thenReturn(true);
		
		final SnapClient modelFixture = new SnapClient();
		modelFixture.setUsername(entityFixture.getUsername());
		
		// Execution
		final boolean successUpdate = target.updateSnapClient(modelFixture);
		
		// Verification
		Mockito.verify(mockDao).getAllSnapClients();
		Mockito.verify(mockDao).getSnapClientEntityById(id);
		Mockito.verify(mockDao).updateSnapClientEntity(entityFixture);
		
		Assert.assertTrue(successUpdate);
		
		// TearDown
		
	}
	
	@Test
	public void testUpdateSnapClientParamNoEntry() {
		
		// SetUp
		final String username = "mitsos";
		
		final SnapClient modelFixture = new SnapClient();
		modelFixture.setUsername(username);
		
		final List<SnapClientEntity> entityListFixture = new ArrayList<>();
		
		final ISnapClientEntityDAO mockDao = Mockito.mock(ISnapClientEntityDAO.class);
		target.setDao(mockDao);
		Mockito.when(mockDao.getAllSnapClients()).thenReturn(entityListFixture);
		
		// Execution
		final boolean successUpdate = target.updateSnapClient(modelFixture);
		
		// Verification
		Mockito.verify(mockDao).getAllSnapClients();
		
		Assert.assertFalse(successUpdate);
		
		// TearDown

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateSnapClientParamNull() {
		
		// SetUp
		
		// Execution
		target.updateSnapClient(null);
		
		// Verification
		
		// TearDown
		
	}
	
	@Test
	public void testDeleteSnapClientByUsernameSuccess()
	{
		//SetUp
		final String id = "1";
		final String username = "mitsos";
		
		final SnapClientEntity entityFixture = new SnapClientEntity();
		entityFixture.setId(id);
		entityFixture.setUsername(username);
		
		final List<SnapClientEntity> entityListFixture = new ArrayList<>();
		entityListFixture.add(entityFixture);
		
		final ISnapClientEntityDAO mockDao = Mockito.mock(ISnapClientEntityDAO.class);
		target.setDao(mockDao);
		Mockito.when(mockDao.getAllSnapClients()).thenReturn(entityListFixture);
		Mockito.when(mockDao.getSnapClientEntityById(id)).thenReturn(entityFixture);
		Mockito.when(mockDao.deleteSnapClientEntity(entityFixture)).thenReturn(true);
		
		// Execution
		final boolean successDelete = target.deleteSnapClientByUsername(username);
		
		// Verification
		Mockito.verify(mockDao).getAllSnapClients();
		Mockito.verify(mockDao).getSnapClientEntityById(id);
		Mockito.verify(mockDao).deleteSnapClientEntity(entityFixture);
		
		Assert.assertTrue(successDelete);
		
	}
	
	@Test
	public void testDeleteSnapClientParamNoEntry() {
		
		// SetUp
		final String username = "mitsos";
		
		final ISnapClientEntityDAO mockDao = Mockito.mock(ISnapClientEntityDAO.class);
		target.setDao(mockDao);
		
		final List<SnapClientEntity> entityListFixture = new ArrayList<>();
		
		Mockito.when(mockDao.getAllSnapClients()).thenReturn(entityListFixture);
		
		// Execution
		final boolean successDelete = target.deleteSnapClientByUsername(username);
		
		// Verification
		Mockito.verify(mockDao).getAllSnapClients();
		
		Assert.assertFalse(successDelete);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteSnapClientParamNull()
	{
		// SetUp
				
		// Execution
		target.deleteSnapClientByUsername(null);
		
		// Verification
		
		// TearDown
		
	}
	
	
	
}
