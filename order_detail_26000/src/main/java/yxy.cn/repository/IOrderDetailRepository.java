package yxy.cn.repository;

import yxy.cn.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findByOrderTableId(Integer order_id);
    OrderDetailEntity save(OrderDetailEntity orderDetailEntity);
}
