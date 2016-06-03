package io.pivotal.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerClient consumer;

    @RequestMapping("/rest")
    String rest() {
        return consumer.getValue();
    }

    @RequestMapping("/balance")
    String blance() {
        return consumer.getValue2();
    }
}
