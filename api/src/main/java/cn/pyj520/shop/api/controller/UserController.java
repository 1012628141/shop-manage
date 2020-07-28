package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.constants.StatusEnum;
import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.po.UserInfo;
import cn.pyj520.shop.api.model.validate.Delete;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Login;
import cn.pyj520.shop.api.model.validate.Update;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import cn.pyj520.shop.api.model.vo.UserInfoVO;
import cn.pyj520.shop.api.service.UserService;
import cn.pyj520.shop.api.util.JsonResult;
import cn.pyj520.shop.api.util.NullUtil;
import cn.pyj520.shop.api.util.OathHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 16:02
 */

@RestController
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * @Author: zjy on 2020-07-27 16:03
     * @Description:用户登陆
     */
    @PostMapping(value = "login")
    public String login(@RequestBody @Validated(value = Login.class) UserInfoDTO userInfoDTO) {
        UserInfoVO userInfoByAccount = userService.getUserInfoByAccount(userInfoDTO.getAccount());
        if (NullUtil.isNullObject(userInfoByAccount)) {
            return JsonResult.toString(NetworkCode.ACCOUNT_NO_REGISTER);
        }
        if (userInfoByAccount.getStatus() == StatusEnum.FORBIDDEN.getCode()) {
            return JsonResult.toString(NetworkCode.ACCOUNT_NOT_ACTIVED);
        }
        String password = userInfoDTO.passwordHelper().getPassword(userInfoDTO.getPassword());
        //判断账号是否存在和密码是否匹配
        if (!userInfoByAccount.getPassword().equals(password)) {
            return JsonResult.toString(NetworkCode.ACCOUNT_PASSWORD_ERROR);
        }
        UserInfoVO userInfoVO = userService.login(userInfoDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, userInfoVO);
    }

    /**
     * @Author: zjy on 2020-07-28 10:14
     * @Description:获取用户列表
     */
    @PostMapping(value = "list")
    public String listPermission(@RequestBody UserInfoDTO userInfoDTO) {
        //设置分页，使用mybatis插件
        userInfoDTO.startPage();
        //获取当前登陆用户id
        userInfoDTO.setUserId(OathHelper.getUserId());
        List<UserInfoVO> listUserInfo = userService.listUserInfo(userInfoDTO);
        PageInfo pageInfo = new PageInfo(listUserInfo);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }


    /**
     * @Author: zjy on 2020-07-28 15:12
     * @Description:添加用户
     */
    @PostMapping(value = "add")
    public String addUser(@RequestBody @Validated(Insert.class) UserInfoDTO userInfoDTO) {
        NetworkCode code = userService.registerUser(userInfoDTO);
        return JsonResult.toString(code);
    }

    /**
     * @Author: zjy on 2020-07-28 15:12
     * @Description:修改用户
     */
    @PostMapping(value = "update")
    public String updateUser(@RequestBody @Validated(Update.class) UserInfoDTO userInfoDTO) {
        NetworkCode code = userService.updateUser(userInfoDTO);
        return JsonResult.toString(code);
    }


    /**
     * @Author: zjy on 2020-07-28 16:18
     * @Description:删除用户
     */
    @PostMapping(value = "delete")
    public String deleteUser(@RequestBody @Validated(Delete.class) UserInfoDTO userInfoDTO) {
        userService.deleteUser(userInfoDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }
}