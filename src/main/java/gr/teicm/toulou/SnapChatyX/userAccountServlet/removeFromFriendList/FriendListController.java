package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class FriendListController implements InterfaceFriendListController
{
	private InterfaceDataAccessObject dataAccessObject;
	
	public FriendListController()
	{
		 dataAccessObject = DataAccessObject.DAO;	
	}
	
	@Override
	public Boolean removeFromFriendList(String userRequested, String userToRemoveFromFriend)
	{
		try
		{
			SnapClient user = dataAccessObject.getRegisteredSnapClientWithUsername( userRequested );
			
			SnapClient friend = dataAccessObject.getRegisteredSnapClientWithUsername( userToRemoveFromFriend );
			
			return user.removeFromFriendList( friend );
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean addToFriendList(String userRequested, String userToAddFriend)
	{
		try
		{
			SnapClient user = dataAccessObject.getRegisteredSnapClientWithUsername( userRequested );
			
			SnapClient friend = dataAccessObject.getRegisteredSnapClientWithUsername( userToAddFriend );
			
			user.removeFromBlackList( friend );
			
			return user.addToFriendList( friend );
		}
		catch( Exception e )
		{
			return Boolean.FALSE;
		}
	}
}
