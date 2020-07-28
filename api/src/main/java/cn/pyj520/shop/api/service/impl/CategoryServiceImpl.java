package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.constants.StatusEnum;
import cn.pyj520.shop.api.dao.CategoryMapper;
import cn.pyj520.shop.api.model.dto.CategoryDTO;
import cn.pyj520.shop.api.model.po.Category;
import cn.pyj520.shop.api.model.po.Role;
import cn.pyj520.shop.api.model.vo.CategoryVO;
import cn.pyj520.shop.api.service.CategoryService;
import cn.pyj520.shop.api.util.NullUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:38
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listCategory(CategoryDTO categoryDTO) {
        return categoryMapper.listCategory(categoryDTO);
    }

    /**
     * @Author: zjy on 2020-07-28 19:59
     * @Description:
     */
    @Transactional
    @Override
    public NetworkCode addCategory(CategoryDTO categoryDTO) {
        //设置层级
        categoryDTO.setDeep(categoryMapper.selectByPrimaryKey(categoryDTO.getParentId()).getDeep() + 1);
        Integer id = categoryMapper.maxSonId(categoryDTO.getParentId());
        if (NullUtil.isNullObject(id)) {
            id = categoryDTO.getParentId() * 100;
        }
        Category category = Category.builder().build();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setId(id + 1);
        category.setParentId(categoryDTO.getParentId());
        categoryMapper.insertSelective(category);
        return NetworkCode.CODE_SUCCESS;
    }

    /**
     * @Author: zjy on 2020-07-28 20:22
     * @Description:
     */
    @Transactional
    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category build = Category.builder().build();
        BeanUtils.copyProperties(categoryDTO, build);
        categoryMapper.updateByPrimaryKeySelective(build);
    }

    @Transactional
    @Override
    public void deleteCategory(CategoryDTO categoryDTO) {
        Category build = Category.builder().id(categoryDTO.getId()).status(StatusEnum.DELETE.getCode()).build();
        categoryMapper.updateByPrimaryKeySelective(build);
    }
}