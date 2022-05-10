package com.fc.controller;

import com.fc.service.BillTypeService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("billtype")
public class BillTypeController {
    @Autowired
    private BillTypeService billTypeService;

    @ResponseBody
    @GetMapping("loadAllBillType")
    public ResultVo loadAllBillType(){
        return billTypeService.loadAllBillType();
    }

}


