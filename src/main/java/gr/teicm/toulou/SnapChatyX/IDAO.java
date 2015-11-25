package gr.teicm.toulou.SnapChatyX;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public interface IDAO {

	
	public String saveLocation(DBObject location);
	public String signUpUser(DBObject user);
	public String signInUser(DBObject user);
}
