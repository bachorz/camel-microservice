package com.apache.camel.project.camelmicroservice.routes.version;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml' "))
                        .log("XML FILE")
                    .when(simple("${body} contains 'USD' "))
                        .log("Not an XML FILE but contains USD")
                    .otherwise()
                        .log("Not an XML FILE")
                .end()
                .log("${body} path to file ${file:absolute.path}")
                .to("file:files/output");
    }
}
