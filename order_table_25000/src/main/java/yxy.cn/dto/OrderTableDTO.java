package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.entity.DeliveryAddressEntity;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableDTO {
    public Integer orderId;
    public LocalDateTime orderDate = LocalDateTime.now();
    public Double orderTotal;
    public Integer orderState = 0;
    public CustomerEntity customerEntity;
    public BusinessEntity businessEntity;
    private DeliveryAddressDTO deliveryAddressDTO;
}
