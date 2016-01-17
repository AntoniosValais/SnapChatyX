package gr.teicm.toulou.SnapChatyX.dao;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.entity.BannedUserEntity;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * This is the basic DAO class that all DAOs of the application should extend in order to create collections
 * for the MongoDB database.
 * 
 * @param <T> the entity type
 * @param <K> the key type
 * 
 * @since Jan 5, 2016
 * 
 * @author Stamatios Tsalikis
 */
class SnapBasicDAO<T, K> extends BasicDAO<T, K> {
	
	/**
	 * This constant represents the <code>MongoClient</code> instance that will be used for this application.
	 */
	private static final MongoClient MONGO_CLIENT = getMongoClientInstance();
	
	/**
	 * This constant represents the <code>Morphia</code> instance that will organize the type of objects that
	 * go in and come out of the MongoDB database.
	 */
	private static final Morphia MORPHIA = getMorphiaInstance();
	
	/**
	 * This constant represents the MongoDB database the this application will use.
	 */
	private static final String DB_NAME = "snapDB";
	
	/**
	 * Call this constructor from any extending class only. The parameter will determine what type of collection
	 * will be created in the database and it will be added as a field in that collection as well.
	 * 
	 * @param entityClass the entity class
	 */
	protected SnapBasicDAO(final Class<T> entityClass) {
		
		super(entityClass, MONGO_CLIENT, MORPHIA, DB_NAME);
		
	}
	
	/**
	 * Returns the built <code>MongoClient</code> instance.
	 * 
	 * @return the MongoClient instance
	 */
	private static MongoClient getMongoClientInstance() {
		
		final MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		return mongoClient;
		
	}
	
	/**
	 * Returns the built <code>Morphia</code> instance.
	 * 
	 * @return the Morphia instance
	 */
	private static Morphia getMorphiaInstance() {
		
		final Morphia morphia = new Morphia();
		
		// map participating classes, such as entities, below:
		morphia.map(UserHistoryEntity.class);
		morphia.map(SnapClientTextMessage.class);
		morphia.map(SnapClientEntity.class);
		morphia.map(BannedUserEntity.class);
		
		return morphia;
		
	}
	
}
