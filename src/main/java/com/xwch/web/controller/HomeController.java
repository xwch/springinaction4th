package com.xwch.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @RequestMapping 可以接受一个String类型的数组
 * Created by xwch on 2017/10/18.
 */
@Controller
@RequestMapping({"/","/homePage"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/home")
    public String homeByPost(){
        return "home";
    }
}
