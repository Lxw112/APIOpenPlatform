package com.lxw.lxwclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.lxw.lxwclientsdk.model.UserExample;
import com.lxw.lxwclientsdk.utils.SignUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 */
public class LxwApiClient {

    private String accessKey;
    private String secretKey;

    public static final String GATEWAY_HOST = "http://localhost:8090";

    public LxwApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST +"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost( String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST +"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    private Map<String,String> getHeaderMap(String body) throws UnsupportedEncodingException {
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        //一定不能直接发送
        //hashMap.put("secretKey","abcdefg");
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", URLEncoder.encode(body,"utf-8"));
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body,secretKey));
        return hashMap;
    }
    public String getNameByPost( UserExample userExample) throws UnsupportedEncodingException {
        String json = JSONUtil.toJsonStr(userExample);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .body(json)
                .addHeaders(getHeaderMap(json))
                .header("Content-Type", "application/json; charset=UTF-8") // 明确指定编码
                .header("Accept-Charset", "UTF-8") // 确保服务器以 UTF-8 解析
                .execute();
        System.out.println(httpResponse.getStatus());
        String body = httpResponse.body();
        System.out.println(body);
        return body;
    }
}
