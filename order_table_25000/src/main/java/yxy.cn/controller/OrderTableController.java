package yxy.cn.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import yxy.cn.dto.DeliveryAddressDTO;
import yxy.cn.dto.DeliveryAddressRo;
import yxy.cn.dto.OrderTableDTO;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.entity.DeliveryAddressEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.feign.BusinessFeign;
import yxy.cn.feign.CustomerFeign;
import yxy.cn.feign.DeliveryAddressFeign;
import yxy.cn.service.OrderTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/orderTable")
@Tag(name = "OrderTable", description = "商品总订单部分" )
public class OrderTableController {
    @Resource
    private OrderTableService orderTableService;
    @Resource
    private BusinessFeign businessFeign;
    @Resource
    private CustomerFeign customerFeign;
    @Resource
    private DeliveryAddressFeign deliveryAddressFeign;

    @PostMapping("/FindByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState")
    public ResponseEntity<OrderTableDTO> findByCustomerIdAndBusinessIdAndOrderState(String user_code, Integer business_id, Integer order_state){
        try{
            CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
            OrderTableEntity orderTableEntity = orderTableService.findByCustomerIdAndBusinessIdAndOrderState(customerEntity.getCustomerId(), business_id, order_state);
            BusinessEntity businessEntity = businessFeign.findByBusinessId(orderTableEntity.getBusinessId()).getBody();
            DeliveryAddressRo deliveryAddressRo = deliveryAddressFeign.findByDeliveryAddressId(orderTableEntity.getDeliveryAddressId()).getBody();
            DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                    .daId(deliveryAddressRo.getDaId())
                    .contactName(deliveryAddressRo.getContactName())
                    .contactTel(deliveryAddressRo.getContactTel())
                    .contactSex(deliveryAddressRo.getContactSex())
                    .address(deliveryAddressRo.getAddress())
                    .userEntity(customerEntity)
                    .build();

            OrderTableDTO orderTableDTO = OrderTableDTO.builder()
                    .orderId(orderTableEntity.getOrderTableId())
                    .orderDate(orderTableEntity.getOrderDate())
                    .orderTotal(orderTableEntity.getOrderTotal())
                    .orderState(orderTableEntity.getOrderState())
                    .customerEntity(customerEntity)
                    .businessEntity(businessEntity)
                    .deliveryAddressDTO(deliveryAddressDTO)
                    .build();
            return new ResponseEntity<>(orderTableDTO, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByOrderTableId")
    public ResponseEntity<OrderTableDTO> findByOrderTableId(Integer order_table_id){
        try{
            OrderTableEntity orderTableEntity = orderTableService.findByOrderTableId(order_table_id);
            BusinessEntity businessEntity = businessFeign.findByBusinessId(orderTableEntity.getBusinessId()).getBody();
            CustomerEntity customerEntity = customerFeign.findByCustomerId(orderTableEntity.getCustomerId()).getBody();
            DeliveryAddressRo deliveryAddressRo = deliveryAddressFeign.findByDeliveryAddressId(orderTableEntity.getDeliveryAddressId()).getBody();
            DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                    .daId(deliveryAddressRo.getDaId())
                    .contactName(deliveryAddressRo.getContactName())
                    .contactTel(deliveryAddressRo.getContactTel())
                    .contactSex(deliveryAddressRo.getContactSex())
                    .address(deliveryAddressRo.getAddress())
                    .userEntity(customerEntity)
                    .build();

            OrderTableDTO orderTableDTO = OrderTableDTO.builder()
                    .orderId(order_table_id)
                    .orderDate(orderTableEntity.getOrderDate())
                    .orderTotal(orderTableEntity.getOrderTotal())
                    .orderState(orderTableEntity.getOrderState())
                    .customerEntity(customerEntity)
                    .businessEntity(businessEntity)
                    .deliveryAddressDTO(deliveryAddressDTO)
                    .build();
            return new ResponseEntity<>(orderTableDTO, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByCustomerId")
    public List<OrderTableDTO> findByCustomerId(Integer customer_id){
        List<OrderTableEntity> orderTableEntityList = orderTableService.findByCustomerId(customer_id);
        CustomerEntity customerEntity = customerFeign.findByCustomerId(customer_id).getBody();
        List<OrderTableDTO> orderTableDTOList = new ArrayList<>();
        for(OrderTableEntity orderTableEntity : orderTableEntityList){
            BusinessEntity businessEntity = businessFeign.findByBusinessId(orderTableEntity.getBusinessId()).getBody();
            DeliveryAddressRo deliveryAddressRo = deliveryAddressFeign.findByDeliveryAddressId(orderTableEntity.getDeliveryAddressId()).getBody();
            DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                    .daId(deliveryAddressRo.getDaId())
                    .contactName(deliveryAddressRo.getContactName())
                    .contactSex(deliveryAddressRo.getContactSex())
                    .contactTel(deliveryAddressRo.getContactTel())
                    .address(deliveryAddressRo.getAddress())
                    .userEntity(customerEntity)
                    .build();
            OrderTableDTO orderTableDTO = OrderTableDTO.builder()
                    .orderId(orderTableEntity.getOrderTableId())
                    .orderDate(orderTableEntity.getOrderDate())
                    .orderTotal(orderTableEntity.getOrderTotal())
                    .orderState(orderTableEntity.getOrderState())
                    .customerEntity(customerEntity)
                    .businessEntity(businessEntity)
                    .deliveryAddressDTO(deliveryAddressDTO)
                    .build();
            orderTableDTOList.add(orderTableDTO);
        }

        return orderTableDTOList;
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderTableEntity")
    public int save(Integer order_id, Integer order_state, Double order_total, Integer business_id, String user_code, Integer da_id){
        CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
        return orderTableService.save(order_id, order_state, order_total, business_id, customerEntity.getCustomerId(), da_id);
    }
}
