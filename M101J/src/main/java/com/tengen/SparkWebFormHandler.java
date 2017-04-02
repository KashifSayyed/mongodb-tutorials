/**
 * 
 */
package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Kashif
 *
 */
public class SparkWebFormHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spark.get(new Route("/"){

			public Object handle(final Request arg0, final Response arg1) {
				Configuration config = new Configuration();
				
				config.setClassForTemplateLoading(SparkWebFormHandler.class, "/");
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = config.getTemplate("FruitPicker.ftl");
					
					Map<String, Object> fruitMap = new HashMap<String, Object>();
					
					fruitMap.put("fruits",Arrays.asList("Banana","Orange","Mango","Pineapple","Grapes"));
					helloTemplate.process(fruitMap, writer);
					
					System.out.println(writer);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
				return writer;
			} 
		
		});
		
		Spark.post(new Route("/favorite_fruit"){

			public Object handle(final Request request, final Response response) {
				final String favFruit = request.queryParams("fruit");
				if(favFruit == null){
					return "Why dont you pick one?";
				}else{
					return "Your favorite fruit is "+ favFruit;
				}
			}
		
		});
		
	}

}
