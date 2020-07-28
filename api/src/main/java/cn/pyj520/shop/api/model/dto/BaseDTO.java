package cn.pyj520.shop.api.model.dto;

import com.github.pagehelper.PageHelper;
import lombok.*;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-03-19 10:35
 */
@Setter
@Getter
public class BaseDTO {

    private int pageSize;//成员变量基本数据类型int默认为0

    private int pageNum;

    private String keyword;

    private Integer userId;


    public void startPage() {
        PageHelper.startPage(pageNum,pageSize);
    }

}