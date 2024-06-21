package yxy.cn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DeliveryAddress")
public class DeliveryAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer deliveryAddressId;
    public String contactName;
    public Integer contactSex;
    public String contactTel;
    public String address;
    public Integer customerId;
}
