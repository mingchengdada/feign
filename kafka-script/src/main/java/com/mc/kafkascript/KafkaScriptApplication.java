package com.mc.kafkascript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
public class KafkaScriptApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaScriptApplication.class, args);
	}

}
