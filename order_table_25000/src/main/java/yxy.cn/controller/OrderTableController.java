package yxy.cn.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import yxy.cn.dto.DeliveryAddressDTO;
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

@RestController
@CrossOrigin
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
    public ResponseEntity<OrderTableEntity> findByCustomerIdAndBusinessIdAndOrderState(String user_code, Integer business_id, Integer order_state){
        try{
            CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
            return new ResponseEntity<>(orderTableService.findByCustomerIdAndBusinessIdAndOrderState(customerEntity.getCustomerId(), business_id, order_state), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByOrderTableId")
    public ResponseEntity<OrderTableEntity> findByOrderTableId(Integer order_table_id){
        try{
            return new ResponseEntity<>(orderTableService.findByOrderTableId(order_table_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderTableEntity")
    public ResponseEntity<OrderTableDTO> save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, String user_code, Integer da_id){
        try{
            CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
            Integer customer_id = customerEntity.getCustomerId();
            OrderTableEntity orderTableEntity = orderTableService.save(order_id, order_data, order_state, order_total, business_id, customer_id, da_id);
            BusinessEntity businessEntity = businessFeign.findByBusinessId(business_id).getBody();
            DeliveryAddressDTO deliveryAddressDTO = deliveryAddressFeign.findByDeliveryAddressId(da_id).getBody();
            assert deliveryAddressDTO != null;
            DeliveryAddressEntity deliveryAddressEntity = DeliveryAddressEntity.builder()
                    .deliveryAddressId(da_id)
                    .contactName(deliveryAddressDTO.getContactName())
                    .contactSex(deliveryAddressDTO.getContactSex())
                    .contactTel(deliveryAddressDTO.getContactTel())
                    .address(deliveryAddressDTO.getAddress())
                    .customerId(deliveryAddressDTO.getCustomerEntity().customerId)
                    .build();

            OrderTableDTO orderTableDTO = OrderTableDTO.builder()
                    .orderTableId(orderTableEntity.getOrderTableId())
                    .orderTotal(orderTableEntity.getOrderTotal())
                    .orderState(orderTableEntity.getOrderState())
                    .customerEntity(customerEntity)
                    .businessEntity(businessEntity)
                    .deliveryAddressEntity(deliveryAddressEntity)
                    .build();
            return new ResponseEntity<>(orderTableDTO, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
