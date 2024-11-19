package com.yu.crawler.controller;


import com.yu.crawler.service.HttpClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("testController")
public class Test {

    HttpClientService httpClientService;
    public Test(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @GetMapping("/")
    public String test() {
        return httpClientService.getHtml("https://www.baidu.com");
    }


}
