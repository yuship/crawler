package com.yu.crawler.service.impl;

import com.yu.crawler.service.HttpClientService;
import com.yu.crawler.util.HttpClientUtil;
import org.springframework.stereotype.Service;


@Service
public class HttpClientServiceImpl implements HttpClientService {

    private HttpClientUtil httpClient;

    public HttpClientServiceImpl(HttpClientUtil httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public String getHtml(String url) {
        httpClient.getRequest("https://www.baidu.com");
        return "";
    }
}
