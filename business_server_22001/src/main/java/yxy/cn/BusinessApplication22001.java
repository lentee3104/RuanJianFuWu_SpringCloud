package yxy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessApplication22001 {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication22001.class,args);
    }
}
