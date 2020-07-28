package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.CategoryDTO;
import cn.pyj520.shop.api.model.vo.CategoryVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:37
 */
public interface CategoryService {
    List<CategoryVO> listCategory(CategoryDTO categoryDTO);

    NetworkCode addCategory(CategoryDTO categoryDTO);

    void updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(CategoryDTO categoryDTO);
}
