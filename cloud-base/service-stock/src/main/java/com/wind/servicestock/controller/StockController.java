package com.wind.servicestock.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/11/21:48
 * @Description:
 */

import com.wind.servicestock.model.vo.GroupVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 库存服务
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    private String port;

    /**
     * 库存扣减
     * @return
     */
    @PostMapping("/subObjStock")
    public String subObjStock(@RequestBody GroupVo groupVo){
        System.out.println("库存扣减成功" + groupVo.toString() );

        return "库存服务-库存扣减成功 - 端口：" + port + groupVo.toString();
    }

    /**
     * 库存新增
     * @return
     */
    @GetMapping("/addStock")
    public String addStock(@RequestParam(name = "s")  String s){
        System.out.println("库存新增成功" + s);

        return "库存服务-库存新增成功" + s;
    }

    /**
     * 库存扣减
     * @return
     */
    @RequestMapping("/subStock")
    public String subStock(){
        System.out.println("库存扣减成功");

        return "库存服务-库存扣减成功 - 端口：" + port;
    }
}