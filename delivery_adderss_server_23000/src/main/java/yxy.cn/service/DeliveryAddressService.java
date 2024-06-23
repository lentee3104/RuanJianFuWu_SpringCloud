package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import yxy.cn.entity.DeliveryAddressEntity;
import yxy.cn.repository.IDeliveryAddressRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxy.cn.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@CommonsLog
@Slf4j
public class DeliveryAddressService {

    /*List<DeliveryAddressEntity> findByCustomerEntity_CustomerId(Integer customer_id);
    DeliveryAddressEntity save(Integer daId, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id);
    DeliveryAddressEntity deleteByDaId(Integer da_id);*/

    @Resource
    private IDeliveryAddressRepository iDeliveryAddressRepository;

    public DeliveryAddressService(IDeliveryAddressRepository iDeliveryAddressRepository){
        this.iDeliveryAddressRepository = iDeliveryAddressRepository;
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindCustomerId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<DeliveryAddressEntity> findByCustomerId(Integer customer_id){
        return iDeliveryAddressRepository.findByCustomerId(customer_id);
    }

    public List<DeliveryAddressEntity> fallbackFindCustomerId(Integer customer_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindAddressId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public DeliveryAddressEntity findByDeliveryAddressId(Integer da_id){
        return iDeliveryAddressRepository.findByDeliveryAddressId(da_id);
    }

    public DeliveryAddressEntity fallbackFindAddressId(Integer da_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new DeliveryAddressEntity();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallback")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public DeliveryAddressEntity save(Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id){
        if(da_id == 0){
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .address(address)
                    .customerId(customer_id)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }else{
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .deliveryAddressId(da_id)
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .customerId(customer_id)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }
    }

    public DeliveryAddressEntity fallback(Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new DeliveryAddressEntity();
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackDeleteAddressId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public DeliveryAddressEntity deleteByDeliveryAddressId(Integer da_id){
        return iDeliveryAddressRepository.deleteByDeliveryAddressId(da_id);
    }

    public DeliveryAddressEntity fallbackDeleteAddressId(Integer da_id, Exception e) {
        log.error("Fallback method called due to exception: ", e);
        // 你可以返回一个默认的 CustomerEntity 对象或 null
        return new DeliveryAddressEntity();
    }
}
