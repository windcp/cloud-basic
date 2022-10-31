package com.wind.serviceproduct.service.demo.rabbitmq.product.impl;

import com.wind.rabbitmq.repo.RabbitBatchSendRepo;
import com.wind.rabbitmq.repo.RabbitSendRepo;
import com.wind.serviceproduct.model.vo.DemoBatchVo;
import com.wind.serviceproduct.service.demo.rabbitmq.product.DemoBatchSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/31/14:34
 * @Description:
 */
@Service
public class DemoBatchSendImpl implements DemoBatchSend {

    @Autowired
    private RabbitBatchSendRepo rabbitBatchSendRepo;

    @Override
    public void batchSendTest(List<DemoBatchVo> messageList) {
        rabbitBatchSendRepo.sendBatch("demo.batch","batch.test",messageList);
    }
}
