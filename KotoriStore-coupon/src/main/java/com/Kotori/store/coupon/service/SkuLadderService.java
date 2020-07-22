package com.Kotori.store.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:46:00
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

