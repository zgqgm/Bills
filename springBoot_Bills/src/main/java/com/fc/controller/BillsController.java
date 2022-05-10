package com.fc.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fc.entity.Bills;
import com.fc.service.BillsService;
import com.fc.vo.PageVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("bills")
public class BillsController {
    @Autowired
    private BillsService billsService;

    @ResponseBody
    @GetMapping("loadAllBills")
    public ResultVo loadAllBills(PageVo pageVo) {
        return billsService.loadAllBills(pageVo);
    }


    @RequestMapping("toBillsList")
    public String toBillsList() {
        return "list";
    }

    @ResponseBody
    @RequestMapping("addBills")
    public ResultVo addBills(Bills bills){

        return billsService.addBills(bills);
    }
}
