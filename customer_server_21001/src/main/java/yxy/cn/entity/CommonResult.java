package yxy.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {//这是对返回的user对象进行封装
    private Integer code;

    private String message;

    private T result;//返回的对象就是一个泛型
}
