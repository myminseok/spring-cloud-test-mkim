package io.pivotal.test;

import com.netflix.appinfo.CloudInstanceConfig;
import com.netflix.appinfo.EurekaInstanceConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {


	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);

	}
//	public EurekaApplication(){
//		log.info("lease expire "+ new CloudInstanceConfig().getLeaseExpirationDurationInSeconds());
//		log.info("renew "+new CloudInstanceConfig().getLeaseRenewalIntervalInSeconds());
//	}
}
