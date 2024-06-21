package yxy.cn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yxy.cn.entity.CustomerEntity;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByCustomerId(Integer customer_id);
    CustomerEntity findByCustomerName(String customer_name);
}

