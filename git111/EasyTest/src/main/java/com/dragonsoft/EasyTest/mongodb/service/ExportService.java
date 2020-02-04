package com.dragonsoft.EasyTest.mongodb.service;

import com.dragonsoft.EasyTest.mongodb.vo.ExcelVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface ExportService {
    HSSFWorkbook exportToExcel(ExcelVo vo);
}
