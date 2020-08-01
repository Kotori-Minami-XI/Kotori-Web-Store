package com.Kotori.store.product.service;

import com.Kotori.store.vo.AttrResponseVo;
import com.Kotori.store.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-21 23:40:44
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params, long catId, String type);

    void saveAttrVo(AttrVo attrVo);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attrVo);
}

