package com.wind.rabbitmq.model.dto;

import com.wind.rabbitmq.utils.DESUtil;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/10:55
 * @Description:
 */
@Data
public class MessageDto {

    private String messageId;

    private Long createTime;

    private Object Data;


}
