package com.pramati.test;
 
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;

import com.pramati.route.SampleRouteBuilder;
 
public class TestRoute1 {
    public static void main(String[] args) throws Exception {
        CamelContext camelContextObj = new DefaultCamelContext();
        camelContextObj.addRoutes(new SampleRouteBuilder() {
        
			@Override
			public void configure() throws Exception {
				int a=0;
				restConfiguration().component("restlet").host("localhost").port("8081").bindingMode(RestBindingMode.auto);
				/*from("file:/home/preethi/route/IN.txt").
						choice()
						.when(xpath("/Order/Country='USA'")).to("file:/home/preethi/route/A.txt")
						.when(xpath("/Order/Country='UK'")).to("file:/home/preethi/routeB.txt")
						.otherwise().to("file:/home/preethi/route/C.txt")
						.end();*/
				//rest("/say").get("/hello/{language}").to("jms:queue:hello-${header.language}");
				rest("getMessage").get().route().transform().constant("Hello World");
				//rest("/say/bye").get().consumes("application/json").route().transform().constant("Bye World").endRest()
					//	.post().to("mock:update");
			}
		});
		System.out.println("Starting Camel Context......");
		camelContextObj.start();
		Thread.sleep(5 * 60 * 1000);
		System.out.println("Stopping Camel Context......");
		camelContextObj.stop();
    }
}