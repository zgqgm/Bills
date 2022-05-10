package com.fc.service.impl;

import com.fc.dao.BilltypeMapper;
import com.fc.entity.Billtype;
import com.fc.service.BillTypeService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillTypeServiceImpl implements BillTypeService {
    @Autowired
    private BilltypeMapper billtypeMapper;

    @Override
    public ResultVo loadAllBillType() {
        List<Billtype> billTypes = billtypeMapper.selectByExample(null);
        return new ResultVo (0, "",(long) billTypes.size(), billTypes);
    }

}

