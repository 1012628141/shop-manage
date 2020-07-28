package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.po.UserRoleKey;
import cn.pyj520.shop.api.model.vo.RoleVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 14:54
 */


public interface RoleService {
    void addRoleRelation(UserRoleKey userRoleKey);

    void addRoleRelation(Integer uid, Integer rid);

    void updateRoleRelation(UserRoleKey userRoleKey);

    List<RoleVO> listRole(RoleDTO roleDTO);

    NetworkCode addRole(RoleDTO roleDTO);

    void deleteRole(RoleDTO roleDTO);

    void addPermission(RoleDTO roleDTO);
}
