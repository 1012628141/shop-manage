package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.vo.UserInfoVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 16:04
 */
public interface UserService {
    UserInfoVO login(UserInfoDTO userInfo);

    UserInfoVO getUserInfoByAccount(String account);



    List<UserInfoVO> listUserInfo(UserInfoDTO userInfoDTO);

    NetworkCode registerUser(UserInfoDTO userInfoDTO);

    NetworkCode insertUser(UserInfoDTO userInfoDTO);



    NetworkCode updateUser(UserInfoDTO userInfoDTO);

    void deleteUser(UserInfoDTO userInfoDTO);
}
