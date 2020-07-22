package com.Kotori.store.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:46:00
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

