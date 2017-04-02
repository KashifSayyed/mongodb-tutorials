/**
 * 
 */
package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author Kashif
 *
 */
public class SparkRoutes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spark.get(new Route("/"){

			public Object handle(final Request arg0, final Response arg1) {
				return "Hello World from Spark web root...";
			} 
		
		});
		
		Spark.get(new Route("/test/"){

			public Object handle(final Request arg0, final Response arg1) {
				return "Hello World from Spark web test page...";
			}
		
		});
		
		Spark.get(new Route("/echo/:thing"){

			public Object handle(final Request request, final Response response) {
				return request.params(":thing");
			}
		
		});
	}

}
