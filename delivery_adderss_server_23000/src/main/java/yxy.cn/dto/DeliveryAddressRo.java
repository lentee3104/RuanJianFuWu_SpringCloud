package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressRo {
    public Integer daId;
    public String contactName;
    public Integer contactSex;
    public String contactTel;
    public String address;
    public String userCode;
}
