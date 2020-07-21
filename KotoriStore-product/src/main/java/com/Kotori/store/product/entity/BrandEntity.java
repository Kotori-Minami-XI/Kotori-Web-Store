package com.Kotori.store.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * brand
 * 
 * @author Kotori
 * @email 543566812@qq.com
 * @date 2020-07-21 23:40:44
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * brand id
	 */
	@TableId
	private Long brandId;
	/**
	 * brand name
	 */
	private String name;
	/**
	 * brand logo path
	 */
	private String logo;
	/**
	 * brand description
	 */
	private String descript;
	/**
	 * display status [0-no display；1-display]
	 */
	private Integer showStatus;
	/**
	 * Initial name for search
	 */
	private String firstLetter;
	/**
	 * sort
	 */
	private Integer sort;

}
