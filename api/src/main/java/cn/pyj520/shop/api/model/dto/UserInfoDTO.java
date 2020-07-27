package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Login;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 18:51
 */
@Data
@Builder
public class UserInfoDTO extends BaseDTO {

    private Integer id;

    private String name;

    private String email;

    private String phone;

    @NotNull(groups = Login.class, message = "用户名不能为空")
    private String account;

    @NotNull(groups = Login.class, message = "密码不能为空")
    private String password;


    private String address;

    private Integer gender;

    private Date birthday;

    private String avatorUrl;
}