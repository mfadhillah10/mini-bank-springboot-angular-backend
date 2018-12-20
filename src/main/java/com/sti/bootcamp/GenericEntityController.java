package com.sti.bootcamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/test")
public class GenericEntityController {
	
	/*@GetMapping(value = "/hello")
	public String entity() {
		return "hello world";
	}*/
	
	@GetMapping("/hello")
	public String hello(@RequestParam("data") String id) {
		return "hello "+id;
	}
	
	@GetMapping("/hello/{data}")
	public String helloPathVariable(@PathVariable("data") String id) {
		return "hello "+id;
	}
	
	@PostMapping("/hello")
	public String hello(@RequestBody Customer customer) {
		return "hello "+customer.getFirst_name();
	}
}