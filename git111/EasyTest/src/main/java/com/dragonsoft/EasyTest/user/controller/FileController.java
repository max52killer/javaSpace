package com.dragonsoft.EasyTest.user.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @PostMapping("uploadFile")
    @ResponseBody
    public void getFile(@ModelAttribute Object object) {
        System.out.println("上传文件。。。。。");
    }
}
