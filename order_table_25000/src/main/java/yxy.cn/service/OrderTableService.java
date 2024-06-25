package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import yxy.cn.entity.*;
import yxy.cn.repository.IOrderTableRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@CommonsLog
@Slf4j
public class OrderTableService {
    @Resource
    private final IOrderTableRepository iOrderTableRepository;

    public OrderTableService(IOrderTableRepository iOrderTableRepository) {
        this.iOrderTableRepository = iOrderTableRepository;
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByCustomerIdAndBusinessIdAndOrderState")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public OrderTableEntity findByCustomerIdAndBusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state){
        return iOrderTableRepository.findByCustomerIdAndBusinessIdAndOrderState(customer_id, business_id, order_state);
    }

    public OrderTableEntity fallbackFindByCustomerIdAndBusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new OrderTableEntity();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByOrderTableId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public OrderTableEntity findByOrderTableId(Integer order_id){
        return iOrderTableRepository.findByOrderTableId(order_id);
    }

    public OrderTableEntity fallbackFindByOrderTableId(Integer order_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new OrderTableEntity();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackSave")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public Integer save(Integer order_id, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id){
        if(order_id == 0){
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessId(business_id)
                    .customerId(customer_id)
                    .deliveryAddressId(da_id)
                    .build();
            return iOrderTableRepository.save(newOrderTable).getOrderTableId();
        }else {
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderTableId(order_id)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessId(business_id)
                    .customerId(customer_id)
                    .deliveryAddressId(da_id)
                    .build();
            return iOrderTableRepository.save(newOrderTable).getOrderTableId();
        }
    }

    public Integer fallbackSave(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id, Exception e) {
        return -1;
    }


    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByCustomerId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<OrderTableEntity> findByCustomerId(Integer customer_id){
        return iOrderTableRepository.findByCustomerId(customer_id);
    }

    public List<OrderTableEntity> fallbackFindByCustomerId(Integer customer_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new ArrayList<>();
    }
}
