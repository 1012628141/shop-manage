package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseDTO {

    @NotNull(groups = {Update.class}, message = "id不能为空")
    private Integer id;

    @NotNull(groups = {Update.class}, message = "订单号不能为空")
    private String orderNumber;

    @NotNull(groups = {Update.class}, message = "价格不能为空")
    private Float price;

    @NotNull(groups = {Update.class}, message = "支付状态不能为空")
    private Integer payStatus;

    @NotNull(groups = {Update.class}, message = "发货状态不能为空")
    private Integer sendStatus;

    private Date createTime;

    private Date updateTime;

    private Integer status;
}