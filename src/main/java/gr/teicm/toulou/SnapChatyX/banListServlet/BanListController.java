package gr.teicm.toulou.SnapChatyX.banListServlet;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;

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

	
}
