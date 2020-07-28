package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.vo.PermissionVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 17:34
 */
public interface PermissionService {

    List<PermissionVO> listPermission(PermissionDTO permissionDTO);


}
