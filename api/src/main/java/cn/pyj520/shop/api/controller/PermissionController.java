package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.po.Permission;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import cn.pyj520.shop.api.service.PermissionService;
import cn.pyj520.shop.api.util.JsonResult;
import cn.pyj520.shop.api.util.OathHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 17:47
 */
@RestController
@RequestMapping(value = "permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    /**
     * @Author: zjy on 2020-07-27 17:48
     * @Description:获取当前用户的权限列表
     */
    @PostMapping(value = "list")
    public String listPermission(@RequestBody PermissionDTO permissionDTO) {
        //设置分页，使用mybatis插件
        permissionDTO.startPage();
        //获取当前登陆用户id
        permissionDTO.setUserId(OathHelper.getUserId());
        List<PermissionVO> permissionVOS = permissionService.listPermission(permissionDTO);
        PageInfo pageInfo = new PageInfo(permissionVOS);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }

}