package com.Kotori.store.product.service.impl;

import com.Kotori.common.constant.ProductConstant;
import com.Kotori.store.product.dao.AttrAttrgroupRelationDao;
import com.Kotori.store.product.dao.AttrGroupDao;
import com.Kotori.store.product.dao.CategoryDao;
import com.Kotori.store.product.entity.AttrAttrgroupRelationEntity;
import com.Kotori.store.product.entity.AttrGroupEntity;
import com.Kotori.store.product.entity.CategoryEntity;
import com.Kotori.store.product.service.CategoryService;
import com.Kotori.store.vo.AttrGroupRelationVo;
import com.Kotori.store.vo.AttrResponseVo;
import com.Kotori.store.vo.AttrVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.product.dao.AttrDao;
import com.Kotori.store.product.entity.AttrEntity;
import com.Kotori.store.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Resource
    private AttrGroupDao attrGroupDao;

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, long catId, String type) {
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", "base".equals(type) ?
                        ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());
        String key = (String)params.get("key");

        if (0 != catId) {
            queryWrapper.eq("catelog_id", catId);
        }

        if (null != key && key.length() > 0) {
            queryWrapper.and((wrapper)->{
                wrapper.like("attr_name",key).or().like("attr_id",key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);

        List<AttrEntity> records = page.getRecords();
        List<AttrResponseVo> responseVos = new ArrayList(records.size());
        for (AttrEntity entity : records) {
            AttrResponseVo attrResponseVo = new AttrResponseVo();
            BeanUtils.copyProperties(entity, attrResponseVo);

            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", entity.getAttrId()));

            if (null != attrAttrgroupRelationEntity) {
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if (null != attrGroupEntity) {
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(entity.getCatelogId());
            if (null != categoryEntity) {
                attrResponseVo.setCatelogName(categoryEntity.getName());
            }
            responseVos.add(attrResponseVo);
        }
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(responseVos);
        return pageUtils;
    }

    @Override
    @Transactional
    public void saveAttrVo(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);

        // Save basic info
        this.save(attrEntity);

        // Save relation between attr and Attr-group
        if (attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public AttrResponseVo getAttrInfo(Long attrId) {
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, attrResponseVo);
        if (attrEntity.getValueSelect().contains(";")) {
            attrResponseVo.setValueType(1);
        } else {
            attrResponseVo.setValueType(0);
        }

        // Set attr group info
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (null != attrAttrgroupRelationEntity && null != attrAttrgroupRelationEntity.getAttrGroupId()) {
                attrResponseVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if (null != attrGroupEntity) {
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        // Set category info
        Long[] path = categoryService.findCategoryPath(attrEntity.getCatelogId());
        attrResponseVo.setCatelogPath(path);
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
        if (null != categoryEntity) {
            attrResponseVo.setCatelogName(categoryEntity.getName());
        }
        return attrResponseVo;
    }

    @Override
    @Transactional
    public void updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        this.updateById(attrEntity);

        // Update attr and attr-group relation
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrVo.getAttrId());

            int count = attrAttrgroupRelationDao.selectCount(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrVo.getAttrId()));

            if (count > 0) {
                attrAttrgroupRelationDao.update(attrAttrgroupRelationEntity,
                        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrVo.getAttrId()));
            } else {
                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
            }
        }
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> attrgroupList = attrAttrgroupRelationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id",attrgroupId));

        List<AttrEntity> attrList = new ArrayList(attrgroupList.size());
        for (AttrAttrgroupRelationEntity attrAttrgroupRelationEntity : attrgroupList) {
            attrList.add(this.getById(attrAttrgroupRelationEntity.getAttrId()));
        }
        return attrList;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos) {
        List<AttrGroupRelationVo> vosList = Arrays.asList(attrGroupRelationVos);
        List<AttrAttrgroupRelationEntity> entities = new ArrayList();

        for (AttrGroupRelationVo attrGroupRelationVo : vosList) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(attrGroupRelationVo, attrAttrgroupRelationEntity);
            entities.add(attrAttrgroupRelationEntity);
        }
        System.out.println("------------------" + entities);
        attrAttrgroupRelationDao.deleteBatchRelation(entities);
    }

    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();

        // 1. Obtain all attr group for the current catelog
        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.selectList(
                new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        List<Long> attrGroupIds = new ArrayList();
        for (AttrGroupEntity groupEntity : attrGroupEntities) {
            attrGroupIds.add(groupEntity.getAttrGroupId());
        }

        // 2.
        List<Long> attrIds = new ArrayList();
        List<AttrAttrgroupRelationEntity> attrAttrGroupRelationEntities = attrAttrgroupRelationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", attrGroupIds));
        for (AttrAttrgroupRelationEntity attrAttrGroupRelationEntity : attrAttrGroupRelationEntities) {
            attrIds.add(attrAttrGroupRelationEntity.getAttrId());
        }

        // 3.
        List<AttrEntity> attrEntities = this.baseMapper.selectList(
                new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).notIn("attr_id", attrIds));
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (attrIds != null && attrIds.size() > 0 ) {
            queryWrapper.notIn("attr_id", attrIds);
        }

        String key = (String)params.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            queryWrapper.and((wrapper)->{
                wrapper.eq("attr_id",key).or().like("attr_name", key);
            });
        }


        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}