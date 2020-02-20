package com.hust.software.wishbottle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hust.software.wishbottle.pojo.User;
import com.hust.software.wishbottle.service.UserService;
import com.hust.software.wishbottle.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //小程序 appid
    private String appID = "wx9860a856c379df42";
    //小程序 appsecret
    private String appSecret = "eeb987f72c854e7d86c8545ced1d2d38";

    /**
     * 接收微信小程序发送的code，请求微信服务器，获取openid和session_key
     * @param code
     * @return
     */
    @RequestMapping("/userLogin")
    public Integer WxLogin(@RequestParam(value = "code") String code) throws Exception{
        String result = "";
        Integer userid = 0;
        try {
            result = HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session" +
                    "?appid=" + appID +
                    "&secret=" + appSecret +
                    "&js_code=" + code +
                    "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //解析从微信服务器上获取的json字符串
        JSONObject jsonObj = JSONObject.fromObject(result);
//        System.out.println("服务器返回的json字符串为："+jsonObj.toString());

        //获取openid的值，此处也可以得到session_key的值
        String openid = jsonObj.getString("openid");
//        System.out.println("openid为："+openid);

        //根据openid查询用户是否存在
        User user = userService.isRegister(openid);
//        System.out.println(user);
        if(null != user){
            //如果该openid对应的用户存在，则取出该用户的userid
            userid = user.getUserId();
        }else{
            //如果对应的user不存在，说明是新用户，则将其插入到数据库中
            User newUser = new User();
            newUser.setUserOpenid(openid);
            userService.add(newUser);

            //然后返回该用户的userid
            userid = userService.isRegister(openid).getUserId();
        }

        //这里为了安全起见，应该用openid和session_key返回一个token给小程序前端，
        //但是在这里为了方便测试直接将用户的userid返回
        return userid;
    }

    //新增用户(小程序前端并不会发送这个请求)
    @PostMapping("/user")
    public String addUser(@RequestBody User user) throws Exception{
        userService.add(user);
        System.out.println(user.toString());
        return "Add success";
    }

    //修改用户信息
    @PutMapping("/user/{id}")
    public String updateUser(@RequestBody User user) throws Exception{
        userService.update(user);
        return "Update success";
    }

    //根据用户id获取用户信息
    @GetMapping("/user/{id}")
    public User getUserInfoById(@PathVariable("id") int userId) throws Exception{
        User user = userService.getUserInfoById(userId);
        return user;
    }
}
