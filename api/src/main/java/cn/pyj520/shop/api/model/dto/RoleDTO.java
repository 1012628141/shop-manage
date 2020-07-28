package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO extends BaseDTO {

    @NotNull(groups = {Update.class}, message = "角色id不能为空")
    private Integer id;

    @NotNull(groups = {Insert.class}, message = "角色名不能为空")
    private String name;

    private String description;

    private Integer parentId;

    private List<Integer> permissionIds;


}