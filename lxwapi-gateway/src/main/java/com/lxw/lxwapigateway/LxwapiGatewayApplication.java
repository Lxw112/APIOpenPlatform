package com.lxw.lxwapigateway;

import com.lxw.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@EnableDubbo
public class LxwapiGatewayApplication {

    @DubboReference
    private DemoService demoService;
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(LxwapiGatewayApplication.class, args);
        LxwapiGatewayApplication application = context.getBean(LxwapiGatewayApplication.class);
        System.out.println(application.sayHello("lxw"));

    }

    public String sayHello(String name){
        return demoService.sayHello(name);
    }


}
