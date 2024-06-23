package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxy.cn.repository.ICustomerRepository;
import yxy.cn.entity.CustomerEntity;

@Slf4j
@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
    @Resource
    private ICustomerRepository iCustomerRepository;

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByCustomerId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public CustomerEntity findByCustomerId(Integer customer_id){
        return iCustomerRepository.findByCustomerId(customer_id);
    }

    // Fallback 方法
    public CustomerEntity fallbackFindByCustomerId(Integer customer_id, Throwable throwable) {
        log.error("Fallback method called due to exception: ", throwable);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new CustomerEntity();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByCustomerName")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public CustomerEntity findByCustomerName(String customer_name){
        return iCustomerRepository.findByCustomerName(customer_name);
    }

    // Fallback 方法
    public CustomerEntity fallbackFindByCustomerName(String customer_name, Throwable throwable) {
        log.error("Fallback method called due to exception: ", throwable);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new CustomerEntity();
    }


    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByCustomerNameAndPassword")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public CustomerEntity findByCustomerNameAndPassword(String customer_name, String password){
        return iCustomerRepository.findByCustomerNameAndPassword(customer_name, password);
    }

    // Fallback 方法
    public CustomerEntity fallbackFindByCustomerNameAndPassword(String customer_name, String password, Throwable throwable) {
        log.error("Fallback method called due to exception: ", throwable);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new CustomerEntity();
    }

}
