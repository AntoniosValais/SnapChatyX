package gr.teicm.toulou.SnapChatyX.WebSocketServlet.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
*
* @Author AntoniosValais
*
*/

public class WebSocketServletTest
{
	private WebSocketClientMock webSocketClientMock;
	
	@Before
	public void beforeTest()
	{
		
	}
	
	@Test
	public void connectionEstablishTest()
	{
		webSocketClientMock = new WebSocketClientMock();
		
		String sessionId = webSocketClientMock.createConnection();
		
		Boolean sessionClosed = webSocketClientMock.closeConnection();
		
		assertNotNull( sessionId );
		assertTrue( sessionClosed );
	}
	
	@Test
	public void servletReceiveMessageTest()
	{
		webSocketClientMock = new WebSocketClientMock();
		
		String sessionId = webSocketClientMock.createConnection();
		
		Boolean sendMessageDone = webSocketClientMock.sendMessage( "dada!" );
		
		Boolean sessionClosed = webSocketClientMock.closeConnection();
		
		assertNotNull( sessionId );
		assertTrue( sessionClosed );
		assertTrue( sendMessageDone );	
	}
	
}
