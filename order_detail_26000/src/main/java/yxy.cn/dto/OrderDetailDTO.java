package yxy.cn.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yxy.cn.entity.FoodEntity;
import yxy.cn.entity.OrderDetailEntity;
import yxy.cn.entity.OrderTableEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    public Integer odId;
    public Integer quantity;
    public OrderTableDTO orderTableEntity;
    public FoodDTO foodEntity;
}
