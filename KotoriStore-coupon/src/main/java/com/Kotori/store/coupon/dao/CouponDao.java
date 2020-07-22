package com.Kotori.store.coupon.dao;

import com.Kotori.store.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:46:00
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
