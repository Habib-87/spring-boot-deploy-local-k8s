package com.habib.example.k8s.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple/api")
public class SimpleController {
	
	  @GetMapping
	  public List<String> getMessage() {
	    return List.of("Hello", "Your", "Kubernetes", "is", "Working");
	  }
	  
}


