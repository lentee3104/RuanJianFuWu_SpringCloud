package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.dto.OrderDetailDTO;
import yxy.cn.dto.OrderDetailRo;
import yxy.cn.entity.OrderTableEntity;

import java.util.List;

@FeignClient("order-detail-server")
public interface OrderDetailFeign {
    @PostMapping("/orderDetail/FindByOrderTableId")
    public List<OrderDetailRo> findByOrderTableId(Integer order_id);

    @PostMapping("/orderDetail/SaveOrderDetailEntity")
    public ResponseEntity<OrderDetailDTO> save(Integer od_id, Integer quantity, Integer food_id, Integer order_id);

}