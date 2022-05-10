package com.in28minutes.rest.webservices.resfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@RestController
public class FilterController {	
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("Value1", "Value2", "Value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfBeans() {
		return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6"));
	}

}
