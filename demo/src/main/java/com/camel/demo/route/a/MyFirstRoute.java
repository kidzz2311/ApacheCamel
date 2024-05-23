package com.camel.demo.route.a;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// @Component
@RequiredArgsConstructor
public class MyFirstRoute extends RouteBuilder {

    private final GetCurrentTimeBean getCurrentTimeBean;

    private final SimpleLoginProcessing loginProcessing;

    @Override
    public void configure() throws Exception {
        // from("timer:mytimer")
        //         .log("${body}")
        //         .transform().constant("Hello World")
        //         .log("${body}")
        //         // .bean("getCurrentTimeBean")
        //         .bean(getCurrentTimeBean, "getCurrentTime")
        //         .log("${body}")
        //         .bean(loginProcessing)
        //         .log("${body}")
        //         .process(new SimpleLoginProcessor())
        //         .to("log:mylog");
    }

}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time:" + LocalDateTime.now();
    }

}

@Component
class SimpleLoginProcessing {
    private final Logger logger = LoggerFactory.getLogger(SimpleLoginProcessing.class);
    public void process(String message) {
        logger.info("Message: {}", message);
    }

}

class SimpleLoginProcessor implements Processor {
    private final Logger logger = LoggerFactory.getLogger(SimpleLoginProcessor.class);
    @Override
    public void process(Exchange exchange) {
        logger.info("SimpleLoginProcessor: {}", exchange.getMessage().getBody());  
    }
}