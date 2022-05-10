package com.fc.service.impl;

import cn.hutool.db.handler.PageResultHandler;
import com.fc.dao.BillsMapper;
import com.fc.dao.BilltypeMapper;
import com.fc.entity.Bills;
import com.fc.entity.BillsExample;
import com.fc.entity.Billtype;
import com.fc.service.BillsService;
import com.fc.vo.PageVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillsServiceImpl implements BillsService {
    @Autowired
    private BillsMapper billsMapper;
    @Autowired
    private BilltypeMapper billtypeMapper;

    @Override
    public ResultVo loadAllBills(PageVo pageVo) {
        if (pageVo == null){
            return null;
        }
        PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        BillsExample billsExample = new BillsExample();
        BillsExample.Criteria criteria = billsExample.createCriteria();
        Billtype billtype = null;
        if (pageVo.getTypeid() == null || pageVo.getTypeid() == 0){}else {
            criteria.andTypeidEqualTo(pageVo.getTypeid());
            billtype = billtypeMapper.selectByPrimaryKey(pageVo.getTypeid());
        }
        if (pageVo.getStartDate() == null){}else {
            criteria.andBilltimeGreaterThanOrEqualTo(pageVo.getStartDate());
        }
        if (pageVo.getEndDate() == null){}else {
            criteria.andBilltimeLessThanOrEqualTo(pageVo.getEndDate());
        }

        List<Bills> list = billsMapper.selectByExample(billsExample);
        if (pageVo.getTypeid() == null || pageVo.getTypeid() == 0){
            for (Bills bills : list) {
                billtype = billtypeMapper.selectByPrimaryKey(bills.getTypeid());
                if (billtype != null){
                    bills.setTypeName(billtype.getName());
                }
            }
        }else {
            for (Bills bills : list) {
                assert billtype != null;
                bills.setTypeName(billtype.getName());
            }
        }
        PageInfo<Bills> billsPageInfo = new PageInfo<>(list);

        return new ResultVo(0,"",billsPageInfo.getTotal(),list);

    }

    @Override
    public ResultVo addBills(Bills bills) {

        int i = billsMapper.insertSelective(bills);
        ResultVo vo ;
        if (i == 1){
            vo = new ResultVo(200,"录入成功",null,bills.getId());
        }else {
            vo = new ResultVo(-1,"录入失败",null,null);
        }

        return vo;

    }
}
