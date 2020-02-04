package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.service.ExportService;
import com.dragonsoft.EasyTest.mongodb.vo.ExcelVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * @program: EasyTest
 * @description: 数据导出实现类
 * @author: songzm
 * @create: 2019-12-30 16:58
 **/
@Service
public class ExportServiceImpl implements ExportService{

    @Override
    public HSSFWorkbook exportToExcel(ExcelVo vo) {
        return null;
    }
}
