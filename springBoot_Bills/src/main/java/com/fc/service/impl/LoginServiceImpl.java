package com.fc.service.impl;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.entity.UserExample;
import com.fc.service.LoginService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        HttpSession session = req.getSession(true);
        session.setAttribute("code",captcha);
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }

    @Override
    public ResultVo login(HttpServletRequest req, HttpServletResponse resp) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (req.getParameter("loginname")== null){

        }else {
            criteria.andLoginnameEqualTo(req.getParameter("loginname"));
        }
        if (req.getParameter("pwd")== null){

        }else {
            criteria.andLoginnameEqualTo(req.getParameter("pwd"));
        }
        List<User> users;
        try {
            users = userMapper.selectByExample(userExample);
            return new ResultVo(200,"登陆成功",null,null);
        }catch (Exception e){
            return new ResultVo(-1,"用户名或密码不正确",null,null);
        }

    }
}
