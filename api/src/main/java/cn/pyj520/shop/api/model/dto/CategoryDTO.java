package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Delete;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO extends BaseDTO {

    @NotNull(groups = {Update.class, Delete.class}, message = "分类ID不能为空")
    private Integer id;

    @NotNull(groups = {Insert.class, Update.class}, message = "分类名称不能为空")
    private String name;

    private Integer deep;

    @NotNull(groups = {Insert.class}, message = "分类名称不能为空")
    private Integer parentId;

    @NotNull(groups = {Update.class}, message = "分类状态不能为空")
    private Integer status;

    private Date updateTime;

    private Date createTime;

}