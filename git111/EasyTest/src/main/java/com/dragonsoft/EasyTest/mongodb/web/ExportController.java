package com.dragonsoft.EasyTest.mongodb.web;

import com.alibaba.fastjson.JSON;
import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.mongodb.po.TUser;
import com.dragonsoft.EasyTest.mongodb.service.ExportService;
import com.dragonsoft.EasyTest.mongodb.vo.ExcelVo;
import com.dragonsoft.EasyTest.util.excel.AnalysisExcelData;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @program: EasyTest
 * @description: 文件导入导出controller
 * @author: songzm
 * @create: 2019-12-27 15:33
 **/
@RestController
@CrossOrigin
@RequestMapping("/file")
public class ExportController {

    @Autowired
    private ExportService exportService;
    /**
     * 文件下载（必须填写resources目录下的文件名称）
     * @throws Exception
     */
    @GetMapping(value = "/downloadTemp/{fileName}")
    public void downloadTemp(@PathVariable String fileName, HttpServletResponse resp)throws Exception{
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition", "attachment; filename="+fileName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos=null;
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "template" + File.separator + fileName);
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[512];

            bos = new BufferedOutputStream(resp.getOutputStream());
            int len = 0;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @PostMapping("/uploadList")
    public Result importList(@RequestBody List<TUser> users){
        System.out.println("前端解析excel上传的用户信息："+ JSON.toJSONString(users));
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"上传成功！",users);
    }
    @PostMapping("/uploadFile")
    public Result importFile(MultipartFile file){
        String pathName = "D:/dmpFile/";
        String fullFileName = pathName + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        FileOutputStream fos = null;
        Result result=null;
        try {
            fos=new FileOutputStream(fullFileName);
            fos.write(file.getBytes());
            System.out.println("上传的文件内容："+new String(file.getBytes(),"utf-8"));
            List results= AnalysisExcelData.getExcelData(file);
            System.out.println(results);
            result=new Result(StatusCodeEnum.SUCCESS.getCode(),"文件上传成功！！！",result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 导出excel
     */
    @PostMapping(value = "/exportExcel")
    public void exportUsers(@RequestBody ExcelVo excelVo, HttpServletResponse response)throws IOException{

        HSSFWorkbook workbook = exportService.exportToExcel(excelVo);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+ excelVo.getFileName()+".xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
