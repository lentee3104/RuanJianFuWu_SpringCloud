package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yxy.cn.dto.DeliveryAddressDTO;
import yxy.cn.entity.BusinessEntity;

import java.util.List;

@FeignClient("delivery-address-server")
public interface DeliveryAddressFeign {
    @GetMapping("/address/FindByCustomerId")
    ResponseEntity<List<DeliveryAddressDTO>> findByCustomerId(Integer customer_id);
    @PostMapping("/address/FindByCustomerName")
    ResponseEntity<List<DeliveryAddressDTO>> findByCustomerName(String customer_name);
    @PostMapping("/address/FindByDeliveryAddressId")
    ResponseEntity<DeliveryAddressDTO> findByDeliveryAddressId(Integer da_id);
}
