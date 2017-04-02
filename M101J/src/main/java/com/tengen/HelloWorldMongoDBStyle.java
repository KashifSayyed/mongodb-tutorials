/**
 * 
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author Kashif
 *
 */
public class HelloWorldMongoDBStyle {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = client.getDB("census");
		
		DBCollection collection = database.getCollection("states");
		
		DBObject document = collection.findOne();
		
		System.out.println(document);
		

	}

}
