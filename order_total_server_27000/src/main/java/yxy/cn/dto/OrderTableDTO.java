package yxy.cn.dto;

import lombok.Builder;
import lombok.Data;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.entity.CustomerEntity;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderTableDTO {
    public Integer orderId;
    public LocalDateTime orderDate = LocalDateTime.now();
    public Double orderTotal;
    public Integer orderState = 0;
    private CustomerEntity userEntity;
    private BusinessEntity businessEntity;
    private DeliveryAddressDTO deliveryAddressEntity;
}
