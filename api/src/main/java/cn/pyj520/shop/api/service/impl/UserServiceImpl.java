package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.constants.AuthConstants;
import cn.pyj520.shop.api.constants.HeaderConstants;
import cn.pyj520.shop.api.dao.UserInfoMapper;
import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import cn.pyj520.shop.api.model.vo.UserInfoVO;
import cn.pyj520.shop.api.service.PermissionService;
import cn.pyj520.shop.api.service.UserService;
import cn.pyj520.shop.api.util.JWTUtil;
import cn.pyj520.shop.api.util.MD5Util;
import cn.pyj520.shop.api.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 16:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PermissionService permissionService;

    /**
     * @param userInfo
     * @Author: zjy on 2020-07-27 17:26
     * @Description:用户登陆，此处需要返回用户所拥有的权限
     */
    @Override
    public UserInfoVO login(UserInfoDTO userInfo) {
        UserInfoVO userInfoVO = getUserInfoByAccount(userInfo.getAccount());
        //判断账号是否存在和密码是否匹配
        String password = MD5Util.MD5Encode(userInfo.getPassword());
        if (NullUtil.isNullObject(userInfoVO) || !userInfoVO.getPassword().equals(password)) {
            return null;
        }
        PermissionDTO permissionDTO = PermissionDTO.builder().build();
        permissionDTO.setUserId(userInfoVO.getId());
        //获取用户的拥有的权限
        List<PermissionVO> permissionVOS = permissionService.listPermission(permissionDTO);
        userInfoVO.setPermissions(permissionVOS);
        userInfoVO.setAccessToken(JWTUtil.generateToken(HeaderConstants.ACCESS_TOKEN, userInfoVO.getId(), JWTUtil.REFRESHTOKEN_EXPIRED_TIME));
        return userInfoVO;
    }

    /**
     * @Author: zjy on 2020-07-27 17:15
     * @Description:根据账户查找用户
     */
    @Override
    public UserInfoVO getUserInfoByAccount(String account) {
        return userInfoMapper.getUserInfoByAccount(account);
    }
}