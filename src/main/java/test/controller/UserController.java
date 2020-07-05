package test.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.User;
import test.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/user",produces="application/json;charset=UTF-8")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @PostMapping(path = "/login")
    public String login(String wx_id){
        System.out.println(wx_id);
        HashMap map = new HashMap();
        map.put("wx_id",wx_id);
        if (userService.findUser(map).isEmpty()){
            User user = new User();
            user.setWx_id(wx_id);
            userService.addUser(user);
            return JSONObject.toJSONString("第一次登陆");
        }else {
            return JSONObject.toJSONString("已经存在登陆状态");
        }
    }

}
