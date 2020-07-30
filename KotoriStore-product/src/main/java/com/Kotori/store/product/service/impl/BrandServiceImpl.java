package com.Kotori.store.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.product.dao.BrandDao;
import com.Kotori.store.product.entity.BrandEntity;
import com.Kotori.store.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        QueryWrapper<BrandEntity> queryWrapper= new QueryWrapper();

        if (key != null && key != "") {
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }

        IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }

}