package gr.teicm.toulou.SnapChatyX.WebSocketServlet;

/*
*
* @Author AntoniosValais
*
*/

public class SnapClient
{
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	
	private Double longitude;
	
	private Double latitude;
	

	public SnapClient()
	{
		
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude( Double longitude )
	{
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude( Double latitude )
	{
		this.latitude = latitude;
	}
	
	
	
}
