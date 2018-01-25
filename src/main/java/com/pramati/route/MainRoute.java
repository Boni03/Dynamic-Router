package com.pramati.route;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class MainRoute {

    public static void main(String[] args) {
        SampleRouteBuilder routeBuilder = new SampleRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();
        try {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            System.out.println("Route Started");
            Thread.sleep(30000);
            ctx.stop();
            System.out.println("Route Stopped");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}