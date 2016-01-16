package gr.teicm.toulou.SnapChatyX.model.transformer;

import java.util.UUID;

import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

public class UserHistoryModelToEntityTransformer implements IUserHistoryModelToEntityTransformer {
	
	@Override
	public UserHistoryEntity transform(final IUserHistory object) {
		
		return new UserHistoryEntity(
				UUID.randomUUID().toString(),
				object.getUsername(),
				object.getMessageList()
		);
		
	}
	
}
