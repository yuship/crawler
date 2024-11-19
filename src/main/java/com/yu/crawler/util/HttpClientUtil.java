package com.yu.crawler.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HttpClientUtil {

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }

    public Document getRequest(String url){
        CloseableHttpClient httpClient = httpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            String s = EntityUtils.toString(response.getEntity(), "UTF-8");
            return Jsoup.parse(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
