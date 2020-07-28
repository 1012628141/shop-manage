package cn.pyj520.shop.api.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: zjy
 * @Date: 2020-07-28 18:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsVO {

    private Integer id;

    private String name;

    private Float price;

    private Integer count;

    private String description;

    private String image;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}