package yxy.cn.controller;

import jakarta.persistence.criteria.Order;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import yxy.cn.dto.DeliveryAddressRo;
import yxy.cn.dto.OrderDetailRo;
import yxy.cn.dto.OrderTableDTO;
import yxy.cn.dto.OrderTotalRo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.feign.*;

import java.util.ArrayList;
import java.util.List;


@RefreshScope
@RestController
@CrossOrigin
@RequestMapping("/orderDetail")
public class OrderTotalController {
    @Resource
    private BusinessFeign businessFeign;
    @Resource
    private CustomerFeign customerFeign;
    @Resource
    private DeliveryAddressFeign deliveryAddressFeign;
    @Resource
    private OrderDetailFeign orderDetailFeign;
    @Resource
    private OrderTableFeign orderTableFeign;

    @PostMapping("/FindOrderByUserCode")
    public List<OrderTotalRo> findByUserCode(String user_code){
        CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
        List<OrderTableDTO> orderTableDTOList = orderTableFeign.findByCustomerId(customerEntity.getCustomerId());
        List<OrderTotalRo> orderTotalRoList = new ArrayList<>();
        for(OrderTableDTO orderTableDTO : orderTableDTOList){
            DeliveryAddressRo deliveryAddressRo = deliveryAddressFeign.findByDeliveryAddressId(orderTableDTO.getDeliveryAddressEntity().getDeliveryAddressId()).getBody();
            List<OrderDetailRo> orderDetailRoList = orderDetailFeign.findByOrderTableId(orderTableDTO.getOrderId());
            OrderTotalRo orderTotalRo = OrderTotalRo.builder()
                    .orderId(orderTableDTO.getOrderId())
                    .orderTotal(orderTableDTO.getOrderTotal())
                    .orderState(orderTableDTO.getOrderState())
                    .userCode(customerEntity.getCustomerName())
                    .businessId(orderTableDTO.getBusinessEntity().getBusinessId())
                    .businessName(orderTableDTO.getBusinessEntity().getBusinessName())
                    .deliveryAddressRo(deliveryAddressRo)
                    .orderDetailRos(orderDetailRoList)
                    .build();
            orderTotalRoList.add(orderTotalRo);
        }
        return orderTotalRoList;
    }
}
