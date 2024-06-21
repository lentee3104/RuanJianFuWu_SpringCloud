package yxy.cn.repository;

import yxy.cn.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity, Integer>{
    List<DeliveryAddressEntity> findByCustomerId(Integer customer_id);
    DeliveryAddressEntity save(DeliveryAddressEntity deliveryAddressEntity);
    DeliveryAddressEntity deleteByDeliveryAddressId(Integer da_id);
    DeliveryAddressEntity findByDeliveryAddressId(Integer da_id);
}
