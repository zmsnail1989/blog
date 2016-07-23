package com.snail.architecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snail.architecture.util.EhCacheUtils;


@Controller
public class TestController {

	@RequestMapping(value="/set")
	public String set(){
		EhCacheUtils.putWithLocalCache("test", "aaaaaaaaaa", 1);
		return null;
	}
	@RequestMapping(value="/get")
	public String get(){
		System.out.println(EhCacheUtils.getLocalCache("test"));
		return null;
	}
}
