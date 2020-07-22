package com.Kotori.store.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:45:59
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

