package cn.pyj520.shop.api.service.impl;

import cn.pyj520.shop.api.dao.OrderMapper;
import cn.pyj520.shop.api.model.dto.OrderDTO;
import cn.pyj520.shop.api.model.po.Order;
import cn.pyj520.shop.api.model.vo.OrderVO;
import cn.pyj520.shop.api.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:12
 */

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    /**
     * @Author: zjy on 2020-07-28 19:17
     * @Description:
     */
    @Override
    public List<OrderVO> listOrder(OrderDTO orderDTO) {
        return orderMapper.listOrder(orderDTO);
    }

    @Transactional
    @Override
    public void updateOrder(OrderDTO orderDTO) {
        Order build = Order.builder().build();
        BeanUtils.copyProperties(orderDTO, build);
        orderMapper.updateByPrimaryKeySelective(build);
    }
}