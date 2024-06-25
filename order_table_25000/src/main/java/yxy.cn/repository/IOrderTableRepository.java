package yxy.cn.repository;

import yxy.cn.entity.OrderTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderTableRepository extends JpaRepository<OrderTableEntity, Integer> {
    /*OrderTableEntity findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state);*/
    OrderTableEntity findByCustomerIdAndBusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state);
    OrderTableEntity save(OrderTableEntity orderTableEntity);
    OrderTableEntity findByOrderTableId(Integer order_id);
    List<OrderTableEntity> findByCustomerId(Integer customer_id);
}
