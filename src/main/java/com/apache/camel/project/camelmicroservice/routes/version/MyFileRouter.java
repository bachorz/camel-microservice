package com.apache.camel.project.camelmicroservice.routes.version;

import com.apache.camel.project.camelmicroservice.routes.base.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml' "))
                        .log("XML FILE")
//                    .when(simple("${body} contains 'USD' "))
                    .when(method(deciderBean))
                        .log("Not an XML FILE but contains USD")
                    .otherwise()
                        .log("Not an XML FILE")
                .end()
   //             .to("direct://log-file-values")
                .to("file:files/output");

        from("direct:log-file-values")
                .log("${messageHistory} ${file:absolute.path}")
                .log("${file:name} ${file:name.ext} ${file:name.noext}")
                .log("${file:onlyname.noext} ${file:parent} ${file:path}")
                .log("${file:size} ${file:modified}")
                .log("${routeId} ${camelId} ${body}");
    }
}
