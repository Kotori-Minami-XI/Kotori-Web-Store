package com.Kotori.store.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.product.entity.BrandEntity;

import java.util.Map;

/**
 * Brand
 *
 * @author Kotori
 * @email 543566812@qq.com
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

