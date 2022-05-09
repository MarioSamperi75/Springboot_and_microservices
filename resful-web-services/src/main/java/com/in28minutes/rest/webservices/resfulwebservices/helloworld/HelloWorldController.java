package com.in28minutes.rest.webservices.resfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//internationalization
// create the messageSource object
// create the properties file with that exact syntax
// create the local object as parameter (request header) 
// OR pick it up from the local context holder 

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized(
		//	@RequestHeader(name="Accept-Language", required = false ) Locale locale
			) {
		
		return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
		//return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean  helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable (@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

}
