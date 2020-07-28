package cn.pyj520.shop.api.dao;

import cn.pyj520.shop.api.model.dto.CategoryDTO;
import cn.pyj520.shop.api.model.po.Category;
import cn.pyj520.shop.api.model.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryVO> listCategory(CategoryDTO categoryDTO);

    Integer maxSonId(Integer parentId);
}