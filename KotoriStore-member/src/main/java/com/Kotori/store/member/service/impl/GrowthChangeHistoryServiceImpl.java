package com.Kotori.store.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.member.dao.GrowthChangeHistoryDao;
import com.Kotori.store.member.entity.GrowthChangeHistoryEntity;
import com.Kotori.store.member.service.GrowthChangeHistoryService;


@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity> implements GrowthChangeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistoryEntity> page = this.page(
                new Query<GrowthChangeHistoryEntity>().getPage(params),
                new QueryWrapper<GrowthChangeHistoryEntity>()
        );

        return new PageUtils(page);
    }

}