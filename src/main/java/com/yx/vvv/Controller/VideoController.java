package com.yx.vvv.Controller;


import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.yx.vvv.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;


    //获取用户下直播视频列表
    @RequestMapping("/downVideoAddress")
    @ResponseBody
    public String accessToken(){
        System.out.println("AppKey:===="+videoService.getAppKey());
        System.out.println("Redis查询出来的Token:===="+videoService.twoKey());

        HashMap<String,Object> paranmMap=new HashMap<>();
        paranmMap.put("accessToken",videoService.twoKey());
/*        paranmMap.put("pageStart",0);
        paranmMap.put("pageSize",3);*/

        //链式构建请求 https://open.ys7.com/api/lapp/live/address/limited
        String result2 = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/list")//1
        //String result2 = HttpRequest.post("https://open.ys7.com/api/lapp/live/address/limited")//2
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paranmMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);

        //获取响应状态码https:
        HttpResponse res = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/list").execute();//1
        //HttpResponse res = HttpRequest.post("open.ys7.com/api/lapp/live/address/limited").execute();
        Console.log(res.getStatus());



/*       //获取响应头信息
        HttpResponse res2 = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/list").execute();
        //预定义的头信息
        Console.log(res.header(Header.CONTENT_ENCODING));
        //自定义头信息
        Console.log(res.header("Content-Video"));*/

        //转换
        return result2;
    }



    //获取指定有效期的直播地址
    @RequestMapping("/getLiveVideo")
    @ResponseBody
    public String getLiveLive(){
        System.out.println("Redis查询出来的Token:===="+videoService.twoKey());

        HashMap<String,Object> paranmMap=new HashMap<>();
        //paranmMap.put("accessToken","at.20h863523v1zfck75qgmwhoy7vl2teqp");
        //授权过程获取的access_token
        paranmMap.put("accessToken",videoService.twoKey());
        //通道号，IPC设备填1
        paranmMap.put("channelNo",1);
        //设备序列号,存在英文字母的设备序列号，字母需为大写
        paranmMap.put("deviceSerial",440912222);



        String result = HttpRequest.post("https://open.ys7.com/api/lapp/live/address/limited")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paranmMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result);

        //获取响应状态码https:
        HttpResponse res = HttpRequest.post("open.ys7.com/api/lapp/live/address/limited").execute();
        Console.log(res.getStatus());

        return result;
    }


    //开通直播功能
    @RequestMapping("/openVideo")
    @ResponseBody
    public String openVideo(){
        System.out.println("Redis查询出来的Token:===="+videoService.twoKey());

        HashMap<String,Object> paranmMap=new HashMap<>();
        //paranmMap.put("accessToken","at.20h863523v1zfck75qgmwhoy7vl2teqp");
        //授权过程获取的access_token
        paranmMap.put("accessToken",videoService.twoKey());

        //source type:String
        //直播源，[设备序列号]:[通道号],[设备序列号]:[通道号]的形式，
        // 例如427734222:1,423344555:3，均采用英文符号
        paranmMap.put("source","427734222:3");



        String result = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/open")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paranmMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result);

        //获取响应状态码https:
        HttpResponse res = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/open").execute();
        Console.log(res.getStatus());

        return result;
    }


    //获取直播地址
    @RequestMapping("/getVideoAddress")
    @ResponseBody
    public String getVideoAddress(){
        System.out.println("Redis查询出来的Token:===="+videoService.twoKey());

        HashMap<String,Object> paranmMap=new HashMap<>();
        //paranmMap.put("accessToken","at.20h863523v1zfck75qgmwhoy7vl2teqp");
        //授权过程获取的access_token
        paranmMap.put("accessToken",videoService.twoKey());

        //source type:String
        //直播源，[设备序列号]:[通道号],[设备序列号]:[通道号]的形式，
        // 例如427734222:1,423344555:3，均采用英文符号
        paranmMap.put("source","423344555:3");



        String result = HttpRequest.post("https://open.ys7.com/api/lapp/live/address/get")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paranmMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result);

        //获取响应状态码https:
        HttpResponse res = HttpRequest.post("https://open.ys7.com/api/lapp/live/address/get").execute();
        Console.log(res.getStatus());

        return result;
    }


    //关闭直播功能
    @RequestMapping("/closeVideo")
    @ResponseBody
    public String closeVideo(){
        System.out.println("Redis查询出来的Token:===="+videoService.twoKey());

        HashMap<String,Object> paranmMap=new HashMap<>();
        //paranmMap.put("accessToken","at.20h863523v1zfck75qgmwhoy7vl2teqp");
        //授权过程获取的access_token
        paranmMap.put("accessToken",videoService.twoKey());

        //source type:String
        //直播源，[设备序列号]:[通道号],[设备序列号]:[通道号]的形式，
        // 例如427734222:1,423344555:3，均采用英文符号
        paranmMap.put("source","423344555:3");



        String result = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/close")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paranmMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result);

        //获取响应状态码https:
        HttpResponse res = HttpRequest.post("https://open.ys7.com/api/lapp/live/video/close").execute();
        Console.log(res.getStatus());

        return result;
    }

}
