package com.wind.serviceopenfeign.controller;

import com.wind.serviceopenfeign.model.vo.GroupVo;
import com.wind.serviceopenfeign.service.StockOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/13/16:20
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockOpenFeignService stockOpenFeignService;

    /**
     * 新增订单
     * @return
     */
    @GetMapping("/addOrder")
    public String addOrder(@RequestParam(name = "s")  String s){
        System.out.println("订单新增成功");
        //调用库存扣减
        String result = stockOpenFeignService.addStock(s);
        return "订单服务-订单新增成功:" + result;
    }

    /**
     * 新增订单
     * @return
     */
    @PostMapping("/addObjOrder")
    public String addObjOrder(@RequestBody GroupVo groupVo){
        System.out.println("订单新增成功");
        //调用库存扣减
        String result = stockOpenFeignService.addsubStock(groupVo);
        return "订单服务-订单新增成功:" + result;
    }

    /**
     * 新增订单
     * @return
     */
    @RequestMapping("/subOrder")
    public String subStock(){
        System.out.println("订单新增成功");
        //调用库存扣减
        String result = stockOpenFeignService.subStock();
        return "订单服务-订单新增成功:" + result;
    }
}
