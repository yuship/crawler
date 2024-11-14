package com.yu.crawler.controller.Config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    // 连接超时时间修改后需要重启生效
    @Value("${httpclient.connect.timeout}")
    private int connectTimeout;
    @Value("${httpclient.socket.timeout}")
    private int socketTimeout;

    @Bean
    public CloseableHttpClient httpClient() {
        int maxTotal = 200; // 最大连接数
        int maxPerRoute = 100; // 每个路由的最大连接数
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        //重试策略： 设置请求失败后的重试策略。
        RetryHandler retryHandler = new DefaultHttpRequestRetryHandler(3, true); // 3 次重试
        //代理设置： 指定代理服务器以通过代理连接。
        request.addHeader("User-Agent", "MyApp/1.0");
        HttpHost proxy = new HttpHost("proxy.host.com", 8080);
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        //SSL/TLS 设置： 如果需要使用 HTTPS，可以配置 SSL/TLS。
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(new TrustSelfSignedStrategy()) // 自签名证书
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();
        // 请求配置： 设置请求的配置，比如连接超时、读取超时等。
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)  // 连接超时时间
                .setSocketTimeout(socketTimeout)    // 服务器响应数据超时
                .build();
        //设置心跳机制： 对于长连接，可以设置心跳机制，保持连接的活跃性。
        KeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy();
        // 连接管理器： 使用连接池管理连接，以提高性能。
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig) // 设置默认请求配置
                .build();
    }


}

