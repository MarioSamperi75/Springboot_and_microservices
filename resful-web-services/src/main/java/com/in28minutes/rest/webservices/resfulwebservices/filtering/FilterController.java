package com.in28minutes.rest.webservices.resfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

// Dynamic filtering allows to apply different filters in different methods
// Dynamic filtering : remember to define the list of valid filter on the bean
// @JsonFilter("SomeBeanFilter") ON THE BEAN

@RestController
public class FilterController {	
	// I want to show just field1 and field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	// I want to show just field2 and field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfBeans() {
		List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		
		return mapping;
	}

}
