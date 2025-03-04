package com.lxw.lxwinterface;

import com.lxw.lxwclientsdk.client.LxwApiClient;
import com.lxw.lxwclientsdk.model.UserExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;


@SpringBootTest
class LxwInterfaceApplicationTests {

@Resource
private LxwApiClient lxwApiClient;
    @Test
    void contextLoads() {
        String result = lxwApiClient.getNameByGet("lxw");
        UserExample user = new UserExample();
        user.setName("ppp");
        String nameByPost = lxwApiClient.getNameByPost(user);
        System.out.println(result);
        System.out.println(nameByPost);

    }

}
