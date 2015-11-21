package gr.teicm.toulou.SnapChatyX.WebSocketServlet.test;

import org.junit.Before;
import org.junit.Ignore;
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
	
	@Ignore
	@Test
	public void connectionEstablishTest()
	{
		webSocketClientMock = new WebSocketClientMock();
		
		String sessionId = webSocketClientMock.createConnection();
		
		Boolean sessionClosed = webSocketClientMock.closeConnection();
		
		assertNotNull( sessionId );
		assertTrue( sessionClosed );
	}
	
	@Ignore
	@Test
	public void servletConnectionReceiveMessageTest()
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
