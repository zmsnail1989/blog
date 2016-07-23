package com.snail.architecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/")
@Controller
public class BlogController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String init(){
		return "/blog";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/m")
	public String init_m(){
		return "/wedding/gallery";
	}
}
