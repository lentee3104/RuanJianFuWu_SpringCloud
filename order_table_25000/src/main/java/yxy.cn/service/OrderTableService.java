package yxy.cn.service;

import yxy.cn.entity.*;
import yxy.cn.repository.IOrderTableRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Component
@CommonsLog
public class OrderTableService {
    @Resource
    private final IOrderTableRepository iOrderTableRepository;
    private final BusinessService businessService;
    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;

    public OrderTableService(IOrderTableRepository iOrderTableRepository, BusinessService businessService, DeliveryAddressService deliveryAddressService, UserService userService) {
        this.iOrderTableRepository = iOrderTableRepository;
        this.businessService = businessService;
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
    }

    public OrderTableEntity findByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState(String user_code, Integer business_id, Integer order_state){
        return iOrderTableRepository.findByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState(user_code, business_id, order_state);
    }

    public OrderTableEntity findByOrderId(Integer order_id){
        return iOrderTableRepository.findByOrderId(order_id);
    }

    public OrderTableEntity save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id){
        BusinessEntity businessEntity = businessService.findByBusinessId(business_id);
        Optional<UserEntity> userEntity = userService.getUserByCode(user_code);
        UserEntity user = userEntity.get();
        DeliveryAddressEntity deliveryAddressEntity = deliveryAddressService.findByDaId(da_id);

        if(order_id == 0){
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessId(business_id)
                    .customerId()
                    .deliveryAddressEntity(deliveryAddressEntity)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }else {
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderId(order_id)
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessEntity(businessEntity)
                    .userEntity(user)
                    .deliveryAddressEntity(deliveryAddressEntity)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }
    }
}
