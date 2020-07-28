package cn.pyj520.shop.api.controller;

import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.model.dto.GoodsDTO;
import cn.pyj520.shop.api.model.validate.Insert;
import cn.pyj520.shop.api.model.validate.Update;
import cn.pyj520.shop.api.model.vo.GoodsVO;
import cn.pyj520.shop.api.service.GoodsService;
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
 * @Date: 2020-07-28 18:07
 */

@RestController
@RequestMapping(value = "goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * @Author: zjy on 2020-07-28 18:07
     * @Description:
     */
    @PostMapping(value = "list")
    public String listGoods(@RequestBody GoodsDTO goodsDTO) {
        //设置分页，使用mybatis插件
        goodsDTO.startPage();
        List<GoodsVO> goodsVOS = goodsService.listGoods(goodsDTO);
        PageInfo pageInfo = new PageInfo(goodsVOS);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS, pageInfo);
    }

    /**
     * @Author: zjy on 2020-07-28 18:46
     * @Description:
     */
    @PostMapping(value = "add")
    public String addGoods(@RequestBody @Validated(Insert.class) GoodsDTO goodsDTO) {
        goodsService.addGoods(goodsDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

    /**
     * @Author: zjy on 2020-07-28 18:46
     * @Description:
     */
    @PostMapping(value = "update")
    public String updateGoods(@RequestBody @Validated(Update.class) GoodsDTO goodsDTO) {
        goodsService.updateGoods(goodsDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }

    /**
     * @Author: zjy on 2020-07-28 18:46
     * @Description:
     */
    @PostMapping(value = "delete")
    public String deleteGoods(@RequestBody @Validated(Update.class) GoodsDTO goodsDTO) {
        goodsService.deleteGoods(goodsDTO);
        return JsonResult.toString(NetworkCode.CODE_SUCCESS);
    }
}