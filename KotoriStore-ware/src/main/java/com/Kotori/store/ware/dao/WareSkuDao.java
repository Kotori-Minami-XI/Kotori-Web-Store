package com.Kotori.store.ware.dao;

import com.Kotori.store.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 13:45:11
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
