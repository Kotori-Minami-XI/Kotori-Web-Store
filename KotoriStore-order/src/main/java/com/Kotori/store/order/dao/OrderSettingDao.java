package com.Kotori.store.order.dao;

import com.Kotori.store.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 13:42:20
 */
@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSettingEntity> {
	
}
