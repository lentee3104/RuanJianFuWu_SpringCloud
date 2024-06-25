package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    public Integer customerId;
    public String customerName;
    public Integer customerSex = 1;
}
