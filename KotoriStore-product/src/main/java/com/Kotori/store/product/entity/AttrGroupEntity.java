package com.Kotori.store.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Attribute group
 * 
 * @author Kotori
 * @email 543566812@qq.com
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
	/**
	 * catelog Path
	 */
	@TableField(exist = false)
	private Long[] catelogPath;
}
