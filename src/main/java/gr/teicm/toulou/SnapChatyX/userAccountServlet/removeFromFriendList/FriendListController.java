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
			
			return user.removeFromFriendList( userToRemoveFromFriend );
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
			
			user.removeFromBlackList( userToAddFriend );
			
			if( user.getFriendList().contains( userToAddFriend ) == false )
			{
				return user.addToFriendList( userToAddFriend );
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
