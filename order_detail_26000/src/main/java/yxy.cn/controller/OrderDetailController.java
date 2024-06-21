package yxy.cn.controller;

import yxy.cn.dto.OrderDetailDTO;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderDetailEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.feign.FoodFeign;
import yxy.cn.feign.OrderTableFeign;
import yxy.cn.service.OrderDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orderDetail")
@Tag(name = "OrderDetail", description = "订单号、订单中有什么食物、对应食品数量的关系" )
public class OrderDetailController {
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private OrderTableFeign orderTableFeign;
    @Resource
    private FoodFeign foodFeign;

    @PostMapping("/FindByOrderTableId")
    public ResponseEntity<List<OrderDetailDTO>> findByOrderTableId(Integer order_id){
        try{
            List<OrderDetailEntity> orderDetailEntityList = orderDetailService.findByOrderTableId(order_id);
            OrderTableEntity orderTableEntity = orderTableFeign.findByOrderTableId(order_id).getBody();
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            for(OrderDetailEntity orderDetailEntity : orderDetailEntityList){
                FoodEntity foodEntity = foodFeign.findByFoodId(orderDetailEntity.getFoodId()).getBody();
                OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder()
                        .quantity(orderDetailEntity.getQuantity())
                        .orderTableEntity(orderTableEntity)
                        .foodEntity(foodEntity)
                        .build();
                orderDetailDTOList.add(orderDetailDTO);
            }
            return new ResponseEntity<>(orderDetailDTOList, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderDetailEntity")
    public ResponseEntity<OrderDetailEntity> save(Integer od_id, Integer quantity, Integer food_id, Integer order_id){
        return new ResponseEntity<>(orderDetailService.save(od_id, quantity, food_id, order_id), HttpStatus.OK);

    }
}
