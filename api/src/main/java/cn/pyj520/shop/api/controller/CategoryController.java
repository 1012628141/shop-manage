package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.CategoryDTO;
import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.po.Category;
import cn.pyj520.shop.api.model.validate.Delete;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Update;
import cn.pyj520.shop.api.model.vo.CategoryVO;
import cn.pyj520.shop.api.model.vo.RoleVO;
import cn.pyj520.shop.api.service.CategoryService;
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
 * @Date: 2020-07-28 19:37
 */

@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @Author: zjy on 2020-07-28 16:26
     * @Description:获取当前用户下属的角色列表
     */
    @PostMapping(value = "list")
    public String listPermission(@RequestBody CategoryDTO categoryDTO) {
        //设置分页，使用mybatis插件
        categoryDTO.startPage();
        List<CategoryVO> categorys = categoryService.listCategory(categoryDTO);
        PageInfo pageInfo = new PageInfo(categorys);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }

    /**
     * @Author: zjy on 2020-07-28 19:59
     * @Description:添加分类
     */
    @PostMapping(value = "add")
    public String addCategory(@RequestBody @Validated(Insert.class) CategoryDTO categoryDTO) {
        NetworkCode networkCode = categoryService.addCategory(categoryDTO);
        return JsonResult.toString(networkCode);
    }

    /**
     * @Author: zjy on 2020-07-28 19:59
     * @Description:添加分类
     */
    @PostMapping(value = "update")
    public String updateCategory(@RequestBody @Validated(Update.class) CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

    /**
     * @Author: zjy on 2020-07-28 19:59
     * @Description:添加分类
     */
    @PostMapping(value = "delete")
    public String deleteCategory(@RequestBody @Validated(Delete.class) CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

}