package yxy.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yxy.cn.entity.BusinessEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    public Integer foodId;
    public String foodName;
    public String foodExplain;
    public String foodImg;
    public Double foodPrice;
    public String remarks;
    public BusinessEntity businessEntity;
}
