package yxy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway_Application_12000 {
    public static void main(String[] args){
        SpringApplication.run(Gateway_Application_12000.class, args);
    }
}
