package gr.teicm.toulou.SnapChatyX.model.transformer;

import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

public class UserHistoryEntityToModelTransformer implements IUserHistoryEntityToModelTransformer {
	
	@Override
	public IUserHistory transform(final UserHistoryEntity object) {
		
		return (IUserHistory) new UserHistory(
				object.getUsername(),
				object.getMessageList()
		);
		
	}
	
}
