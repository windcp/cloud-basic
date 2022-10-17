package com.wind.serviceopenfeign.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.wind.serviceopenfeign.service.StockOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/17/14:22
 * @Description:
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    @Autowired
    private StockOpenFeignService stockOpenFeignService;

    private static final String ORDER_RESOURCE_NAME = "service-stock";

    /**
     * 使用@SentinelResource进行Sentinel流控
     * @SentinelResource注解改善接口钟资源定义和被流控降级后的处理方法
     * 使用方法：1、添加依赖
     *           2、配置bean-SentinelResourceAspect
     *  value:定义流控资源
     *  blockHandler：设置流控降级后的处理方法（默认该方法必须声明在同一个类）
     *      如果不想在同一个类中，可以使用 blockHandlerClass 指定，但是方法必须是static
     *  fallback：当接口出现异常，就可以交给fallback指定的方法进行处理
     *      如果不想在同一个类中，可以使用 fallbackClass 指定，但是方法必须是static
     *
     *  注意：如果blockHandler和fallback方法同时指定了，则blockHandler优先级更高
     * 新增订单
     * @return
     */
    @GetMapping ("/subOrder")
    //@SentinelResource(value = ORDER_RESOURCE_NAME,blockHandler = "blockHandlerForUserTest",fallback = "fallbackForUserTest")
    public String subStock(@RequestParam(name = "s")String s){
        System.out.println("订单新增成功");
        //调用库存扣减
        String result = stockOpenFeignService.addStock(s);
        return "订单服务-订单新增成功:" + result;
    }

//    /**
//     * userTest流控降级后的处理方法
//     * 注意：
//     * 1、一定要是public
//     * 2、返回值一定要和源方法（userTest）保证一致，包含源方法的参数
//     * 3、可以在参数最后添加BlockException，可以区分是什么规则的处理方法
//     * @param id
//     * @param ex
//     * @return
//     */
//    public String blockHandlerForUserTest(String id, BlockException ex){
//        ex.printStackTrace();
//        return "服务降级："+ ex.getMessage();
//    }
//
//    /**
//     * userTest异常后的处理方法
//     * 注意：
//     * 1、一定要是public
//     * 2、返回值一定要和源方法（userTest）保证一致，包含源方法的参数
//     * 3、可以在参数最后添加Throwable，可以区分是什么异常
//     * @param id
//     * @param e
//     * @return
//     */
//    public String fallbackForUserTest(String id,Throwable e){
//        e.printStackTrace();
//        return  "服务异常处理" + e.getMessage();
//    }

    /**
     * 定义规则
     *
     * spring的初始化方法
     */
    @PostConstruct
    private static void initFlowRules(){
        //流控规则
        List<FlowRule> rules = new ArrayList<>();//流控
        FlowRule rule2 = new FlowRule();
        //设置受保护的资源
        rule2.setResource(ORDER_RESOURCE_NAME);
        // 设置流控规则 QPS
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护资源的阈值
        // Set limit QPS to 20.
        rule2.setCount(1);
        rules.add(rule2);

        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

}
