package com.Kotori.store.member.dao;

import com.Kotori.store.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-22 12:52:54
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
