package com.lxw.lxwinterface.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lxw.lxwclientsdk.utils.SignUtils;


import com.lxw.lxwinterface.entity.User;
import com.lxw.lxwclientsdk.model.UserExample;
import com.lxw.lxwinterface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称API
 */
@RestController()
@RequestMapping("/name")
public class NameController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getNameByGet(String name){
        return "Get 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "POST 你的名字是" + name;
    }
    @PostMapping("/user")
    public String getNameByPost(@RequestBody UserExample user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestampStr = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        //String body = request.getHeader("body");//因为会乱码，所以直接使用body（user）
        // todo 实际情况应该是去数据库中查是否已分配给用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccessKey,accessKey);
        User user1 = userService.getOne(lambdaQueryWrapper);
        if (user1==null){
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 10000){
            throw new RuntimeException("无权限");
        }
        // todo 需要验证时间和当前时间不能超过5分钟
        try {
            long timestamp = Long.parseLong(timestampStr); // 将字符串转换为 long 类型
            long currentTime = System.currentTimeMillis(); // 获取当前时间戳
            if (Math.abs(currentTime - timestamp) > 300_000){// 计算时间差是否小于等于 5 分钟
                throw new RuntimeException("无权限");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("无权限");
        }
        // todo 实际情况是从数据库中查出 secretKey
        String serverSign = SignUtils.genSign(JSONUtil.toJsonStr(user), user1.getSecretKey());
        if (!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }
         String result = "POST 你的名字是" + user.getName();
        return result;
    }


}
