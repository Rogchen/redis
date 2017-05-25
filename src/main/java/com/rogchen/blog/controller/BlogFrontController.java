package com.rogchen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/7.
 * {tags}
 */
@Controller("blogFrontController")
@RequestMapping("/blog")
public class BlogFrontController{


    @RequestMapping("")
    public String incde(HttpServletRequest request, HttpServletResponse response, Model model){
        return "blog/fornt/index";
    }

    @RequestMapping("front/blogIndex")
    public String blogIndex(HttpServletRequest request, HttpServletResponse response, Model model){
        return "blog/fornt/index";
    }

}
