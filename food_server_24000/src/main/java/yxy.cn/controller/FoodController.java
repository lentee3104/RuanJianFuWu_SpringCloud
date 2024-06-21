package yxy.cn.controller;

import yxy.cn.dto.FoodDTO;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.entity.FoodEntity;
import yxy.cn.feign.BusinessFeign;
import yxy.cn.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/food")
public class FoodController {
    @Resource
    private FoodService foodService;
    @Resource
    private BusinessFeign businessFeign;

    @PostMapping("/FindFoodListByBusinessId")
    public ResponseEntity<List<FoodDTO>> findByBusinessEntityBusinessId(Integer business_id){
        try{
            List<FoodEntity> foodEntityList = foodService.findByBusinessEntityBusinessId(business_id);
            BusinessEntity businessEntity = businessFeign.findByBusinessId(business_id).getBody();
            List<FoodDTO> foodDTOList = new ArrayList<>();
            for(FoodEntity foodEntity : foodEntityList){
                FoodDTO foodDTO = FoodDTO.builder()
                        .foodId(foodEntity.getFoodId())
                        .foodName(foodEntity.getFoodName())
                        .foodExplain(foodEntity.getFoodExplain())
                        .foodImg(foodEntity.getFoodImg())
                        .foodPrice(foodEntity.getFoodPrice())
                        .remarks(foodEntity.getRemarks())
                        .businessEntity(businessEntity)
                        .build();

                foodDTOList.add(foodDTO);
            }
            return new ResponseEntity<>(foodDTOList, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
