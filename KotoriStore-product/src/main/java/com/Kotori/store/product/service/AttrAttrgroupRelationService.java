package com.Kotori.store.product.service;

import com.Kotori.store.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-21 23:40:44
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupRelationVo> attrGroupRelationVos);
}

