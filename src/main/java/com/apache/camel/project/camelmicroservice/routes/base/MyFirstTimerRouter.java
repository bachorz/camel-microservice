package com.apache.camel.project.camelmicroservice.routes.base;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;
    @Override
    public void configure() throws Exception {

        from("timer:first-timer")
                .log("${body}")
                .transform().constant("My Constant Message")
                .log("${body}")
                .bean(getCurrentTimeBean,"getCurrentTime")
                .log("${body}")
                .bean(loggingComponent)
                .process(new SimpleLoggingProcessor())
                .to("log:first-timer");
    }
}
