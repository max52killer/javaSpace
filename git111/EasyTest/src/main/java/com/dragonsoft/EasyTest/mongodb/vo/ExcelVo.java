package com.dragonsoft.EasyTest.mongodb.vo;

import java.util.Map;

/**
 * @program: EasyTest
 * @description: 表格导出vo
 * @author: songzm
 * @create: 2019-12-27 15:56
 **/
public class ExcelVo {
    //导出的文件名称
    private String fileName;
    //导出参数配置名称（从resources目录下的exportParams.json中配置）
    private String querySchema;
    //接收前端传入的查询条件
    private Map<String, Object> conditions;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getQuerySchema() {
        return querySchema;
    }

    public void setQuerySchema(String querySchema) {
        this.querySchema = querySchema;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }
}
