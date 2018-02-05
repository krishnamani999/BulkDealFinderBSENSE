package modules.common;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	
	public static MongoClient getConnection(){
		
		//MongoCredential credential = MongoCredential.createCredential("****", "heroku_vqx2q3wd", "****".toCharArray());
		MongoCredential credential = MongoCredential.createScramSha1Credential("user1", "heroku_vqx2q3wd", "user1".toCharArray());
		MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
		
		//MongoClient mongoClient = new MongoClient("mongodb://heroku_vqx2q3wd:q4kt994dcd4hg610a3hf9qf8ne@ds125048.mlab.com:25048/heroku_vqx2q3wd");
		MongoClient mongoClient = new MongoClient(new ServerAddress("ds125048.mlab.com", 25048), Arrays.asList(credential), options);
		
		return mongoClient;
	}
	
	public static MongoDatabase getDatabase(MongoClient mongoClient){
		
		MongoDatabase database = mongoClient.getDatabase("heroku_vqx2q3wd");
		
		return database;
	}
	
	public static void closeConnection(MongoClient mongoClient){
		if(mongoClient != null){
			mongoClient.close();
		}
	}
}
