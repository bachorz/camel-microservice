package com.apache.camel.project.camelmicroservice.routes.patterns;

import org.apache.camel.DynamicRouter;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AbstractListAggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;

//@Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    private SplitterComponent splitterComponent;

    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {

        getContext().setTracing(true);

        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));
//        from("timer:multicast?period=10000")
//                .multicast()
//                .to("log:something1", "log:something2");

//        from("file:files/csv")
//                .unmarshal().csv()
//                .split(body())
//                .to("activemq:split-queue");
//        from("file:files/csv")
//                .convertBodyTo(String.class)
//         //       .split(body(), ",")
//                .split(method(splitterComponent))
//                .to("activemq:split-queue");

//        from("file:files/aggregate-json")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .aggregate((Expression) simple("${body.to}"), new ArrayListAggregationStrategy())
//                .completionSize(3)
//                .to("log:aggregate-json");
        String routingSlip = "direct:endpoint1,direct:endpoint2";

//        from("timer:routingSlip?period=10000")
//                .transform().constant("My Message is Hardcoded")
//                .routingSlip(simple(routingSlip));

        from("timer:dynamicRouterBean?period={{timePeriod}}")
                .transform().constant("My Message is Hardcoded")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                .wireTap("log:wire-tap")
                .to("{{endpoint-for-logging}}");

        from("direct:endpoint2")
                .log("BBBBBBBBBBBBBBBBB")
                .to("log:directendpoint2");

        from("direct:endpoint3")
                .log("CCCCCCCCCCCCCCCCCc")
                .to("log:directendpoint3");

    }

}
