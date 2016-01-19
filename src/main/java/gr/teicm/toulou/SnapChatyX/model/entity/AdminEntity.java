package gr.teicm.toulou.SnapChatyX.model.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("admins")
public class AdminEntity {
	@Id
	private String id;
	
	private String userId;
	
	private String username;

	public AdminEntity(String id, String userId, String username) {
		this.id = id;
		this.userId = userId;
		this.username = username;
	}

	public AdminEntity() {
		this(null, null, null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
