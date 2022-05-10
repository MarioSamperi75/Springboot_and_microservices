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
	
	// basic solution for versioning: use differents params
	// .../person/param?version=1
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonV1Param () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonV2Param () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}

}
