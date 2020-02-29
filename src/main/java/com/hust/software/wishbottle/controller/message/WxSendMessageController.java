package com.hust.software.wishbottle.controller.message;


import com.hust.software.wishbottle.pojo.message.TemplateData;
import com.hust.software.wishbottle.pojo.message.WxMessVo;
import com.hust.software.wishbottle.pojo.user.User;
import com.hust.software.wishbottle.service.message.WxSendMessageService;
import com.hust.software.wishbottle.service.user.UserUserService;
import jdk.nashorn.internal.ir.ReturnNode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 发送微信订阅消息的Controller
 */
@RestController
public class WxSendMessageController {
    //小程序 appid
    private String appID = "wx9860a856c379df42";
    //小程序 appsecret
    private String appSecret = "eeb987f72c854e7d86c8545ced1d2d38";

    @Autowired
    UserUserService userUserService;

    @Autowired
    WxSendMessageService wxSendMessageService;

    /**
     * 根据用户id查询用户是否有新的消息
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/message/{id}")
    public String sendMessage(@PathVariable("id") int userId) throws Exception{
        //查询数据库中是否有新创建的回复
        int i = wxSendMessageService.checkReplyRecord(userId);


        if(i == 0){     //如果没有，则不用给用户发送订阅消息
            return "nothing be created";
        }else{      //如果有，则给用户发送订阅消息
            //根据userid查询用户的openid
            User user = userUserService.getUserInfoById(userId);

            //传入用户的openid
            push(user.getUserOpenid());

            //发送订阅消息以后修改数据库中的心愿回复的状态
            wxSendMessageService.UpdateReplyStatus(userId);
            return "something be created";
        }

    }

    /**
     * 根据用户openid发送订阅消息
     * @param openid
     * @return
     * @throws Exception
     */
    public String push(String openid) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        //这里简单起见我们每次都重新获取最新的access_token(实际开发中，应该在access_token快过期时再重新获取)
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();

        //拼接推送的模板
        WxMessVo wxMessVo = new WxMessVo();
        wxMessVo.setTouser(openid);   //用户的openid（要发送给哪个客户，通常这里应该动态传进来）
        wxMessVo.setTemplate_id("ra6yLfYhoD6l1OFvCWXRpJ5JMdDkUm0j8JB678veSxI");
        wxMessVo.setPage("pages/myself/myself/home");

        HashMap<String, TemplateData> m = new HashMap<>(3);
        m.put("name1",new TemplateData("小小小小心愿瓶"));
        m.put("thing2",new TemplateData("您有新的消息，请注意查收"));
        m.put("time3",new TemplateData(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date())));
        wxMessVo.setData(m);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, wxMessVo, String.class);

        return responseEntity.getBody();
    }

    /**
     * 获取小程序官方提供的access_token
     * @return
     * @throws Exception
     */
    public String getAccessToken() throws Exception{
        //使用springboot自带的RestTemplate来做网络请求
        RestTemplate restTemplate = new RestTemplate();

        //设置请求参数
        HashMap<String, String> params = new HashMap<>();
        params.put("APPID",appID);
        params.put("APPSECRET",appSecret);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);

        String body = responseEntity.getBody();

        JSONObject object = JSONObject.fromObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in:"+expires_in);

        //这里获取的Token有效时长是2个小时
        return Access_Token;
    }
}
