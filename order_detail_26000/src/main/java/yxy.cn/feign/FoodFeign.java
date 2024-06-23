package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.dto.FoodDTO;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderTableEntity;

@FeignClient("food-server")
public interface FoodFeign {
    @PostMapping("/food/FindByFoodId")
    public ResponseEntity<FoodDTO> findByFoodId(Integer food_id);
}