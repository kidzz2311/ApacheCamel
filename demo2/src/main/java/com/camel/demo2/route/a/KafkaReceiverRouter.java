package com.camel.demo2.route.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


public class KafkaReceiverRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("kafka:myKafkaTopic")
        .to("log:received-message-from-kafka");
    }
    
}
