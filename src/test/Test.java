package test;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Test {
	public static void main(String args[]){
		MongoClient mongoClient = new MongoClient();
		
		MongoDatabase database = mongoClient.getDatabase("test");
		
		MongoCollection<Document> collection = database.getCollection("testCollection");
		
		MongoCursor<Document> cursor = collection.find().iterator();
		
		try{
			while(cursor.hasNext()){
				Document doc = cursor.next();
				System.out.println(doc.toJson());
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			cursor.close();
		}
		
		mongoClient.close();
	}
}
