package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.dto.DeliveryAddressDTO;
import yxy.cn.dto.DeliveryAddressRo;

import java.util.List;

@FeignClient("delivery-address-server")
public interface DeliveryAddressFeign {
    @GetMapping("/address/FindByCustomerId")
    List<DeliveryAddressRo> findByCustomerId(Integer customer_id);
    @PostMapping("/address/FindByCustomerName")
    List<DeliveryAddressRo> findByCustomerName(String customer_name);
    @PostMapping("/address/FindByDeliveryAddressId")
    ResponseEntity<DeliveryAddressRo> findByDeliveryAddressId(Integer da_id);
}
