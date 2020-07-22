package com.Kotori.store.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.store.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:52:54
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

