package yxy.cn.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryAddressRo {
    private Integer daId;
    private String contactName;
    private Integer contactSex;
    private String contactTel;
    private String address;
    private String userCode;
}
