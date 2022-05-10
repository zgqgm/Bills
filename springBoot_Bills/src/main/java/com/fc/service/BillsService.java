package com.fc.service;

import com.fc.entity.Bills;
import com.fc.vo.PageVo;
import com.fc.vo.ResultVo;

public interface BillsService {
    ResultVo loadAllBills(PageVo pageVo);

    ResultVo addBills(Bills bills);
}
