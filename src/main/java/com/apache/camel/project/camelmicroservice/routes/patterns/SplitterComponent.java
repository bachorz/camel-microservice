package com.apache.camel.project.camelmicroservice.routes.patterns;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SplitterComponent {

    public List<String> splitInput(String body){
        return List.of("ABC", "DEF", "GHI");
    }
}
