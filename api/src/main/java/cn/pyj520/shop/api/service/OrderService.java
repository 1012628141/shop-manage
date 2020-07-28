package cn.pyj520.shop.api.service;

import cn.pyj520.shop.api.model.dto.OrderDTO;
import cn.pyj520.shop.api.model.vo.OrderVO;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:12
 */
public interface OrderService {
    List<OrderVO> listOrder(OrderDTO orderDTO);

    void updateOrder(OrderDTO orderDTO);
}
