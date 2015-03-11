package com.cmcltd.springdata.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcltd.springdata.model.Greeting;

@Controller

public class GreetingController {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping("/greeting")
    @ResponseBody	
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	logger.info("Invoking Greeting method with " + name);
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
