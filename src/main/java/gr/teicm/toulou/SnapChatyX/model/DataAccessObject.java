package gr.teicm.toulou.SnapChatyX.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.websocket.Session;

import com.mongodb.DBObject;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;

public enum DataAccessObject implements IDAO,InterfaceDataAccessObject,IUserHistoryDAO
{

	DAO;

	public Set< SnapClient > registeredSnapClients;

	private Set< Session > sessions;

	private Set< SnapClient > onlineSnapClients;

	private HashMap< SnapClient, Session > snapClientSessionMap;

	private HashMap< SnapClient, List< SnapClientTextMessage > > snapClientTextMessageMap;

	private Timer messageDeleterTimer = new Timer ();
	
	private final List<IUserHistory> userHistoryList;
	
	private List<String> banList;
	
	private List<String> administrators;
	
	private DataAccessObject()
	{
		this.initializeDeleterTimer();
		
		registeredSnapClients = new HashSet< SnapClient >();
		
		sessions = new HashSet< Session >();
		
		onlineSnapClients = new HashSet< SnapClient >();
		
		snapClientSessionMap = new HashMap< SnapClient, Session >();
		
		snapClientTextMessageMap = new HashMap< SnapClient, List< SnapClientTextMessage > >();
		
		userHistoryList = new ArrayList<>();
		
		banList = new ArrayList<>();
		
		administrators = new ArrayList<>();
		
		administrators.add("Eftixia");
	}
	
	@Override
	public String saveLocation( DBObject location )
	{
		try
		{
			String usernameOnLocationDATA = location.get( "username" ).toString();

			SnapClient snapClientToUpdate = this.getOnlineSnapClientWithUsername( usernameOnLocationDATA );

			//snapClientToUpdate.setLatitude( (Double) location.get( "latitude" ) );
			//snapClientToUpdate.setLongitude( (Double) location.get( "longitude" ) );
			snapClientToUpdate.setLocationName( (String) location.get("location") );

			return "{\"result\":\"success\"}";
		}
		catch( Exception e )
		{
			System.out.println( e );
			return "{\"result\":\"internal error\"}";
		}

	}


	// onoma,epitheto,username,password,email
	@Override
	public String signUpUser( DBObject user )
	{

		SnapClient newUser = new SnapClient();
		try
		{

			if(user.get("username") == null)
			{
				newUser.setUsername( "" );
			}else{
				newUser.setUsername( user.get( "username" ).toString() );
			}
			if(user.get("password") == null)
			{
				newUser.setPassword( "" );
			}else{
				newUser.setPassword( user.get( "password" ).toString() );
			}
			if(user.get("firstName")==null){
				newUser.setFirstName( "" );
			}else{
				newUser.setFirstName( user.get( "firstName" ).toString() );
			}

			if(user.get("lastName") == null){
				newUser.setLastName( "" );
			}else{
				newUser.setLastName( user.get( "lastName" ).toString() );
			}
				
			if(user.get( "email" ) == null)
			{
				newUser.setEmail( "" );
			}else{
				newUser.setEmail( user.get( "email" ).toString() );
			}
			this.registeredSnapClients.add( newUser );

			List< SnapClientTextMessage > newUserMessageList = new ArrayList< SnapClientTextMessage >();

			snapClientTextMessageMap.put( newUser, newUserMessageList );

			System.out.println( "signup user " + newUser.getUsername() );

			return "{\"result\":\"success\"}";
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return "{\"result\":\"internal error\"}";
		}

	}
	
	public Boolean registerSnapClient( SnapClient snapClient )
	{
		return registeredSnapClients.add( snapClient );
	}
	
	public Boolean signInSnapClient( SnapClient snapClient )
	{
		return onlineSnapClients.add( snapClient );
	}

