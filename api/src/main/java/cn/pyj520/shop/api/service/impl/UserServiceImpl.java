package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.constants.AuthConstants;
import cn.pyj520.shop.api.constants.HeaderConstants;
import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.constants.StatusEnum;
import cn.pyj520.shop.api.dao.UserInfoMapper;
import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.dto.UserInfoDTO;
import cn.pyj520.shop.api.model.po.UserInfo;
import cn.pyj520.shop.api.model.po.UserRoleKey;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import cn.pyj520.shop.api.model.vo.UserInfoVO;
import cn.pyj520.shop.api.service.PermissionService;
import cn.pyj520.shop.api.service.RoleService;
import cn.pyj520.shop.api.service.UserService;
import cn.pyj520.shop.api.util.JWTUtil;
import cn.pyj520.shop.api.util.MD5Util;
import cn.pyj520.shop.api.util.NullUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 16:04
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String DEFAUL_PASSWORD = "123456";

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * @param userInfo
     * @Author: zjy on 2020-07-27 17:26
     * @Description:用户登陆，此处需要返回用户所拥有的权限
     */
    @Override
    public UserInfoVO login(UserInfoDTO userInfo) {
        UserInfoVO userInfoVO = getUserInfoByAccount(userInfo.getAccount());
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


    /**
     * @Author: zjy on 2020-07-28 10:15
     * @Description:查询用户列表，根绝当前角色的子角色
     */
    @Override
    public List<UserInfoVO> listUserInfo(UserInfoDTO userInfoDTO) {
        return userInfoMapper.listUserInfo(userInfoDTO);
    }

    /**
     * @Author: zjy on 2020-07-28 14:32
     * @Description:注册用户，此处需要处理用户的角色
     */
    @Transactional
    @Override
    public NetworkCode registerUser(UserInfoDTO userInfoDTO) {
        //设置默认密码
        userInfoDTO.passwordHelper().getPassword(DEFAUL_PASSWORD);
        NetworkCode code = insertUser(userInfoDTO);
        if (code != NetworkCode.CODE_SUCCESS) {
            return code;
        }
        //处理角色
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setUid(userInfoDTO.getId());
        userRoleKey.setRids(userInfoDTO.getRoleIds());
        roleService.addRoleRelation(userRoleKey);
        return NetworkCode.CODE_SUCCESS;
    }


    /**
     * @Author: zjy on 2020-07-28 14:51
     * @Description:添加用户以及验证
     */
    @Transactional
    @Override
    public NetworkCode insertUser(UserInfoDTO userInfoDTO) {
        UserInfoVO emailUserInfo = getUserInfoByAccount(userInfoDTO.getEmail());
        if (!NullUtil.isNullObject(emailUserInfo)) {
            return NetworkCode.ACCOUNT_BE_REGISTER;//邮箱被注册
        }
        UserInfoVO phoneUserInfo = getUserInfoByAccount(userInfoDTO.getPhone());
        if (!NullUtil.isNullObject(phoneUserInfo)) {
            return NetworkCode.ACCOUNT_BE_REGISTER;//手机号被注册
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        userInfoMapper.insertSelective(userInfo);
        userInfoDTO.setId(userInfo.getId());//将生成的用户id注入参数，供其他业务使用
        return NetworkCode.CODE_SUCCESS;
    }

    /**
     * @Author: zjy on 2020-07-28 15:22
     * @Description:
     */
    @Override
    public NetworkCode updateUser(UserInfoDTO userInfoDTO) {
        UserInfoVO emailUserInfo = getUserInfoByAccount(userInfoDTO.getEmail());
        if (!NullUtil.isNullObject(emailUserInfo) && !emailUserInfo.getId().equals(userInfoDTO.getId())) {
            return NetworkCode.ACCOUNT_BE_REGISTER;//邮箱被注册
        }
        UserInfoVO phoneUserInfo = getUserInfoByAccount(userInfoDTO.getPhone());
        if (!NullUtil.isNullObject(phoneUserInfo) && !emailUserInfo.getId().equals(userInfoDTO.getId())) {
            return NetworkCode.ACCOUNT_BE_REGISTER;//手机号被注册
        }
        UserInfo build = UserInfo.builder()
                .id(userInfoDTO.getId())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .status(userInfoDTO.getStatus())
                .birthday(userInfoDTO.getBirthday())
                .build();
        userInfoMapper.updateByPrimaryKeySelective(build);
        UserRoleKey userRoleKey = UserRoleKey.builder().uid(userInfoDTO.getId()).rids(userInfoDTO.getRoleIds()).build();
        roleService.updateRoleRelation(userRoleKey);
        return NetworkCode.CODE_SUCCESS;
    }

    /**
     * @Author: zjy on 2020-07-28 16:19
     * @Description:
     */
    @Transactional
    @Override
    public void deleteUser(UserInfoDTO userInfoDTO) {
        UserInfo build = UserInfo.builder()
                .id(userInfoDTO.getId())
                .status(StatusEnum.DELETE.getCode())
                .build();
        userInfoMapper.updateByPrimaryKeySelective(build);
    }
}