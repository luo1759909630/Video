package com.yx.vvv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yx.vvv.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VvvApplication.class)
public class JsonRedisTest {

    @Autowired
    VideoService videoService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //appKey
    @Value("${appKey}")
    private String appKey;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Test
    public void test() throws JsonProcessingException {
            //1、从redis中获取数据，数据的格式json字符串
            String videoKey=redisTemplate.boundValueOps("videoKey").get();
            //2、判断redis中是否存在数据
            if (null==videoKey){
                videoService.setJson("qwertyuiop");
                ObjectMapper objectMapper = new ObjectMapper();
                videoKey=objectMapper.writeValueAsString(videoService.getJson());
                redisTemplate.boundValueOps("videoKey").set(videoKey);
                System.out.println("========从get中获取user的数据================");
            }else {
                System.out.println("========从redis缓存中获取user的数据================");
            }

        //5、将返回的数据在控制台打印
        System.out.println(videoKey);
        System.out.println(appKey);


    }
}
