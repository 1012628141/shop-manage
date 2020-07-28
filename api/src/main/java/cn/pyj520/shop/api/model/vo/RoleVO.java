package cn.pyj520.shop.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleVO {

    private Integer id;

    private String name;

    private String description;

    private Integer parentId;
}