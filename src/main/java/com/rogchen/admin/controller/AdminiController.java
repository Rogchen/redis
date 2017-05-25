package com.rogchen.admin.controller;

import com.rogchen.admin.domain.AdminUser;
import com.rogchen.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/6.
 * {tags}
 */
@Controller("adminiController")
@RequestMapping("/admin/sa")
public class AdminiController {
    @Autowired
    private AdminService adminiService;

    @RequestMapping(value = "")
    public String testEnpty(){
      return "main";
    }

    @RequestMapping(value = "adminManage/main")
    public String main(HttpServletRequest request, HttpServletResponse response, Model model){
        AdminUser adminUser = adminiService.getAdminUser(1l);
        System.out.println("*"+"*********************************8");
        System.out.println(adminUser.getUserName()+adminUser.getRealName());
        model.addAttribute("user",adminUser);
        return "admin/indexMain";
    }


    @RequestMapping(value = "adminManage/list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model){
        AdminUser adminUser = adminiService.getAdminUser(1l);
        System.out.println(adminUser.getUserName());
        model.addAttribute("user",adminUser);
        return "admin/adminIndex";
    }
}
