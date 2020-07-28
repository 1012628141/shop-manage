package cn.pyj520.shop.api.dao;

import cn.pyj520.shop.api.model.dto.RoleDTO;
import cn.pyj520.shop.api.model.po.UserRoleKey;
import cn.pyj520.shop.api.model.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    int deleteByUid(Integer uid);

    List<RoleVO> listRole(RoleDTO roleDTO);
}