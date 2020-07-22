package com.Kotori.store.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 13:45:11
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

