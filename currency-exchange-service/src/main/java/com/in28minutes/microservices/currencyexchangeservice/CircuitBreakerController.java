package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	Logger logger = org.slf4j.LoggerFactory.getLogger(CircuitBreakerController.class);
	
	
	//@Retry(name="default") 	// default is 3 times
	
	// you can configure sample-api in the properties a provide a fallback method
	@Retry(name="sample-api", fallbackMethod = "hardCodedResponse") 	
	@GetMapping("/sample-api")
	public String simpleApi() {
		
		logger.info("Sample API call received");
		//we just call a not existing URL to create an error
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/not-existing-url", String.class);
		
		return forEntity.getBody();
	}
	
	public String hardCodedResponse (Exception ex) {
		return "Fallback-Response";
	}

}
