/**
 * 
 */
package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author Kashif
 *
 */
public class HelloWorldSparkFreeMarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spark.get(new Route("/"){

			public Object handle(final Request arg0, final Response arg1) {
				Configuration config = new Configuration();
				
				config.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class, "/");
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = config.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					
					helloMap.put("name","FreeMarker");
					helloTemplate.process(helloMap, writer);
					
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
