package com.yu.crawler.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@Component
public class JsonParamAnalysisUtil {
    // 解析json字符串，返回levels列表
    public static JSONArray parseLevels() {
        Map<String, String> data = new HashMap<>();
        // 读取json文件
        String filePath = "E:\\workspace\\crawler\\crawler\\src\\main\\java\\com\\yu\\crawler\\data\\Abc1.json";
        // 解析json文件
        try {
            // 读取文件内容为字符串
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(json);
            JSONArray levels = new JSONArray();
            extractLevels(jsonObject, levels);
            return levels;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void extractLevels(JSONObject jsonObject, JSONArray levels) {
        while (jsonObject.has("level")) {
            JSONObject levelObject = jsonObject.getJSONObject("level");
            JSONObject levelJson = new JSONObject();
            // 将当前level的内容放入map
            for (String key : levelObject.keySet()) {
                if (!key.equals("level")) {
                    levelJson.put(key, levelObject.get(key).toString());
                }
            }
            levels.put(levelJson);
            // 移动到下一个level
            jsonObject = levelObject;
        }
    }

}
