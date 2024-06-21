package yxy.cn.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.repository.IBusinessRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CommonsLog
@NoArgsConstructor
public class BusinessService {
    @Resource
    private IBusinessRepository iBusinessRepository;

    public BusinessService(IBusinessRepository iBusinessRepository){
        this.iBusinessRepository = iBusinessRepository;
    }

    public List<BusinessEntity> findByBusinessIdIsNotNull(){
        return iBusinessRepository.findByBusinessIdIsNotNull();
    }

    public List<BusinessEntity> findByOrderTypeId(Integer order_type_id){
        return iBusinessRepository.findByOrderTypeId(order_type_id);
    }

    public BusinessEntity findByBusinessId(Integer business_id){
        return iBusinessRepository.findByBusinessId(business_id);
    }
}
