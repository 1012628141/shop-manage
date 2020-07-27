package cn.pyj520.shop.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVO {

    private Integer id;

    private Integer parentId;

    private String name;

    private String description;

    private Integer deep;
}