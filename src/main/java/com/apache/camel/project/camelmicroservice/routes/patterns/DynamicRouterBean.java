package com.apache.camel.project.camelmicroservice.routes.patterns;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicRouterBean {

    Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);
    int invocation;

    public String decideTheNextEnpoint(@ExchangeProperties Map<String, String> properties, @Headers Map<String, String> headers, @Body String body) {

        logger.info("{} {} {}", properties, headers, body);
        invocation++;

        if (invocation % 3 == 0) {
            return "direct:endpoint1";
        }
        if(invocation % 3 == 1) {
            return "direct:endpoint2";
        }
        return "direct:endpoint3";
    }
}
