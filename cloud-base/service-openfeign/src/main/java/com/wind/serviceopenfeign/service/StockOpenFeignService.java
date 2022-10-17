package com.wind.serviceopenfeign.service;

import com.wind.serviceopenfeign.model.vo.GroupVo;
import com.wind.serviceopenfeign.service.sentinel.StockOpenFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/13/16:18
 * @Description:
 */
@FeignClient(name = "service-stock",path = "stock" ,fallback = StockOpenFeignServiceFallback.class)
public interface StockOpenFeignService {

    //声明需要调用的rest接口对应的方法
    /**
     * 库存扣减
     * @return
     */
    @GetMapping("/subStock")
    String subStock();

    /**
     * 库存新增
     * @return
     */
    @GetMapping("/addStock")
    String addStock(@RequestParam(name = "s")String s);

    /**
     * 库存新增
     * @return
     */
    @PostMapping("/subObjStock")
    String addsubStock(@RequestBody GroupVo groupVo);




}
