package cn.pyj520.shop.api.model.vo;

import cn.pyj520.shop.api.model.po.Permission;
import cn.pyj520.shop.api.model.validate.Login;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 17:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String account;

    @JSONField(serialize = false)
    private String password;

    private String address;

    private Integer gender;

    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    private String avatorUrl;

    private Integer status;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<PermissionVO> permissions;

    private String accessToken;

}