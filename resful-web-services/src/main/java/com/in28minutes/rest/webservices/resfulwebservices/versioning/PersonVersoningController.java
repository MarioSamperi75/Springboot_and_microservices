package com.in28minutes.rest.webservices.resfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PersonVersoningController {
	// basic solution for versioning: use differents URLs
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1 () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2 () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}
	
	// basic solution for versioning: use different params
	// .../person/param?version=1
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonV1Param () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonV2Param () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}
	
	// basic solution for versioning: use different headers
	// you should add the header in your request
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getPersonV1Headers () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getPersonV2Headers () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}

}
