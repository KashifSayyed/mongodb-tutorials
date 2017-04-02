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

/**
 * @author Kashif
 *
 */
public class HelloWorldFromFreeMarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration config = new Configuration();
		
		config.setClassForTemplateLoading(HelloWorldFromFreeMarkerStyle.class, "/");
		
		try {
			Template helloTemplate = config.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			
			helloMap.put("name","FreeMarker");
			helloTemplate.process(helloMap, writer);
			
			System.out.println(writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
