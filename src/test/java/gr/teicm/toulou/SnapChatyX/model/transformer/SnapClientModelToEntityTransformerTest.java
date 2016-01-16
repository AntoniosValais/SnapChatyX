package gr.teicm.toulou.SnapChatyX.model.transformer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientModelToEntityTransformerTest {

	private SnapClientModelToEntityTransformer target;
	
	@Before
	public void setUp() {
		target = new SnapClientModelToEntityTransformer();
	}
	
	@Test
	public void testTransformSuccess() 
	{
		
		//setUp
		SnapClient model = new SnapClient();
		model.setUsername("kitsos");
		model.setPassword("12345");
		model.setFirstName("kuriakos");
		model.setLastName("tzabelas");
		model.setLatitude(1234.213);
		model.setLongitude(453.543);
		model.setEmail("kitsostzab@gmail.com");
		model.setLocationName("souli");
		//TODO:test friends and blacks
		
		//execution
		SnapClientEntity result = target.transform(model);
		
		//verification
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(model.getUsername(), result.getUsername());
		Assert.assertEquals(model.getPassword(), result.getPassword());
		Assert.assertEquals(model.getFirstName(), result.getFirstName());
		Assert.assertEquals(model.getLastName(), result.getLastName());
		Assert.assertEquals(model.getLatitude(), result.getLatitude());
		Assert.assertEquals(model.getLongitude(), result.getLongitude());
		Assert.assertEquals(model.getEmail(), result.getEmail());
		Assert.assertEquals(model.getLocationName(), result.getLocationName());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTransformParamNull()
	{
		target.transform(null);
	}

}
