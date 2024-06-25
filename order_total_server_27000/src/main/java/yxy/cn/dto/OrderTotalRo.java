package yxy.cn.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderTotalRo {
    private Integer orderId;
    private double orderTotal;
    private Integer orderState;
    private String userCode;
    private Integer businessId;
    private String businessName;
    private DeliveryAddressRo deliveryAddressRo;
    private List<OrderDetailRo> orderDetailRos;
}
