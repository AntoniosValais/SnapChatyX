package gr.teicm.toulou.SnapChatyX.banListServlet;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author iKetsi
*
*/

public class BanListController implements InterfaceBanListController {
	
	private List<String> banList;
	
	private InterfaceDataAccessObject dataAccessObject;
	
	public BanListController(){
		banList = new ArrayList<String>();
		dataAccessObject = DataAccessObject.DAO;
	}
	
	@Override
	public List<String> getBanList(){
		
/*		banList.add("Ketsis");
		banList.add("iKetsis2");
		banList.add("Ketsis");
		banList.add("iKetsis2");
		banList.add("Ketsis");
		banList.add("iKetsis2");
		banList.add("Ketsis");
		banList.add("iKetsis2");
		banList.add("Ketsis");
		banList.add("iKetsis2");*/
		
		banList = dataAccessObject.getBanList();
		return banList;
	}

	@Override
	public Boolean banUser( String username )
	{
		if(username==null || username.isEmpty())
		{
			return Boolean.FALSE;
		}
		
		SnapClient bannedUser = dataAccessObject.getRegisteredSnapClientWithUsername(username);
		
		if( bannedUser == null)
		{
			return Boolean.FALSE;
		}
			
		try
		{
			banList = dataAccessObject.getBanList();
			
			if(banList.contains(username))
			{
				return Boolean.TRUE;
			}
			else
			{
				return dataAccessObject.addUserToBanList(username);
			}
		}
		catch(Exception e)
		{
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean unbanUser( String username )
	{
		if(username==null || username.isEmpty())
		{
			return Boolean.FALSE;
		}
			
		try
		{
			banList = dataAccessObject.getBanList();
			
			if(banList.contains(username))
			{
				return dataAccessObject.removeUserFromBanList(username);
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
