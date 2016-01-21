package gr.teicm.toulou.SnapChatyX.adminListServlet;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

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
	
	@Override
	public Boolean addAdmin( String username )
	{
		if(username==null || username.isEmpty())
		{
			return Boolean.FALSE;
		}
		
		SnapClient admin = dataAccessObject.getRegisteredSnapClientWithUsername(username);
		
		if( admin == null)
		{
			return Boolean.FALSE;
		}
			
		try
		{
			adminList = dataAccessObject.getAdminList();
			
			if(adminList.contains(username))
			{
				return Boolean.TRUE;
			}
			else
			{
				return dataAccessObject.addUserToAdminList(username);
			}
		}
		catch(Exception e)
		{
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean removeAdmin( String username )
	{
		if(username==null || username.isEmpty())
		{
			return Boolean.FALSE;
		}
			
		try
		{
			adminList = dataAccessObject.getAdminList();
			
			if(adminList.contains(username))
			{
				return dataAccessObject.removeUserFromAdminList(username);
			}
			else
			{
				return Boolean.FALSE;
			}
		}
		catch(Exception e){
			return Boolean.FALSE;
		}
	}
	
}
