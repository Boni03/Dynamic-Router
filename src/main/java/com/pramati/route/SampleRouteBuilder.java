package com.pramati.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;

import com.pramati.bean.InputProcessorBean;
import com.pramati.bean.DynamicRouterBean;
import com.pramati.nlp.DateProcessorBean;
import com.pramati.nlp.LocationProcessorBean;

public class SampleRouteBuilder extends RouteBuilder {	
	
	@Autowired
	private InputProcessorBean inputProcessorBean;
	
	@Override
	public void configure() throws Exception {
		
		//restConfiguration().component("restlet").host("localhost").port("8082").bindingMode(RestBindingMode.auto);
		
		//rest("/messages/sendmessage").post().route().bean(inputProcessorBean);

		from("file:/home/preethi/Desktop/dynamicRouter?noop=true").split().tokenize("\n").dynamicRouter(method(DynamicRouterBean.class, "route"));

		from("direct:locationRoute").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String message = exchange.getIn().getBody().toString();
				LocationProcessorBean locationBean = new LocationProcessorBean();
				locationBean.findLocation(message);
				message = "I am on leave today";
				exchange.getIn().setBody(message);
			}
		});

		from("direct:dateRoute").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String message = exchange.getIn().getBody().toString();
				DateProcessorBean dateBean = new DateProcessorBean();
			    dateBean.findDate(message);
				message = "bye";
				exchange.getIn().setBody(message);
			}
		});
}
}