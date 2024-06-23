package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.repository.IBusinessRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@CommonsLog
@NoArgsConstructor
@Slf4j
public class BusinessService {
    @Resource
    private IBusinessRepository iBusinessRepository;

    public BusinessService(IBusinessRepository iBusinessRepository){
        this.iBusinessRepository = iBusinessRepository;
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallback")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<BusinessEntity> findByBusinessIdIsNotNull(){
        return iBusinessRepository.findByBusinessIdIsNotNull();
    }

    public List<BusinessEntity> fallback(Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackTypeId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<BusinessEntity> findByOrderTypeId(Integer order_type_id){
        return iBusinessRepository.findByOrderTypeId(order_type_id);
    }

    public List<BusinessEntity> fallbackTypeId(Integer order_type_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackBusinessId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public BusinessEntity findByBusinessId(Integer business_id){
        return iBusinessRepository.findByBusinessId(business_id);
    }
    public BusinessEntity fallbackBusinessId(Integer business_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new BusinessEntity();
    }
}
