package com.camel.demo.route.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    private SplitterComponent splitterComponent;

    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {
        getContext().setTracing(true);
        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));
        // pipeline
        // Content Based Routing - choice()

        // multicast
        // from("timer:multicast?period=10000")
        // .multicast()
        // .to("log:something1", "log:something2");

        // from("file:files/csv")
        // .unmarshal().csv()
        // .split(body())
        // .to("activemq:split-queue");

        // Msg,Msg2,Msg3
        // from("file:files/csv")
        // .convertBodyTo(String.class)
        // .split(method(splitterComponent, "split"))
        // .to("activemq:split-queue");

        from("file:files/aggregate-json")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                // .completionTimeout(3000)
                .to("log:aggregate-json");

        // routing slip
        String routingSlip = "direct:endpoint1, direct:endpoint3";

        // from("timer:routingSlip?period=10000")
        // .transform().constant("Hello World")
        // .routingSlip(simple(routingSlip));

        // Dynamic routing
        from("timer:routingSlip?period={{timer-period}}")
                .transform().constant("Hello World")

                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                .wireTap("log:wire-tap")
                .to("{{endpoint-for-logging}}");

        from("direct:endpoint2")
                .to("log:directendpoint2");

        from("direct:endpoint3")
                .to("log:directendpoint3");
    }

}

@Component
class SplitterComponent {
    public List<String> split(String body) {
        return List.of(body.split(","));
    }
}

@Component
class DynamicRouterBean {

    Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);
    int invocations;

    public String decideEndpoint(@ExchangeProperties Map<String, String> properties,
            @Headers Map<String, String> headers, @Body String body) {
        logger.info("{} {} {}", properties, headers, body);
        invocations++;
        if (invocations % 3 == 0) {
            return "direct:endpoint1";
        }
        if (invocations % 3 == 1) {
            return "direct:endpoint2, direct:endpoint3";
        }
        return null;
    }
}
