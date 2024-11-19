package com.yu.crawler.util;

import org.springframework.stereotype.Component;

@Component
public class RuleCheckUtil {

    //校验字符串是不是URL
    public boolean isUrl(String str){
        if(str!= null && str.startsWith("http://") || str.startsWith("https://")){
            return true;
        }
        return false;
    }
}
