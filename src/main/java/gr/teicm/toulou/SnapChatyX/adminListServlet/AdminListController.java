package gr.teicm.toulou.SnapChatyX.adminListServlet;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;

public class AdminListController implements InterfaceAdminListController{

	private List<String> adminList;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	public AdminListController(){
		
		adminList = new ArrayList<String>();
		
		dataAccessObject = DataAccessObject.DAO;
	}
	
	@Override
	public List<String> getAdminList(){
		
/*		adminList.add("Ketsis");
		adminList.add("iKetsis2");
		adminList.add("Ketsis");
		adminList.add("iKetsis2");
		adminList.add("Ketsis");
		adminList.add("iKetsis2");
		adminList.add("Ketsis");
		adminList.add("iKetsis2");*/
		
		adminList = dataAccessObject.getAdminList();
		return adminList;
	}
	
}
