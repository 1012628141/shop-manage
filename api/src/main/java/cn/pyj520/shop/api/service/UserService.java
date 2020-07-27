package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.vo.UserInfoVO;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 16:04
 */
public interface UserService {
    UserInfoVO login(UserInfoDTO userInfo);

    UserInfoVO getUserInfoByAccount(String account);
}
