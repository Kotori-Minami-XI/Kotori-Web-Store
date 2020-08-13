package com.Kotori.store.product.service.impl;

import com.Kotori.store.product.entity.AttrEntity;
import com.Kotori.store.product.entity.ProductAttrValueEntity;
import com.Kotori.store.product.entity.SpuInfoDescEntity;
import com.Kotori.store.product.service.*;
import com.Kotori.store.vo.BaseAttrs;
import com.Kotori.store.vo.SpuSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.product.dao.SpuInfoDao;
import com.Kotori.store.product.entity.SpuInfoEntity;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Autowired
    SkuInfoService skuInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveInfo(SpuSaveVo spuSaveVo) {
        // 1. Save basic info pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);

        // 2. Save spu desc info pms_spu_info_desc
        List<String> list = spuSaveVo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",",list));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);

        // 3. Save pms_spu_image
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);

        // 4. Save pms_product_attr_value
        List<BaseAttrs> baseAttrsList = spuSaveVo.getBaseAttrs();
        List<ProductAttrValueEntity> productAttrValueEntityList = new ArrayList();
        for (BaseAttrs baseAttrs : baseAttrsList) {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setAttrId(baseAttrs.getAttrId());
            AttrEntity id = attrService.getById(baseAttrs.getAttrId());
            productAttrValueEntity.setAttrName(id.getAttrName());
            productAttrValueEntity.setAttrValue(baseAttrs.getAttrValues());
            productAttrValueEntity.setQuickShow(baseAttrs.getShowDesc());
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());

            productAttrValueEntityList.add(productAttrValueEntity);
        }
        productAttrValueService.saveProductAttr(productAttrValueEntityList);

        // 5.
        //List<Skus>
        //skuInfoService
    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

}