package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.test;

import org.junit.Test;
import gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList.RemoveFromBlackListResult;

import static org.junit.Assert.*;
/*
*
* @Author AntoniosValais
*
*/

public class RemoveFromBlackListResultTests
{
	private RemoveFromBlackListResult actualResult;
	
	@Test
	public void successResultIsCorrect()
	{
		actualResult = new RemoveFromBlackListResult( Boolean.TRUE );
		
		assertEquals( "Success!", actualResult.getResult() );
		
		String expectedMessage = "User has been removed from blackList";
		
		assertEquals( expectedMessage, actualResult.getMessage() );
	}
	
	@Test
	public void failedResultIsCorrect()
	{
		actualResult = new RemoveFromBlackListResult( Boolean.FALSE );
		
		assertEquals( "Failed!", actualResult.getResult() );
		
		String expectedMessage = "User cannot be removed from blackList!!";
		
		assertEquals( expectedMessage, actualResult.getMessage() );
	}
}
