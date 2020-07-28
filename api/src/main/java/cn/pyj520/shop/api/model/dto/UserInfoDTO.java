package cn.pyj520.shop.api.model.dto;

import cn.pyj520.shop.api.model.validate.Delete;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Login;
import cn.pyj520.shop.api.model.validate.Update;
import cn.pyj520.shop.api.util.MD5Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 18:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends BaseDTO {

    @NotNull(groups = {Update.class, Delete.class}, message = "用户id不能为空")
    private Integer id;

    @NotNull(groups = {Insert.class, Update.class}, message = "姓名不能为空")
    private String name;

    @NotNull(groups = {Insert.class, Update.class}, message = "邮箱不能为空")
    private String email;

    @NotNull(groups = {Insert.class, Update.class}, message = "手机不能为空")
    private String phone;

    @NotNull(groups = Login.class, message = "用户名不能为空")
    private String account;

    @NotNull(groups = Login.class, message = "密码不能为空")
    private String password;


    private String address;

    private Integer gender;

    private Date birthday;

    private String avatorUrl;

    private Integer status;

    @NotNull(groups = {Insert.class, Update.class}, message = "用户角色不能为空")
    private List<Integer> roleIds;


    public class PasswordHelper {

        public PasswordHelper() {
        }

        public String getPassword(String pass) {
            password = MD5Util.MD5Encode(pass);
            return password;
        }
    }

    public PasswordHelper passwordHelper() {
        return new PasswordHelper();

    }


}