	// username,password
	@Override
	public String signInUser( DBObject user )
	{

		try
		{
			String username = user.get( "username" ).toString();

			String password = user.get( "pass" ).toString();

			System.out.println( username + " " + password );

			for( SnapClient registeredUser : this.registeredSnapClients )
			{
				System.out.println( registeredUser.getUsername() + " " + registeredUser.getPassword() );

				if( registeredUser.getUsername().equals( username ) && registeredUser.getPassword().equals( password ) )
				{
					System.out.println( username + "exists corrent Log in" );

					SnapClient loggedInSnapClient = this.getRegisteredSnapClientWithUsername( username );
					
					this.onlineSnapClients.add( loggedInSnapClient );

					return "{\"result\":\"User exists\"}";
				}
			}
			return "{\"result\":\"User Does Not Exist\"}";
		}
		catch( Exception e )
		{
			System.out.println( e );

			return "{\"result\":\"internal error\"}";
		}

	}

	@Override
	public Boolean addSession( Session session )
	{
		try
		{
			this.sessions.add( session );

			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Boolean removeSession( Session session )
	{
		try
		{
			this.sessions.remove( session );

			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Session getSessionWithID( String sessionID )
	{
		if( sessions == null || sessions.isEmpty() )
		{
			return null;
		}

		for( Session session : sessions )
		{
			if( session.getId().equals( sessionID ) )
			{
				return session;
			}
		}

		return null;

	}

	@Override
	public Boolean addOnlineSnapClient( SnapClient snapClient )
	{
		try
		{
			onlineSnapClients.add( snapClient );

			return Boolean.TRUE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}

	}

	@Override
	public Boolean removeOnlineSnapClient( SnapClient snapClient )
	{
		try
		{
			onlineSnapClients.remove( snapClient );

			return Boolean.FALSE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean relateUsernameWithSession( String username, Session session )
	{
		for( SnapClient snapClient : onlineSnapClients )
		{
			if( snapClient.getUsername().equals( username ) )
			{
				try
				{
					snapClientSessionMap.put( snapClient, session );

					return Boolean.TRUE;
				}
				catch( Exception e )
				{

					return Boolean.FALSE;
				}

			}
		}

		return Boolean.FALSE;
	}

	@Override
	public Set< Session > getAllSessions()
	{
		return this.sessions;
	}

	@Override
	public Boolean saveMessage( SnapClientTextMessage userTextMessage )
	{
		SnapClient snapClient = this.getOnlineSnapClientWithUsername( userTextMessage.getSenderUsername() );

		if( snapClient != null )
		{
			userTextMessage.setMessageId( UUID.randomUUID().toString() );
			
			List< SnapClientTextMessage > snapClientMessageList = snapClientTextMessageMap.get( snapClient );
			
			snapClientMessageList.add( userTextMessage );
			
			if (this.usernameExistsInUserHistoryList(userTextMessage.getSenderUsername())) {
				
				this.getUserHistoryByUsername(userTextMessage.getSenderUsername()).getMessageList().add(userTextMessage);
				
			} else {
				
				UserHistory userHistory = new UserHistory(userTextMessage.getSenderUsername(), snapClientMessageList);
				
				this.addUserHistoryByUsername(userHistory);
				
			}

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public SnapClient getOnlineSnapClientWithUsername( String username )
	{
		try
		{
			for( SnapClient snapClient : onlineSnapClients )
			{
				if( snapClient.getUsername().equals( username ) )
				{
					return snapClient;
				}
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return null;
	}
	
	@Override
	public SnapClient getRegisteredSnapClientWithUsername( String username )
	{
		try
		{
			for( SnapClient snapClient : registeredSnapClients )
			{
				if( snapClient.getUsername().equals( username ) )
				{
					return snapClient;
				}
			}
		}
		catch( Exception e )
		{
			return null;
		}

		return null;
	}

	@Override
	public Set< SnapClient > getOnlineSnapClients()
	{
		return onlineSnapClients;
	}
	
	@Override
	public HashMap< SnapClient, Session > getSnapClientSessionMap()
	{
		return snapClientSessionMap;
	}

	public void initializeDeleterTimer(){
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		        HashMap< SnapClient, List< SnapClientTextMessage > > snapClientTextMessageMapToBeErased = new HashMap< SnapClient, List< SnapClientTextMessage > >();
		        
		        for(SnapClient user : onlineSnapClients )
		        {
		        	List<SnapClientTextMessage> messages = snapClientTextMessageMap.get(user);
		        	List<SnapClientTextMessage> messagesToBeErased=new ArrayList<>();
		        	for(SnapClientTextMessage msg : messages)
		        	{
		        		if(msg.getTimeToLive() == 0)
		        		{
		        			messagesToBeErased.add(msg);
		        			//erazeSnapTextMessageRemove
		        			String message = "{\"messageType\":\"SnapTextMessageRemove\",\"data\":{\"messageId\":\""+msg.getMessageId()+"\"}}";
		        			System.out.println(message);
		        			for( Session session : sessions)
		        			{
		        				session.getAsyncRemote().sendText(message);
		        				
		        			}
		        			
		        		}else{
		        			msg.setTimeToLive(msg.getTimeToLive()-1);
		        		}
		        	}
		        	snapClientTextMessageMapToBeErased.put(user, messagesToBeErased);
		        	
		        }
		        //apostoli mnmatos pros diagrafi
		        updateCollections(snapClientTextMessageMapToBeErased);
		    }
		};
		
		// schedule the task to run starting now and then every hour...
		messageDeleterTimer.schedule (hourlyTask, 0l, 1000 * 60);
	}
	
	public void updateCollections(HashMap< SnapClient, List< SnapClientTextMessage > > snapClientTextMessageMapToBeErased)
	{
		 for(SnapClient user : snapClientTextMessageMapToBeErased.keySet()  )
	        {
			 	//messages to be erased
	        	List<SnapClientTextMessage> messagesToBeErased = snapClientTextMessageMapToBeErased.get(user);
	        	//all user messages
	        	List<SnapClientTextMessage> userMessages = snapClientTextMessageMap.get(user);
	        	for(SnapClientTextMessage msg : messagesToBeErased)
	        	{
	        		userMessages.remove(msg);
	        	}
	        }
	}
	
	@Override
	public Boolean usernameExistsInUserHistoryList(String username) {
		
		if (username == null || username == "") {
			
			return false;
			
		}
		
		for (IUserHistory uh : userHistoryList) {
			
			if (uh.getUsername().equals(username)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public Boolean addUserHistoryByUsername(IUserHistory userHistory) {
		
		if (userHistory == null) {
			
			return false;
			
		}
		
		if (! this.usernameExistsInUserHistoryList(userHistory.getUsername())) {
			
			userHistoryList.add(userHistory);
			
			return true;
			
		}
		
		return false;
		
	}
	
	@Override
	public IUserHistory getUserHistoryByUsername(String username) {
		
		if (username == null || username == "") {
			
			return null;
			
		}
		
		for (IUserHistory uh : userHistoryList) {
			
			if (uh.getUsername().equals(username)) {
				
				return uh;
				
			}
			
		}
		
		return null;
		
	}
	
	@Override
	public Boolean updateUserHistoryByUsername(IUserHistory userHistory) {
		
		if (userHistory == null) {
			
			return false;
			
		}
		
		for (IUserHistory uh : userHistoryList) {
			
			if (uh.getUsername().equals(userHistory.getUsername())) {
				
				int index = userHistoryList.indexOf(uh);
				
				userHistoryList.set(index, userHistory);
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public Boolean deleteUserHistoryByUsername(String username) {
		
		if (username == null || username == "") {
			
			return false;
			
		}
		
		for (IUserHistory uh : userHistoryList) {
			
			if (uh.getUsername().equals(username)) {
				
				int index = userHistoryList.indexOf(uh);
				
				userHistoryList.remove(index);
				
				return true;
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public List<IUserHistory> getUserHistoryList() {
		
		return userHistoryList;
		
	}	
	
	@Override
	public List<String> getBanList(){
		return banList;
	}
	
	@Override
	public List<String> getAdminList(){
		return administrators;
	}
}
