package com.Kotori.store.product.dao;

import com.Kotori.store.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-21 23:40:44
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
