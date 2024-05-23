package com.camel.demo.route.b;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class NewFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
        //pipeline

        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                .when(simple("${file:ext} ends with 'xml'"))
                .log("XML File")
                .when(method(deciderBean, "isThisConditionMet"))
                .log("Not XML File but contain USD")
                .otherwise()
                .log("Not XML File")
                .end()
                // .to("direct:log-file-values")
                .to("file:files/output");

        // from("direct:log-file-values")
        //         .log("${messageHistory} ${file:absolute.path}")
        //         .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
        //         .log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}")
        //         .log("${file:size} ${file:modified}")
        //         .log("${routeId} ${camelId} ${body}");
    }

}

@Component
class DeciderBean{

    Logger logger = LoggerFactory.getLogger(DeciderBean.class);
    public boolean isThisConditionMet(@Body String body, @Headers Map<String,String> headers
    , @ExchangeProperties Map<String,String> exchangeProperties){
        logger.info("{} {} {}", body, headers, exchangeProperties);
        return true;
    }
}
