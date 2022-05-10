package com.in28minutes.rest.webservices.resfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// basic solution for versioning: use differents URLs

@RestController
public class PersonVersoningController {
	
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1 () {
		return new PersonV1("Mario Samperi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2 () {
		return new PersonV2(new Name("Mario", "Samperi"));
	}

}
