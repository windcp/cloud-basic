package com.wind.serviceproduct.service.demo.rabbitmq.product;

import com.wind.serviceproduct.model.vo.DemoBatchVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/31/14:33
 * @Description:
 */
public interface DemoBatchSend {

    void batchSendTest(List<DemoBatchVo> messageList);
}
