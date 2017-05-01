package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${spring.application.name}")
	String applicationName;
	
	@Value("${server.port}")
	int port=0;

	@RequestMapping("/call")
	@HystrixCommand(fallbackMethod = "fallback")
	public String foo() throws InterruptedException{
		String msg="from "+applicationName+" "+port;
		String call=restTemplate.getForObject("http://service2/call",String.class);
		log.info(msg+" ->"+call);
		return msg+" ->"+call;
	}

	public String fallback() throws InterruptedException{
		String msg="from "+applicationName+" "+port+ " fallback";
		log.info(msg);
		return msg;
	}
	
	

}


