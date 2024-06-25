package yxy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderTotalApplication27000 {
    public static void main(String[] args) {
        SpringApplication.run(OrderTotalApplication27000.class,args);
    }
}
