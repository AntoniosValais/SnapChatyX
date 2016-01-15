package gr.teicm.toulou.SnapChatyX.model.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("snapClients")
public class SnapClientEntity {

	@Id
	private String id;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	
	private Double longitude;
	
	private Double latitude;
	
	private String locationName;
	
	@Embedded 
	private List< String > friendList;
	
	@Embedded
	private List< String > blackList;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public List< String > getFriendList() {
		return friendList;
	}

	public void setFriendList(List< String > friendList) {
		this.friendList = friendList;
	}

	public List< String > getBlackList() {
		return blackList;
	}

	public void setBlackList(List< String > blackList) {
		this.blackList = blackList;
	}

}
