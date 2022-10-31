package com.wind.serviceproduct.controller.demo;

import com.wind.serviceproduct.model.vo.DemoBatchVo;
import com.wind.serviceproduct.service.demo.rabbitmq.product.DemoBatchSend;
import com.wind.serviceproduct.service.demo.rabbitmq.product.DemoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/11:07
 * @Description:
 */
@RestController
@RequestMapping("/rabbitmqDemo")
public class RabbitControlller {

    @Autowired
    private DemoSend demoSend;

    @Autowired
    private DemoBatchSend demoBatchSend;

    @PostMapping("/sendTest")
    public String sendTest(@RequestBody String message){
        return demoSend.sendTest(message);
    }

    @PostMapping("/batchSendTest")
    public String batchSendTest(@RequestBody List<DemoBatchVo> messageList){
        demoBatchSend.batchSendTest(messageList);
        return "true";
    }
}
