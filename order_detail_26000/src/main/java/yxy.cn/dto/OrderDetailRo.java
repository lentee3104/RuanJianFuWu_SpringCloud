package yxy.cn.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailRo {
    private Integer quantity;
    private Integer orderId;
    private Integer foodId;
    private String foodName;
}
