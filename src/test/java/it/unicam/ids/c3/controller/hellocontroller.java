package it.unicam.ids.c3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellocontroller {
	
	@GetMapping
	public String hellocontroller() {
		return "ciaoooo";
	}
	@PostMapping("/ciao")
	public String ciaofederico(@RequestParam String name) {
		return "ciao " + name;
	}
}
