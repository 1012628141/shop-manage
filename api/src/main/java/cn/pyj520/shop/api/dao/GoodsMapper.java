package cn.pyj520.shop.api.dao;

import cn.pyj520.shop.api.model.dto.GoodsDTO;
import cn.pyj520.shop.api.model.po.Goods;
import cn.pyj520.shop.api.model.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsVO> listGoods(GoodsDTO goodsDTO);

}