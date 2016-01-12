package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromFriendList;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceFriendListController
{

	Boolean removeFromFriendList( String userRequested, String userToRemoveFromFriend );

	Boolean addToFriendList( String userRequested, String userToAddFriend );

}