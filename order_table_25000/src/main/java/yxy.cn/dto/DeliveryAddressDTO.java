package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yxy.cn.entity.CustomerEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDTO {
    public Integer daId;
    public String contactName;
    public Integer contactSex;
    public String contactTel;
    public String address;
    public CustomerEntity userEntity;
}
