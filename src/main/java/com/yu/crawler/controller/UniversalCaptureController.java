package com.yu.crawler.controller;


import com.yu.crawler.service.UniversalCaptureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universal")
public class UniversalCaptureController {

    private UniversalCaptureService universalCaptureService;

    public UniversalCaptureController(UniversalCaptureService universalCaptureService) {
        this.universalCaptureService = universalCaptureService;
    }

    @GetMapping("/capture")
    public String capturePage() {
        universalCaptureService.getPageUrls();
        return "Universal Capture";
    }
}
