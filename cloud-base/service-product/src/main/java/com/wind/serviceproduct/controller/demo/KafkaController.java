package com.wind.serviceproduct.controller.demo;

import com.wind.serviceproduct.service.demo.kafka.product.DemoSendKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/15:36
 * @Description:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private DemoSendKafka demoSendKafka;

    @PostMapping("/sendTest")
    public String sendTest(@RequestBody String message){
         demoSendKafka.send(message);
         return "true";
    }

}
