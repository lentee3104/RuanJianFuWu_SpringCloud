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
public class FoodEntity {
    public Integer foodId;
    public String foodName;
    public String foodExplain;
    public String foodImg;
    public Double foodPrice;
    public String remarks;
    public Integer businessId;
}
