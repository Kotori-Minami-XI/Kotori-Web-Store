package com.Kotori.store.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 13:42:20
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

