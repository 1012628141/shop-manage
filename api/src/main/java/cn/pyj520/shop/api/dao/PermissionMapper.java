package cn.pyj520.shop.api.dao;

import cn.pyj520.shop.api.model.dto.PermissionDTO;
import cn.pyj520.shop.api.model.po.Permission;
import cn.pyj520.shop.api.model.vo.PermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<PermissionVO> listPermission(PermissionDTO permissionDTO);
}