package com.in28minutes.rest.webservices.resfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PersonVersoningController {
	// URI VERSIONING
	// basic solution for versioning: use differents URIs
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1 () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2 () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}
	
	// REQUEST PARAMETER VERSIONING
	// .../person/param?version=1
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonV1Param () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonV2Param () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}
	
	//HEADER VERSIONING
	// you should add the header in your request
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getPersonV1Headers () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getPersonV2Headers () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}
	
	//MEDIA TYPE VERSIONING- (also called "Content negotiation" or "Accept Header")
	// you should add the produces specification in the Accept header
	@GetMapping(value="/person/produces", produces="application/ms.company.app-v1+json")
	public PersonV1 getPersonV1Produces () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/produces", produces="application/ms.company.app-v2+json")
	public PersonV2 getPersonV2Produces () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}

}
