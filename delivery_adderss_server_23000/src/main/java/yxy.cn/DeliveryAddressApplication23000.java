package yxy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name = "delivery-address-server")
public class DeliveryAddressApplication23000 {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryAddressApplication23000.class,args);
    }
}
