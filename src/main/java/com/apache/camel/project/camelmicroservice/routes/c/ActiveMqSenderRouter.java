package com.apache.camel.project.camelmicroservice.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//      from("timer:active-mq-timer?period=10000")
//              .transform().constant("My message for Active MQ")
//              .log("${body}")
//              .to("activemq:my-queue");

//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:my-queue");

        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-xml-queue");
    }
}
