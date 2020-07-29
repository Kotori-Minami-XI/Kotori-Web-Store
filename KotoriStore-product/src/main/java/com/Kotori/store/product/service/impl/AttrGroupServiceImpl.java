package com.Kotori.store.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.product.dao.AttrGroupDao;
import com.Kotori.store.product.entity.AttrGroupEntity;
import com.Kotori.store.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if (0 == catelogId) { // Obtain all attributes
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    new QueryWrapper<AttrGroupEntity>());
            return new PageUtils(page);
        } else {
            QueryWrapper<AttrGroupEntity> queryWrapper =
                    new QueryWrapper<AttrGroupEntity>().eq("catelog_id",catelogId);
            String key = (String)params.get("key");
            if (null != key) {
                queryWrapper.and((obj)->{
                    obj.eq("attr_group_id",key).or().like("attr_group_name",key);
                });
                IPage<AttrGroupEntity> page = this.page(
                        new Query<AttrGroupEntity>().getPage(params),
                        queryWrapper);
                return new PageUtils(page);
            }
        }
        return null;
    }
}