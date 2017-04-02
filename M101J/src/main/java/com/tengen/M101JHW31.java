/**
 * 
 */
package com.tengen;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
public class M101JHW31 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws UnknownHostException {
		 MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

	        DB database = client.getDB("school");
	        final DBCollection collection = database.getCollection("students");
	        
	        DBCursor cursor = collection.find();
	        System.out.println(cursor.hasNext());
	        
	        while(cursor.hasNext()){
	        	DBObject student = cursor.next();
	        	System.out.println(student);
	        	List<DBObject> scores = (List<DBObject>) student.get("scores");
	        	double score_value=-1, highScore =0, lowScore = 0;
	        	DBObject tempScore=null;
	        	List<DBObject> tempScores = new ArrayList<DBObject>();
	        	tempScores.addAll(scores);
	        	for(DBObject score: scores){
	        		if("homework".equals(score.get("type"))){
	        			if(score_value == -1){
	        				score_value = (Double) score.get("score");
	        				tempScore = score;
	        			}else{
	        				if(score_value > (Double) score.get("score")){
	        					highScore = score_value;
	        					lowScore = (Double) score.get("score");
	        					tempScore = score;
	        					//scores.remove(score);
	        				}else{
	        					highScore = (Double) score.get("score");
	        					lowScore = score_value;
	        					//scores.remove(tempScore);
	        				}
	        				tempScores.remove(tempScore);
	        				System.out.println("High Score: "+ highScore+ ", LowScore: "+ lowScore);
	        			}
	        			
	        		}
	        		
	        	}
	        	student.put("scores", tempScores);
	        	collection.findAndModify(new BasicDBObject("_id", student.get("_id")), new BasicDBObject("$set", new BasicDBObject("scores", tempScores)));
	        }

	}

}
