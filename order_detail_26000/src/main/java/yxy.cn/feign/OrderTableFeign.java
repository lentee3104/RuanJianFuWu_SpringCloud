package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.entity.OrderTableEntity;

@FeignClient("order-table-server")
public interface OrderTableFeign {
    @PostMapping("/orderTable/FindByOrderTableId")
    public ResponseEntity<OrderTableEntity> findByOrderTableId(Integer order_table_id);
}