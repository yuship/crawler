package com.yu.crawler.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import java.util.List;

public interface UniversalCaptureService {


    /*获取页面链接集合*/
    List<JSONObject> getPageUrls();
}
