package com.akinramirez.orderapi.controllers;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akinramirez.orderapi.entity.Product;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {

	// private static final Logger log =
	// Logger.getLogger(HelloWorldController.class.getCanonicalName());
	// log.info("findProduct =>");

	@GetMapping(value = "hello")
	public String hello() {
		return "Hello World";
	}
}
