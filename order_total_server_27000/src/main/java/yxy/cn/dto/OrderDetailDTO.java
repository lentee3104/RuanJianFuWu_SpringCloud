package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
