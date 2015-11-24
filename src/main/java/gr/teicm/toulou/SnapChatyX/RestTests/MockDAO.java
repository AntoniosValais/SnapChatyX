package gr.teicm.toulou.SnapChatyX.RestTests;

import com.mongodb.DBObject;

import gr.teicm.toulou.SnapChatyX.IDAO;

public class MockDAO implements IDAO {

	public String locationResponse;
	public String signUpResponce;
	public String signInResponce;
	
	public void setLocationResponse(String response){
		this.locationResponse = response ;
	}
	public void setSingUpResponse(String response){
		this.signUpResponce = response ;
	}
	public void setSingInResponse(String response){
		this.signInResponce = response ;
	}
	
	@Override
	public String saveLocation(DBObject location) {
		// TODO Auto-generated method stub
		return this.locationResponse;
	}

	@Override
	public String signUpUser(DBObject user) {
		// TODO Auto-generated method stub
		return this.signUpResponce;
	}

	@Override
	public String signInUser(DBObject user) {
		// TODO Auto-generated method stub
		return this.signInResponce;
	}

}
