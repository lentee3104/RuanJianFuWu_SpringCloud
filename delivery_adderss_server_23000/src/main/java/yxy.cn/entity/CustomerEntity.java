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
public class CustomerEntity {
    public Integer customerId;
    public String password;
    public String customerName;
    public Integer customerSex = 1;

}
