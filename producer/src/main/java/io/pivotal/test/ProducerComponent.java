package io.pivotal.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kimm5 on 6/1/16.
 */
@Component
public class ProducerComponent {

    Log log = LogFactory.getLog(ProducerComponent.class);
    AtomicInteger counter = new AtomicInteger(0);

    @Value("${server.port}")
    String port;

    @Value("${latency:0}")
    int latency;

    @HystrixCommand
    public String getValue() {
        if(latency>0){
            try{
                Thread.sleep(latency);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        int value = counter.getAndIncrement();
        log.info("Produced a value: " + value);

        return String.format(" %s %d ", port, value);
    }
}
