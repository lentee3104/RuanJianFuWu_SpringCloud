package yxy.cn.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import yxy.cn.entity.FoodEntity;
import yxy.cn.repository.IFoodRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CommonsLog
@Slf4j
public class FoodService {
    @Resource
    private final IFoodRepository iFoodRepository;

    public FoodService(IFoodRepository iFoodRepository) {
        this.iFoodRepository = iFoodRepository;
    }


    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByBusinessId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public List<FoodEntity> findByBusinessId(Integer business_id){
        return iFoodRepository.findByBusinessId(business_id);
    }

    public String fallbackFindByBusinessId(Integer business_id, Exception e) {
        return "External service is down. Please try again later.";
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackFindByFoodId")
    @RateLimiter(name = "myService")
    @Retry(name = "myService")
    public FoodEntity findByFoodId(Integer food_if){
        return iFoodRepository.findByFoodId(food_if);
    }

    public String fallbackFindByFoodId(Integer food_if, Exception e) {
        return "External service is down. Please try again later.";
    }
}
