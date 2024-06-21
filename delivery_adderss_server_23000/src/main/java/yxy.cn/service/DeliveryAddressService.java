package yxy.cn.service;

import yxy.cn.entity.DeliveryAddressEntity;
import yxy.cn.repository.IDeliveryAddressRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxy.cn.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Service
@Component
@CommonsLog
public class DeliveryAddressService {

    /*List<DeliveryAddressEntity> findByCustomerEntity_CustomerId(Integer customer_id);
    DeliveryAddressEntity save(Integer daId, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id);
    DeliveryAddressEntity deleteByDaId(Integer da_id);*/

    @Resource
    private IDeliveryAddressRepository iDeliveryAddressRepository;

    public DeliveryAddressService(IDeliveryAddressRepository iDeliveryAddressRepository){
        this.iDeliveryAddressRepository = iDeliveryAddressRepository;
    }

    public List<DeliveryAddressEntity> findByCustomerId(Integer customer_id){
        return iDeliveryAddressRepository.findByCustomerId(customer_id);
    }

    public DeliveryAddressEntity findByDeliveryAddressId(Integer da_id){
        return iDeliveryAddressRepository.findByDeliveryAddressId(da_id);
    }

    public DeliveryAddressEntity save(Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id){
        if(da_id == 0){
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .address(address)
                    .customerId(customer_id)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }else{
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .deliveryAddressId(da_id)
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .customerId(customer_id)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }
    }

    public DeliveryAddressEntity deleteByDeliveryAddressId(Integer da_id){
        return iDeliveryAddressRepository.deleteByDeliveryAddressId(da_id);
    }
}
