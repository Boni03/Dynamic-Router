package com.pramati.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

public class DynamicRouterBean {
	public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRoute) {
		if (body.toString().contains("today")) {
			return "direct://dateRoute";
		} else if (body.toString().contains("Atlanta")) {
			return "direct://locationRoute";
		} else {
			return null;
		}
	}
}