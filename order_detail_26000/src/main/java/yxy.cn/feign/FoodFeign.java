package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderTableEntity;

@FeignClient("order-table-server")
public interface FoodFeign {
    @PostMapping("/food/FindByFoodId")
    public ResponseEntity<FoodEntity> findByFoodId(Integer food_id);
}