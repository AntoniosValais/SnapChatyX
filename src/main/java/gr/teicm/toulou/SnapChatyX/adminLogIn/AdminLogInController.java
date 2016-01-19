package gr.teicm.toulou.SnapChatyX.adminLogIn;

import java.util.List;
import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/**
 * 
 * 
 * @Author Eftiqia Bibo
 * 
 * 
 * 
 */

public class AdminLogInController implements InterfaceAdminLogInController {
	
	private InterfaceDataAccessObject dataAccessObject;
	private List<String> adminList;
	
	public AdminLogInController()  {
		
		dataAccessObject = DataAccessObject.DAO;
		
		
	}
	
	@Override
	public Boolean verify(String adminName, String password) {
		
		adminList = dataAccessObject.getAdminList();
		
		try {
			
			if(adminList.contains(adminName)) {
				
				SnapClient admin = dataAccessObject.getRegisteredSnapClientWithUsername(adminName);
				
				return admin.getPassword().equals(password);
			}
		}
		catch(Exception e){
			
			return Boolean.FALSE;
		}
		
		return Boolean.FALSE;	
	}
}
