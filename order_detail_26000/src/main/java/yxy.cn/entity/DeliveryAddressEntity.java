package yxy.cn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressEntity {
    public Integer daId;
    public String contactName;
    public Integer contactSex;
    public String contactTel;
    public String address;
    private CustomerEntity userEntity;
}
