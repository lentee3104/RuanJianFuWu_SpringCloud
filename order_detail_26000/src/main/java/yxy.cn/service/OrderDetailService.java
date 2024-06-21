package yxy.cn.service;

import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderDetailEntity;
import yxy.cn.entity.OrderTableEntity;
import yxy.cn.repository.IOrderDetailRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CommonsLog
public class OrderDetailService {
    @Resource
    private IOrderDetailRepository iOrderDetailRepository;

    public OrderDetailService(IOrderDetailRepository iOrderDetailRepository) {
        this.iOrderDetailRepository = iOrderDetailRepository;
    }

    public List<OrderDetailEntity> findByOrderTableId(Integer order_id){
        return iOrderDetailRepository.findByOrderTableId(order_id);
    }

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
}
