package yxy.cn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import yxy.cn.dto.OrderTableDTO;
import yxy.cn.entity.OrderTableEntity;

import java.util.List;

@FeignClient("order-table-server")
public interface OrderTableFeign {
    @PostMapping("/orderTable/FindByOrderTableId")
    public ResponseEntity<OrderTableDTO> findByOrderTableId(Integer order_table_id);
    @PostMapping("/orderTable/FindByCustomerId")
    public List<OrderTableDTO> findByCustomerId(Integer customer_id);
}