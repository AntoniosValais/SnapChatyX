package gr.teicm.toulou.SnapChatyX.userProfileServlet;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/

public class UserProfileController implements InterfaceUserProfileController
{
	private InterfaceDataAccessObject dataAccessObject;
	
	private InterfaceUserProfile userProfile;
	
	public UserProfileController()
	{
		 dataAccessObject = DataAccessObject.DAO;
	}
	
	@Override
	public InterfaceUserProfile getUserProfile( String username )
	{
		SnapClient snapClient = dataAccessObject.getRegisteredSnapClientWithUsername( username );
		
		if( snapClient != null )
		{
			userProfile = new UserProfile( snapClient );
		}
		else
		{
			userProfile = null;
		}
		
		return userProfile;
	}
}
