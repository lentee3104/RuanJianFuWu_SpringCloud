package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yxy.cn.entity.BusinessEntity;

@FeignClient("business-server")
public interface BusinessFeign {
    @GetMapping("/business/ListAllBusiness")
    ResponseEntity<BusinessEntity> findByBusinessIdIsNotNull();
    @PostMapping("/business/ListBusinessByOrderTypeId")
    ResponseEntity<BusinessEntity> findByOrderTypeId(@RequestParam("order_type_id")Integer order_type_id);
    @PostMapping("/business/FindByBusinessId")
    ResponseEntity<BusinessEntity> findByBusinessId(@RequestParam("business_id")Integer business_id);
}
