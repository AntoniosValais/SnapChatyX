package gr.teicm.toulou.SnapChatyX.model.entity;

import java.util.Date;

public class BannedUserEntity {
	
	private String id;
	
	private String username;
	
	private String userId;
	
	private Date dateBanned;
	
	private long timeForBan;

	public BannedUserEntity(String id, String username, String userId, Date dateBanned, long timeForBan)
	{
		this.id = id;
		this.username = username;
		this.userId = userId;
		this.dateBanned = dateBanned;
		this.timeForBan = timeForBan;
	}
	
	public BannedUserEntity()
	{
		this(null,null,null,new Date(),0);
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDateBanned() {
		return dateBanned;
	}

	public void setDateBanned(Date dateBanned) {
		this.dateBanned = dateBanned;
	}

	public long getTimeForBan() {
		return timeForBan;
	}

	public void setTimeForBan(long timeForBan) {
		this.timeForBan = timeForBan;
	}
	
	

}
