package yxy.cn.service;

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
public class FoodService {
    @Resource
    private final IFoodRepository iFoodRepository;

    public FoodService(IFoodRepository iFoodRepository) {
        this.iFoodRepository = iFoodRepository;
    }

    public List<FoodEntity> findByBusinessEntityBusinessId(Integer business_id){
        return iFoodRepository.findByBusinessEntityBusinessId(business_id);
    }

    public FoodEntity findByFoodId(Integer food_if){
        return iFoodRepository.findByFoodId(food_if);
    }
}
