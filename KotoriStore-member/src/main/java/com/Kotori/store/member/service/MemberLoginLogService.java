package com.Kotori.store.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:52:53
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

