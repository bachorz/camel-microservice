package com.apache.camel.project.camelmicroservice.routes.base;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer")
                //        .transform().constant("My constant message")
                //         .transform().constant("Time now is: " + LocalDateTime.now())
                .bean("getCurrentTimeBean")
                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is: " + LocalDateTime.now();

    }
}