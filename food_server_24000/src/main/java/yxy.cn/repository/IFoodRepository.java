package yxy.cn.repository;

import yxy.cn.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findByBusinessId(Integer business_id);
    FoodEntity findByFoodId(Integer food_if);
}
