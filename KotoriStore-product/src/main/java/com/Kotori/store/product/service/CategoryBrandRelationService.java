package com.Kotori.store.product.service;

import com.Kotori.store.product.entity.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * Brand and category service
 *
 * @author Kotori
 * @email 543566812@qq.com
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    List<BrandEntity> getBrandsByCatId(Long catId);
}

