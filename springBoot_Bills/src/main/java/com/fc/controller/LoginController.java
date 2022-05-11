package com.fc.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.fc.service.LoginService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("login")
    @ResponseBody
    public ResultVo login(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        CircleCaptcha code = null;
        if (session == null){
            return new ResultVo(-1,"验证码错误",null,req.getParameter("code")+"**"+code);
        }else {
             code = (CircleCaptcha)session.getAttribute("code");
        }

        if (req.getParameter("code") == null||!req.getParameter("code").equals(code.getCode())){
            return new ResultVo(-1,"验证码错误",null,req.getParameter("code")+"**"+code);
        }else {
            return loginService.login(req,resp);
        }
    }
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        loginService.getCode(req,resp);
    }
}
