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
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${spring.application.name}")
	String applicationName;
	
	@Value("${server.port}")
	int port=0;

//	@Value("${key2}")
//	String key;
//	
//	
//	@RequestMapping("/key")
//	public String get(){
//		return  key;
//	}
	
	@RequestMapping("/call")
	@HystrixCommand(fallbackMethod = "fallback")
	public String foo() throws InterruptedException{
		String msg="from "+applicationName+" "+port;
		log.info(msg);
		return msg;
	}

	public String fallback() throws InterruptedException{
		String msg="from "+applicationName+" "+port+ " fallback";
		log.info(msg);
		return msg;
	}
	
}
