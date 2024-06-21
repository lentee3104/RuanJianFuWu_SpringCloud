package yxy.cn.service;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxy.cn.repository.ICustomerRepository;
import yxy.cn.entity.CustomerEntity;

@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
    @Resource
    private ICustomerRepository iCustomerRepository;

    public CustomerEntity findByCustomerId(Integer customer_id){
        return iCustomerRepository.findByCustomerId(customer_id);
    }

    public CustomerEntity findByCustomerName(String customer_name){
        return iCustomerRepository.findByCustomerName(customer_name);
    }

}
