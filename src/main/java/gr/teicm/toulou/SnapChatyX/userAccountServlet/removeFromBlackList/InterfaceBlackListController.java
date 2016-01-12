package gr.teicm.toulou.SnapChatyX.userAccountServlet.removeFromBlackList;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceBlackListController
{

	Boolean removeFromBlackList( String userRequested, String userToRemoveFromBlackList );

	Boolean addToBlackList( String userRequested, String userToAddOnBlackList );

}