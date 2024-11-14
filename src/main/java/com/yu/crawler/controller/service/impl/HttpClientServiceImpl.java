package com.yu.crawler.controller.service.impl;

import com.yu.crawler.controller.Config.HttpClientConfig;
import com.yu.crawler.controller.service.HttpClientService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


@Service
public class HttpClientServiceImpl implements HttpClientService {

    private CloseableHttpClient httpClient;

    public HttpClientServiceImpl(HttpClientConfig httpClient) {
        this.httpClient = httpClient.httpClient();
    }


    @Override
    public String getHtml(String url) {
        String result = null;
        HttpGet request = new HttpGet(url);
        // 设置请求头
        request.addHeader("Authorization", "Bearer token");
        request.addHeader("User-Agent", "Mozilla/5.0");
        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpResponse httpResponse = response;
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
