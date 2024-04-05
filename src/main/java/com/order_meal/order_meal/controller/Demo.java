package com.order_meal.order_meal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.order_meal.order_meal.entity.FileData;
import com.order_meal.order_meal.model.response.FileResponse;
import com.order_meal.order_meal.service.Impl.FileService;

@RestController
@RequestMapping("/api")
public class Demo {

    @Autowired
    FileService fileService;
    @RequestMapping(path = "", method = RequestMethod.POST)
    public String getLogin() {
        System.out.println("有鬼阿..........=.=+，1");

        return "login";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getLogin2() {
        System.out.println("有鬼阿..........=.=+， 2");

        return "login";
    }

    @Transactional
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<FileResponse> uploadFile(MultipartFile file) {

        System.out.println(file.getName());

        FileData save = fileService.save(file);

        FileResponse fileResponse = new FileResponse(save.getId(),  save.getFileName());
        return ResponseEntity.ok().body(fileResponse);
    }

    @Transactional
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        // 获取请求头中的 X-XSRF-TOKEN
        String xsrfToken = request.getHeader("X-XSRF-TOKEN");
        String abc = request.getHeader("123");
        String tttt = request.getHeader("tttt");
        String token = request.getHeader("token");
        // 打印 X-XSRF-TOKEN 的值
        System.out.println("X-XSRF-TOKEN: " + xsrfToken);
        System.out.println("abc: " + abc);
        System.out.println("tttt: " + tttt);
        System.out.println("token: " + token);
        
        // 返回响应
        return "Received X-XSRF-TOKEN: " + xsrfToken +  "    abc: " + abc + "   tttt: " + tttt  +  "   token: " + token  ;
    }
}
