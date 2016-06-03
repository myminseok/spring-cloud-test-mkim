package io.pivotal.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component
//@RibbonClient(name="producer", configuration = RibbonConfiguration.class)  //여기 오면 에러남
public class ConsumerClient {

    @Autowired
//    @LoadBalanced
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancer;


    @HystrixCommand(fallbackMethod = "getProducerFallback")
    public String getValue() {
        return restTemplate.getForObject("http://producer", String.class);
    }

    @HystrixCommand(fallbackMethod = "getProducerFallback")
    public String getValue2() {
        ServiceInstance instance = loadBalancer.choose("producer");
        URI producerUri = URI.create(String.format("http://%s:%d", instance.getHost(),instance.getPort()));
        return new RestTemplate().getForObject(producerUri, String.class);
    }


    private String getProducerFallback() {
        return "fallback";
    }
}
