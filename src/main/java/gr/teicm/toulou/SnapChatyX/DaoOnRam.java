package gr.teicm.toulou.SnapChatyX;

import java.awt.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import com.mongodb.DBObject;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.WebSocketServlet.SnapClient;

public class DaoOnRam implements IDAO,InterfaceDataAccessObject {

	public static Set<User> registeredUsers = new HashSet<User>();
	public static Set<OnlineUser> onlineUsers = new HashSet<OnlineUser>();
	
	//websocket stuff
	private Set< Session > sessions = new HashSet< Session >();
	private static Set< SnapClient > onlineSnapClients = new HashSet< SnapClient >();
	private HashMap< SnapClient, Session > snapClientSessionMap = new HashMap< SnapClient, Session >();
	
	//username,longitude,latitude
	@Override
	public String saveLocation(DBObject location) {
		
		OnlineUser onlineUser = new OnlineUser();
		try{
			onlineUser.username  = location.get("username").toString();
			onlineUser.latitude  = (Double) location.get("latitude");
			onlineUser.longitude = (Double) location.get("longitude");
			
			DaoOnRam.onlineUsers.add(onlineUser);
			
			return "{\"result\":\"success\"}";
		}catch(Exception e){
			System.out.println(e);
			return "{\"result\":\"internal error\"}";
		}
	
	}

	//onoma,epitheto,username,password,email
	@Override
	public String signUpUser(DBObject user) {
		
		User newUser = new User();
		try{
			newUser.username = user.get("username").toString();
			newUser.password = user.get("pass").toString();
			newUser.onoma 	 = user.get("onoma").toString();
			newUser.epitheto = user.get("epitheto").toString();
			newUser.email 	 = user.get("email").toString();
			
			DaoOnRam.registeredUsers.add(newUser);
			
			System.out.println("signup user "+ newUser.username );
			return "{\"result\":\"success\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"result\":\"internal error\"}";
		}
		
		
	}
	//username,password
	@Override
	public String signInUser(DBObject user) {

		
		try{
			String username   = user.get("username").toString();
			String password   = user.get("pass").toString();
			System.out.println(username+ " " + password);
			
			for(User registeredUser : DaoOnRam.registeredUsers) {
				System.out.println(registeredUser.username+" "+registeredUser.password);
				if(registeredUser.username.equals(username) && registeredUser.password.equals(password) )
				{				
					System.out.println(username + "exists corrent Log in");
					SnapClient client = new SnapClient();
					client.setUsername(username);
					//prosthesi xristi pou ekane login gia xrisi apo websocket
					this.addOnlineSnapClient(client);
					DaoOnRam.onlineSnapClients.add(client);
					return "{\"result\":\"User exists\"}";
				}
			}
			return "{\"result\":\"User Does Not Exist\"}";
		}catch(Exception e){
			System.out.println(e);
			return "{\"result\":\"internal error\"}";
		}
		
	}

	@Override
	public Boolean addSession(Session session) {
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
	public Boolean removeSession(Session session) {
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
	public Session getSessionWithID(String sessionID) {
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
	public Boolean addOnlineSnapClient(SnapClient snapClient) {
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
	public Boolean removeOnlineSnapClient(SnapClient snapClient) {
		try
		{
			onlineSnapClients.remove( snapClient );
			//remove user from onlineUsers
			for(OnlineUser onlineUser : DaoOnRam.onlineUsers)
			{
				if(onlineUser.username == snapClient.getUsername())
				{
					DaoOnRam.onlineUsers.remove(onlineUser);
					return Boolean.TRUE;
				}
			}
			
			return Boolean.FALSE;
		}
		catch( NullPointerException e )
		{
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean relateUsernameWithSession(String username, Session session) {
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
	public Set<Session> getAllSessions() {
		return this.sessions;
	}

}
