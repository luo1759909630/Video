package com.yx.vvv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    //测试的
    private String Json;
    public String getJson() {
        return Json;
    }
    public void setJson(String json) {
        Json = json;
    }

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //appKey
    @Value("${appKey}")
    private String appKey;
    //secret
    @Value("${secret}")
    private String secret;

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }

    //判断2个密码是否正确
    public String twoKey(){
        boolean relust=false;
        if (appKey!=null&&secret!=null){
            return  redisTemplate.boundValueOps("accessToken").get();
        }else {
            return "您的密码错误";
        }
    }
}
