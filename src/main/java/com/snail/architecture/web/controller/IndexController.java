package com.snail.architecture.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snail.architecture.entity.MenuVo;
import com.snail.architecture.entity.User;
import com.snail.architecture.service.ResourceService;
import com.snail.architecture.service.UserService;
import com.snail.architecture.web.bind.annotation.CurrentUser;

/**
 * <p>User: Snail
 * <p>Date: 15-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/backend")
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String index() {
        return "/backend/admin-index";
    }
    @RequestMapping("/menu")
    public @ResponseBody Object menu(@CurrentUser User loginUser){
    	Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<MenuVo> menus = resourceService.findMenus(permissions);
        return menus;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "/backend/welcome";
    }


}
