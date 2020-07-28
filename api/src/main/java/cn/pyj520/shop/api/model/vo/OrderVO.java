package cn.pyj520.shop.api.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {

    private Integer id;

    private String orderNumber;

    private Float price;

    private Integer payStatus;

    private Integer sendStatus;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date updateTime;

    private Integer status;
}