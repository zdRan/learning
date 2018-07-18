package com.zdran.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create by ranzd on 2018/7/18
 *
 * @author cm.zdran@gmail.com
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/helloSpring", method = RequestMethod.GET)
    public String getAccountByName() {
        return "helloSpring";
    }
}
