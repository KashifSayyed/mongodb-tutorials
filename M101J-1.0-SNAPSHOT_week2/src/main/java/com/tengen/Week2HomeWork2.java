/**
 * 
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author Kashif
 *
 */
public class Week2HomeWork2 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		 MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

	        DB database = client.getDB("students");
	        final DBCollection collection = database.getCollection("grades");
	        
	        DBCursor cursor = collection.find(new BasicDBObject("type", "homework"))
	        							.sort(new BasicDBObject("student_id",1).append("score", 1));
	        
	        int studentId = -1;
	        while(cursor.hasNext()){
	        	DBObject student = cursor.next();
	        	System.out.println(student);
	        	int tempID =  (Integer) student.get("student_id");
	        	System.out.println("studentId = "+studentId);
	        	if(studentId != tempID){
	        		studentId = tempID;
	        		collection.remove(student);
	        	}
	        }
	}

}
