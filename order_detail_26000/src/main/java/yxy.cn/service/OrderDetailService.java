package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderDetailEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.repository.IOrderDetailRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@CommonsLog
@Slf4j
public class OrderDetailService {
    @Resource
    private IOrderDetailRepository iOrderDetailRepository;

    public OrderDetailService(IOrderDetailRepository iOrderDetailRepository) {
        this.iOrderDetailRepository = iOrderDetailRepository;
    }


    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByOrderTableId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<OrderDetailEntity> findByOrderTableId(Integer order_id){
        return iOrderDetailRepository.findByOrderTableId(order_id);
    }

    public List<OrderDetailEntity> fallbackFindByOrderTableId(Integer order_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackSave")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public OrderDetailEntity save(Integer od_id, Integer quantity, Integer food_id, Integer order_id){

        if(od_id == 0){
            OrderDetailEntity newOrderDetailEntity = OrderDetailEntity.builder()
                    .quantity(quantity)
                    .foodId(food_id)
                    .orderTableId(order_id)
                    .build();
            return iOrderDetailRepository.save(newOrderDetailEntity);
        }else {
            OrderDetailEntity newOrderDetailEntity = OrderDetailEntity.builder()
                    .odId(od_id)
                    .quantity(quantity)
                    .foodId(food_id)
                    .orderTableId(order_id)
                    .build();
            return iOrderDetailRepository.save(newOrderDetailEntity);
        }
    }

    public OrderDetailEntity fallbackSave(Integer od_id, Integer quantity, Integer food_id, Integer order_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new OrderDetailEntity();
    }
}
