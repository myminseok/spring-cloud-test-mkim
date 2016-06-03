package io.pivotal.test;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component

//@RibbonClient(name="producer", configuration = RibbonConfiguration.class)  //여기 오면 에러남
public class ConsumerClient {

    @Autowired
    LoadBalancerClient loadBalancer;

    RestTemplate restTemplate= new RestTemplate();

    @HystrixCommand(fallbackMethod = "getProducerFallback")
    public String getValue() {
        ServiceInstance instance = loadBalancer.choose("producer");
        URI producerUri = URI.create(String.format("http://%s:%d", instance.getHost(),instance.getPort()));
        return restTemplate.getForObject(producerUri, String.class);
    }


    private String getProducerFallback() {
        return "fallback";
    }
}
