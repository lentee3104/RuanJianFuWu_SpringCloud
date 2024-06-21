package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yxy.cn.entity.BusinessEntity;

@FeignClient("customer-server")
public interface BusinessFeign {
    @GetMapping("/business/FindByBusinessId")
    ResponseEntity<BusinessEntity> findByBusinessId(@PathVariable("business_id")Integer business_id);
}