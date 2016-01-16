package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class BlackListController implements InterfaceBlackListController
{
	private InterfaceDataAccessObject dataAccessObject;
	
	public BlackListController()
	{
		 dataAccessObject = DataAccessObject.DAO;	
	}
	
	@Override
	public Boolean removeFromBlackList(String userRequested, String userToRemoveFromBlackList)
	{
		try
		{
			SnapClient user = dataAccessObject.getRegisteredSnapClientWithUsername( userRequested );
			
			SnapClient blackListed = dataAccessObject.getRegisteredSnapClientWithUsername( userToRemoveFromBlackList );
			
			return user.removeFromBlackList( blackListed );
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean addToBlackList(String userRequested, String userToAddOnBlackList)
	{
		try
		{
			SnapClient user = dataAccessObject.getRegisteredSnapClientWithUsername( userRequested );
			
			SnapClient bannedUser = dataAccessObject.getRegisteredSnapClientWithUsername( userToAddOnBlackList );
			
			user.removeFromFriendList( bannedUser );
			
			if( user.getBlackList().contains( bannedUser ) == false )
			{
				return user.addToBlackList( bannedUser );
			}
			else
			{
				return Boolean.TRUE;
			}
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}	
}
