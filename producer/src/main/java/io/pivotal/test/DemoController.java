package io.pivotal.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    ProducerComponent producer;

    @RequestMapping("/")
    String call(){
        return producer.getValue();
    }
}
