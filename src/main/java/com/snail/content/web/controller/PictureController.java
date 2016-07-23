package com.snail.content.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>User: Snail
 * <p>Date: 15-5-25
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/wedding")
public class PictureController {


    @RequestMapping("/home")
    public String index() {
        return "/wedding/index";
    }
}
