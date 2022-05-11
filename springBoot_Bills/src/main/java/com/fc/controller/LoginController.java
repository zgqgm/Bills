package com.fc.controller;

import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        loginService.getCode(req,resp);
    }
}
