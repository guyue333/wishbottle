package com.hust.software.wishbottle.utils;

import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * http工具类，用来向微信官方发送请求，以获取openid和session_key
 */
public class HttpUtil {

    public static String doGet(String urlPath, HashMap<String,Object> params) throws Exception{
        StringBuilder sb = new StringBuilder(urlPath);
        if(params != null && !params.isEmpty()){
            //说明有参数
            sb.append("?");

            Set<Map.Entry<String, Object>> entries = params.entrySet();
            //遍历params里面的参数
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                String value = "";
                if(null != entry.getValue()){
                    value = entry.getValue().toString();
                    //转码
                    value = URLEncoder.encode(value, "UTF-8");
                }
                sb.append(key).append("=").append(value).append("&");
            }

            sb.deleteCharAt(sb.length()-1);      //删除最后一个&
        }

        //打印最终得到的请求地址
//        System.out.println(sb.toString());

        URL url = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);    //连接超时为5秒
        conn.setRequestMethod("GET");    //请求方式为GET

        if(conn.getResponseCode() == HttpStatus.SC_OK){    //HttpStatus.SC_OK = 200
            //如果服务器返回的状态码为200，说明连接成功
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sbs = new StringBuilder();
            String line;

            //读取服务器返回的字符流
            while((line = reader.readLine()) != null ){
                sbs.append(line);
            }

            return sbs.toString();
        }else{
            System.out.println("接口发生未处理异常，请求状态码："+conn.getResponseCode());
            return null;
        }
    }
}
