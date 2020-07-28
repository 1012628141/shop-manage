package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.GoodsDTO;
import cn.pyj520.shop.api.model.dto.OrderDTO;
import cn.pyj520.shop.api.model.po.Order;
import cn.pyj520.shop.api.model.validate.Update;
import cn.pyj520.shop.api.model.vo.GoodsVO;
import cn.pyj520.shop.api.model.vo.OrderVO;
import cn.pyj520.shop.api.service.OrderService;
import cn.pyj520.shop.api.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 19:11
 */
@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "list")
    public String listOrder(@RequestBody OrderDTO orderDTO) {
        //设置分页，使用mybatis插件
        orderDTO.startPage();
        List<OrderVO> listOrder = orderService.listOrder(orderDTO);
        PageInfo pageInfo = new PageInfo(listOrder);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }


    /**
     * @Author: zjy on 2020-07-28 18:46
     * @Description:
     */
    @PostMapping(value = "update")
    public String updateOrder(@RequestBody @Validated(Update.class) OrderDTO orderDTO) {
        orderService.updateOrder(orderDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }
}