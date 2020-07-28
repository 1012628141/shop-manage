package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.model.dto.GoodsDTO;
import cn.pyj520.shop.api.model.vo.GoodsVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 18:10
 */
public interface GoodsService {
    List<GoodsVO> listGoods(GoodsDTO goodsDTO);

    void addGoods(GoodsDTO goodsDTO);

    void updateGoods(GoodsDTO goodsDTO);

    void deleteGoods(GoodsDTO goodsDTO);
}
