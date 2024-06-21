package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yxy.cn.entity.CustomerEntity;

@FeignClient("customer-server")
public interface CustomerFeign {
    @GetMapping("/customer/FindByCustomerId/{customer_id}")
    ResponseEntity<CustomerEntity> findByCustomerId(@PathVariable("customer_id")Integer customer_id);
    @PostMapping("/customer/FindByCustomerName")
    ResponseEntity<CustomerEntity> findByCustomerName(@RequestParam("customer_name")String customer_name);
}
