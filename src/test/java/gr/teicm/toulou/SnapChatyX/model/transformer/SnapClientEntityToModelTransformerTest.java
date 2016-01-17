package gr.teicm.toulou.SnapChatyX.model.transformer;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientEntityToModelTransformerTest 
{

	private SnapClientEntityToModelTransformer target;
	
	@Before
	public void setUp()
	{
		target = new SnapClientEntityToModelTransformer();
	}
	
	@Test
	public void testTransformSuccess() {
		//setUp
		SnapClientEntity entity = new SnapClientEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setUsername("kitsos");
		entity.setPassword("12345");
		entity.setFirstName("kuriakos");
		entity.setLastName("tzabelas");
		entity.setLatitude(1234.213);
		entity.setLongitude(453.543);
		entity.setEmail("kitsostzab@gmail.com");
		entity.setLocationName("souli");
		//TODO:test friends and blacks
		
		//execution
		SnapClient result = target.transform(entity);
		
		//verification
		Assert.assertNotNull(result);
		Assert.assertEquals(entity.getUsername(), result.getUsername());
		Assert.assertEquals(entity.getPassword(), result.getPassword());
		Assert.assertEquals(entity.getFirstName(), result.getFirstName());
		Assert.assertEquals(entity.getLastName(), result.getLastName());
		Assert.assertEquals(entity.getLatitude(), result.getLatitude());
		Assert.assertEquals(entity.getLongitude(), result.getLongitude());
		Assert.assertEquals(entity.getEmail(), result.getEmail());
		Assert.assertEquals(entity.getLocationName(), result.getLocationName());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTransformParamNull()
	{
		target.transform(null);
	}

}
