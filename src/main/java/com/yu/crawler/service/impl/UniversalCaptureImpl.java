package com.yu.crawler.service.impl;

import com.yu.crawler.service.UniversalCaptureService;
import com.yu.crawler.util.HttpClientUtil;
import com.yu.crawler.util.JsonParamAnalysisUtil;
import com.yu.crawler.util.RuleCheckUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//多层次抓取的实现类
@Service
public class UniversalCaptureImpl implements UniversalCaptureService {
    private HttpClientUtil httpClient;
    private RuleCheckUtil checkUtil;
    private JsonParamAnalysisUtil jsonUtil;

    public UniversalCaptureImpl(HttpClientUtil httpClient, RuleCheckUtil checkUtil, JsonParamAnalysisUtil jsonUtil) {
        this.httpClient = httpClient;
        this.checkUtil = checkUtil;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public List<JSONObject> getPageUrls() {
        //解析json要抓取的字段
        JSONArray jsonArray = jsonUtil.parseLevels();
        //定义抓取的数据集合
        List<JSONObject> dataList = new ArrayList<>();
        //上一级的页面链接
        String lastUrlsXpath = "";
        Document doc = null;
        Elements elementsUrl = null;
        for (Object fieldObject : jsonArray) {
            JSONObject fieldJson = (JSONObject) fieldObject;
            //获取第一级页面的Doc
            if (fieldJson.has("link")) {
                //抓取第一级页面
                doc = httpClient.getRequest(fieldJson.getString("link"));
                JSONObject dataLast = new JSONObject();
                dataLast.put("序号", "序号");
                //获取第一级页面要抓取的元素
                captureDetail(dataLast,doc, new JSONObject(fieldJson.getString("content")), dataList);
                lastUrlsXpath = fieldJson.getString("urls");
                //解析下一级的页面链接列表
                //elementsUrl = doc.selectXpath(lastUrlsXpath);
            }/*else if(fieldJson.has("urls")){
                //解析下一级的页面链接列表
                for (Element element : elementsUrl) {
                    //抓取第二级页面
                    String url = element.attr("href") == null? element.attr("src") : element.attr("href");
                    Document docCentre = httpClient.getRequest(url);
                    //获取第二级页面要抓取的元素
                    captureDetail(docCentre,fieldJson.optJSONObject("content"),dataList);
                }
            }*/


        }
        return dataList;
    }

    public List<JSONObject> captureDetail(JSONObject dataLast,Document doc, JSONObject fieldJson, List<JSONObject> dataList) {
        //获取当前页面要存储的元素
        JSONObject data = new JSONObject(dataLast);
        //获取当前页面要抓取的元素
        fieldJson.keySet().forEach(key -> {
            data.put(key, doc.selectXpath(fieldJson.getString(key)).text());
            dataList.add(data);
        });
        //解析当前页面要抓取的字段
        return dataList;
    }


}
