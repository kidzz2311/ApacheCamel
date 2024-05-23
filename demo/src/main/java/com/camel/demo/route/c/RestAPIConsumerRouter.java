package com.camel.demo.route.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class RestAPIConsumerRouter extends RouteBuilder {
    private String from = "EUR";
    private String to = "USD";

    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost").port(8082);

        from("timer:rest-api-consumer?period=10000")
                // .setHeader("from",() -> from)
                // .setHeader("to", () -> to)
                .to("rest:get:/cities/all")
                .log("${body}")
                .to("activemq:rest-api-queue");
    }

}
