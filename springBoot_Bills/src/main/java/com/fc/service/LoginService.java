package com.fc.service;

import com.fc.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface LoginService {
    void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    ResultVo login(HttpServletRequest req, HttpServletResponse resp);
}
