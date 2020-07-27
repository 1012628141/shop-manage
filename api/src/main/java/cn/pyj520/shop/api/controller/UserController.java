package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.po.UserInfo;
import cn.pyj520.shop.api.model.validate.Login;
import cn.pyj520.shop.api.model.vo.UserInfoVO;
import cn.pyj520.shop.api.service.UserService;
import cn.pyj520.shop.api.util.JsonResult;
import cn.pyj520.shop.api.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UserInfoVO userInfoVO = userService.login(userInfoDTO);
        if (NullUtil.isNullObject(userInfoVO)) {
            return JsonResult.toString(NetworkCode.ACCOUNT_PASSWORD_ERROR);
        }
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, userInfoVO);
    }


}