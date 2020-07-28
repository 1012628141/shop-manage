package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.dao.RoleMapper;
import cn.pyj520.shop.api.dao.RolePermissionMapper;
import cn.pyj520.shop.api.dao.UserRoleMapper;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.po.Permission;
import cn.pyj520.shop.api.model.po.Role;
import cn.pyj520.shop.api.model.po.RolePermissionKey;
import cn.pyj520.shop.api.model.po.UserRoleKey;
import cn.pyj520.shop.api.model.vo.RoleVO;
import cn.pyj520.shop.api.service.PermissionService;
import cn.pyj520.shop.api.service.RoleService;
import cn.pyj520.shop.api.util.NullUtil;
import cn.pyj520.shop.api.util.OathHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 14:55
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    /**
     * @Author: zjy on 2020-07-28 15:00
     * @Description:
     */
    @Transactional
    @Override
    public void addRoleRelation(UserRoleKey userRoleKey) {
        if (!NullUtil.isNullObject(userRoleKey.getRids())) {
            for (Integer rid : userRoleKey.getRids()) {
                addRoleRelation(userRoleKey.getUid(), rid);
            }
        }
        if (!NullUtil.isNullObject(userRoleKey.getRid())) {
            addRoleRelation(userRoleKey.getUid(), userRoleKey.getRid());
        }
    }


    /**
     * @Author: zjy on 2020-07-28 15:07
     * @Description:
     */
    @Transactional
    @Override
    public void addRoleRelation(Integer uid, Integer rid) {
        UserRoleKey build = UserRoleKey.builder().rid(rid).uid(uid).build();
        userRoleMapper.insertSelective(build);
    }

    /**
     * @Author: zjy on 2020-07-28 15:31
     * @Description:先清楚该用户所有的关系
     */
    @Transactional
    @Override
    public void updateRoleRelation(UserRoleKey userRoleKey) {
        userRoleMapper.deleteByUid(userRoleKey.getUid());
        addRoleRelation(userRoleKey);
    }

    /**
     * @Author: zjy on 2020-07-28 16:30
     * @Description:获取所有的角色列表
     */
    @Override
    public List<RoleVO> listRole(RoleDTO roleDTO) {
        return userRoleMapper.listRole(roleDTO);
    }

    /**
     * @Author: zjy on 2020-07-28 16:40
     * @Description:添加角色
     */
    @Transactional
    @Override
    public NetworkCode addRole(RoleDTO roleDTO) {
        //默认使用当前用户角色的第一个作为parentId
        RoleDTO build = RoleDTO.builder().build();
        build.setUserId(OathHelper.getUserId());
        List<RoleVO> roleVOS = listRole(build);
        if (NullUtil.isNullObject(roleVOS)) {
            return NetworkCode.PARM_ERROR;
        }
        //根据父类查询子类的最大的id
        Integer id = roleMapper.maxSonId(roleVOS.get(0).getId());
        if (NullUtil.isNullObject(id)) {
            id = roleVOS.get(0).getId() * 100;
        }
        Role role = Role.builder().build();
        BeanUtils.copyProperties(roleDTO, role);
        role.setId(id + 1);
        role.setParentId(roleVOS.get(0).getId());
        roleMapper.insertSelective(role);
        return NetworkCode.CODE_SUCCESS;
    }

    /**
     * @Author: zjy on 2020-07-28 17:07
     * @Description:
     */
    @Transactional
    @Override
    public void deleteRole(RoleDTO roleDTO) {
        roleMapper.deleteByPrimaryKey(roleDTO.getId());
    }

    /**
     * @Author: zjy on 2020-07-28 17:16
     * @Description:
     */
    @Transactional
    @Override
    public void addPermission(RoleDTO roleDTO) {
        rolePermissionMapper.deleteByRoleId(roleDTO.getId());
        if (NullUtil.isNullObject(roleDTO.getPermissionIds())) {
            return;
        }
        for (Integer id : roleDTO.getPermissionIds()) {
            RolePermissionKey build = RolePermissionKey.builder().pid(id).rid(roleDTO.getId()).build();
            rolePermissionMapper.insertSelective(build);
        }

    }
}