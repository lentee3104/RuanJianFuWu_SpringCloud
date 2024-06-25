package yxy.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableEntity {
    public Integer orderTableId;
    public LocalDateTime orderDate = LocalDateTime.now();
    public Double orderTotal;
    public Integer orderState = 0;
    public Integer customerId;
    public Integer businessId;
    private Integer deliveryAddressId;
}
