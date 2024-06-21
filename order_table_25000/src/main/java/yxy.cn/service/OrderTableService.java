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

    public OrderTableService(IOrderTableRepository iOrderTableRepository) {
        this.iOrderTableRepository = iOrderTableRepository;
    }

    public OrderTableEntity findByCustomerIdAndBusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state){
        return iOrderTableRepository.findByCustomerIdAndBusinessIdAndOrderState(customer_id, business_id, order_state);
    }

    public OrderTableEntity findByOrderTableId(Integer order_id){
        return iOrderTableRepository.findByOrderTableId(order_id);
    }

    public OrderTableEntity save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id){
        if(order_id == 0){
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessId(business_id)
                    .customerId(customer_id)
                    .deliveryAddressId(da_id)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }else {
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderTableId(order_id)
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessId(business_id)
                    .customerId(customer_id)
                    .deliveryAddressId(da_id)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }
    }
}
