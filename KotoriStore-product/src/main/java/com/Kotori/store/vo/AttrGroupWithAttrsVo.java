package com.Kotori.store.vo;

import com.Kotori.store.product.entity.AttrEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsVo {
    /**
     * attrGroupId
     */
    @TableId
    private Long attrGroupId;
    /**
     * attrGroupName
     */
    private String attrGroupName;
    /**
     * sort
     */
    private Integer sort;
    /**
     * descript
     */
    private String descript;
    /**
     * icon
     */
    private String icon;
    /**
     * catelog Id
     */
    private Long catelogId;

    private List<AttrEntity> attrs;
}
