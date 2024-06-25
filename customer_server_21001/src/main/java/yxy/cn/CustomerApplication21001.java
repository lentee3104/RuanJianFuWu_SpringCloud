package yxy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import yxy.cn.rule.CustomThreeTimeLoadBalancerConf;

@SpringBootApplication
@EnableFeignClients
public class CustomerApplication21001 {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication21001.class,args);
    }
}
