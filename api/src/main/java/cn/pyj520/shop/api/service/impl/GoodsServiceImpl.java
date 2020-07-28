package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.constants.StatusEnum;
import cn.pyj520.shop.api.dao.GoodsMapper;
import cn.pyj520.shop.api.model.dto.GoodsDTO;
import cn.pyj520.shop.api.model.po.Goods;
import cn.pyj520.shop.api.model.vo.GoodsVO;
import cn.pyj520.shop.api.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 18:10
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVO> listGoods(GoodsDTO goodsDTO) {
        return goodsMapper.listGoods(goodsDTO);
    }

    /**
     * @Author: zjy on 2020-07-28 18:47
     * @Description:新增商品
     */
    @Transactional
    @Override
    public void addGoods(GoodsDTO goodsDTO) {
        Goods goods = Goods.builder().build();
        BeanUtils.copyProperties(goodsDTO, goods);
        goodsMapper.insertSelective(goods);
    }

    /**
     * @Author: zjy on 2020-07-28 18:53
     * @Description:
     */
    @Override
    public void updateGoods(GoodsDTO goodsDTO) {
        Goods goods = Goods.builder().build();
        BeanUtils.copyProperties(goodsDTO, goods);
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * @Author: zjy on 2020-07-28 18:54
     * @Description:
     */
    @Override
    public void deleteGoods(GoodsDTO goodsDTO) {
        Goods goods = Goods.builder().id(goodsDTO.getId()).build();
        goods.setStatus(StatusEnum.DELETE.getCode());
        goodsMapper.updateByPrimaryKeySelective(goods);
    }
}