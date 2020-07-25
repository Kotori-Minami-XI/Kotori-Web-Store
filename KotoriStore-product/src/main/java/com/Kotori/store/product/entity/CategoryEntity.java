package com.Kotori.store.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 商品三级分类
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-21 23:40:44
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Category id
	 */
	@TableId
	private Long catId;
	/**
	 * Category name
	 */
	private String name;
	/**
	 * Parent category id
	 */
	private Long parentCid;
	/**
	 * Category level
	 */
	private Integer catLevel;
	/**
	 * Display or not[0-no，1-yes]
	 */
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 * Product display order
	 */
	private Integer sort;
	/**
	 * Icon path for the product
	 */
	private String icon;
	/**
	 * The unit for the Product
	 */
	private String productUnit;
	/**
	 * Remaining Product number
	 */
	private Integer productCount;
	/**
	 * Sub categories
	 * Not exist in the table
	 */
	@TableField(exist = false)
	private List<CategoryEntity> children;
}
