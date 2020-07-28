package cn.pyj520.shop.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryVO {
    private Integer id;

    private String name;

    private Integer deep;

    private Integer parentId;

    private Integer status;

    private Date updateTime;

    private Date createTime;

}