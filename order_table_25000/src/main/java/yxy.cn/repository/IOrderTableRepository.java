package yxy.cn.repository;

import yxy.cn.entity.OrderTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderTableRepository extends JpaRepository<OrderTableEntity, Integer> {
    /*OrderTableEntity findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state);*/
    OrderTableEntity findByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState(String user_code, Integer business_id, Integer order_state);
    OrderTableEntity save(OrderTableEntity orderTableEntity);
    OrderTableEntity findByOrderId(Integer order_id);
}
