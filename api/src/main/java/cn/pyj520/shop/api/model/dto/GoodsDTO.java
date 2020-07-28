package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO extends BaseDTO {

    @NotNull(groups = {Update.class}, message = "商品名id为空")
    private Integer id;

    @NotNull(groups = {Insert.class, Update.class}, message = "商品名不能为空")
    private String name;

    @NotNull(groups = {Insert.class, Update.class}, message = "价格不能为空")
    private Float price;

    @NotNull(groups = {Insert.class, Update.class}, message = "数量不能为空")
    private Integer count;

    @NotNull(groups = {Insert.class, Update.class}, message = "描述不能为空")
    private String description;

    private String image;

    private Date updateTime;

    private Date createTime;
}