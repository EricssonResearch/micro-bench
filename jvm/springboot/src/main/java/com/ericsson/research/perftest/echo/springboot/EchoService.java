package com.ericsson.research.perftest.echo.springboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource class
 */
@RestController
@EnableAutoConfiguration
public class EchoService {

    @RequestMapping("/EchoService/echo")
    @ResponseBody
    String echo(@RequestBody String body) {
        return body;
    }
}
