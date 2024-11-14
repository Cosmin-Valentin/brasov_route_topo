package com.primalsoup.topo.brasov_route_topo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/")
	public String index() {
		return "Home page";
	}
	
	@GetMapping("/hello")
	public Greeting hello(@RequestParam(value = "name", defaultValue = "Bobita") String name) {
		return new Greeting("Hello, " + name);
	}

}
