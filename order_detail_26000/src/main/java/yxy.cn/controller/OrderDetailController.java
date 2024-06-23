package yxy.cn.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import yxy.cn.dto.FoodDTO;
import yxy.cn.dto.OrderDetailDTO;
import yxy.cn.dto.OrderDetailRo;
import yxy.cn.dto.OrderTableDTO;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderDetailEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.feign.CustomerFeign;
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
@RefreshScope
@RequestMapping("/orderDetail")
@Tag(name = "OrderDetail", description = "订单号、订单中有什么食物、对应食品数量的关系")
public class OrderDetailController {
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private OrderTableFeign orderTableFeign;
    @Resource
    private FoodFeign foodFeign;
    @Resource
    private CustomerFeign customerFeign;

    @PostMapping("/FindByOrderTableId")
    public List<OrderDetailRo> findByOrderTableId(Integer order_id) {
        List<OrderDetailEntity> orderDetailEntityList = orderDetailService.findByOrderTableId(order_id);
        OrderTableEntity orderTableEntity = orderTableFeign.findByOrderTableId(order_id).getBody();
        List<OrderDetailRo> orderDetailRoList = new ArrayList<>();
        for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
            FoodEntity foodEntity = foodFeign.findByFoodId(orderDetailEntity.getFoodId()).getBody();
            OrderDetailRo orderDetailRo = OrderDetailRo.builder()
                    .orderId(order_id)
                    .quantity(orderDetailEntity.getQuantity())
                    .foodId(foodEntity.getFoodId())
                    .foodName(foodEntity.getFoodName())
                    .build();
            orderDetailRoList.add(orderDetailRo);
        }
        return orderDetailRoList;
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderDetailEntity")
    public ResponseEntity<OrderDetailDTO> save(Integer od_id, Integer quantity, Integer food_id, Integer order_id){
        OrderDetailEntity orderDetailEntity = orderDetailService.save(od_id, quantity, food_id, order_id);
        OrderTableEntity orderTableEntity = orderTableFeign.findByOrderTableId(order_id).getBody();
        CustomerEntity customerEntity = customerFeign.findByCustomerId(orderTableEntity.getCustomerId()).getBody();
        OrderTableDTO orderTableDTO = OrderTableDTO.builder()
                .orderId(order_id)
                .orderDate(orderTableEntity.getOrderDate())
                .orderTotal(orderTableEntity.getOrderTotal())
                .orderState(orderTableEntity.getOrderState())
                .userEntity(customerEntity)
                .build();

        FoodDTO foodDTO = foodFeign.findByFoodId(food_id).getBody();
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder()
                .odId(od_id)
                .quantity(quantity)
                .orderTableEntity(orderTableDTO)
                .foodEntity(foodDTO)
                .build();
        return new ResponseEntity<>(orderDetailDTO, HttpStatus.OK);

    }

}
