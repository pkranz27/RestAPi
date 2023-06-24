package com.Pk.Udemy.Verisoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charile");
	}
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name ("Bob","Charlie"));
	}
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersionOfPersonReqeustParameter() {
		return new PersonV1("Bob Charile");
	}
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonReqeustParameter() {
		return new PersonV2(new Name ("Bob","Charlie"));
	}
	@GetMapping(path="/person/header",headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonReqeustHeader() {
		return new PersonV1("Bob Charile");
	}
	@GetMapping(path="/person", headers="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonReqeustHeader() {
		return new PersonV2(new Name ("Bob","Charlie"));
	}
	@GetMapping(path="/person/accept",produces="application/vnd.company.app.-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charile");
	}
	@GetMapping(path="/person",produces="application/vnd.company.app.-v1+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name ("Bob","Charlie"));
	}
}
