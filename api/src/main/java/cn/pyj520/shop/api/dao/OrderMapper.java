package cn.pyj520.shop.api.dao;

import cn.pyj520.shop.api.model.dto.OrderDTO;
import cn.pyj520.shop.api.model.po.Order;
import cn.pyj520.shop.api.model.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVO> listOrder(OrderDTO orderDTO);
}