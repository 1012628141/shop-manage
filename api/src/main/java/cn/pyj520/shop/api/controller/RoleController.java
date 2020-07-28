package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.validate.Delete;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.vo.RoleVO;
import cn.pyj520.shop.api.service.RoleService;
import cn.pyj520.shop.api.util.JsonResult;
import cn.pyj520.shop.api.util.OathHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 16:25
 */
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    RoleService roleService;


    /**
     * @Author: zjy on 2020-07-28 16:26
     * @Description:获取当前用户下属的角色列表
     */
    @PostMapping(value = "list")
    public String listPermission(@RequestBody RoleDTO roleDTO) {
        //设置分页，使用mybatis插件
        roleDTO.startPage();
        //获取当前登陆用户id
        roleDTO.setUserId(OathHelper.getUserId());
        List<RoleVO> roleVOS = roleService.listRole(roleDTO);
        PageInfo pageInfo = new PageInfo(roleVOS);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }


    /**
     * @Author: zjy on 2020-07-28 16:39
     * @Description:添加角色
     */
    @PostMapping(value = "add")
    public String addRole(@RequestBody @Validated(Insert.class) RoleDTO roleDTO) {
        NetworkCode networkCode = roleService.addRole(roleDTO);
        return JsonResult.toString(networkCode);
    }


    /**
     * @Author: zjy on 2020-07-28 17:11
     * @Description:删除角色
     */
    @PostMapping(value = "delete")
    public String deleteRole(@RequestBody @Validated(Delete.class) RoleDTO roleDTO) {
        roleService.deleteRole(roleDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

    /**
     * @Author: zjy on 2020-07-28 17:16
     * @Description:给角色分配权限
     */
    @PostMapping(value = "permission")
    public String permission(@RequestBody @Validated(Delete.class) RoleDTO roleDTO) {
        roleService.addPermission(roleDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

}