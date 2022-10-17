package com.wind.serviceopenfeign.service.sentinel;

import com.wind.serviceopenfeign.model.vo.GroupVo;
import com.wind.serviceopenfeign.service.StockOpenFeignService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/17/17:24
 * @Description:
 */

@Component
public class StockOpenFeignServiceFallback implements StockOpenFeignService {

    @Override
    public String subStock() {
        return "subStock-服务降级了！";
    }

    @Override
    public String addStock(String s) {
        return "subStock-服务降级了！";
    }

    @Override
    public String addsubStock(GroupVo groupVo) {
        return "subStock-服务降级了！";
    }
}
