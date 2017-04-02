/**
 * 
 */
package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Kashif
 *
 */
public class HelloWorldMongoSparkFreeMarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spark.get(new Route("/"){

			public Object handle(final Request arg0, final Response arg1) {
				Configuration config = new Configuration();
				
				config.setClassForTemplateLoading(HelloWorldMongoSparkFreeMarkerStyle.class, "/");
				StringWriter writer = new StringWriter();
				MongoClient client = null;
				try {
					client = new MongoClient(new ServerAddress("localhost", 27017));
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				
				DB database = client.getDB("census");
				
				DBCollection collection = database.getCollection("states");
				try {
					Template helloTemplate = config.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					helloTemplate.process(document, writer);
					
					System.out.println(writer);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
				return writer;
			}
			
		});
	}

}
