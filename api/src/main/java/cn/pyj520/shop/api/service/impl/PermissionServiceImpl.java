package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.dao.PermissionMapper;
import cn.pyj520.shop.api.dao.RolePermissionMapper;
import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import cn.pyj520.shop.api.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 17:34
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;



    /**
     * @Author: zjy on 2020-07-27 18:02
     * @Description:根据条件查询权限列表
     */
    @Override
    public List<PermissionVO> listPermission(PermissionDTO permissionDTO) {
        return permissionMapper.listPermission(permissionDTO);
    }


}