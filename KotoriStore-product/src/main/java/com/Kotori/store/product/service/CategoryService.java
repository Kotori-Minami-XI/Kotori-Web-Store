package com.Kotori.store.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * CategoryService
 *
 * @author Kotori
 * @email 543566812@qq.com
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listCategoryTree();

    void removeMenuByIds(List<Long> asList);
}

