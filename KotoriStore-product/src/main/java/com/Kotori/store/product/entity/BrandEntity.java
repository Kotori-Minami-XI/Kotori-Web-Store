package com.Kotori.store.product.entity;

import com.Kotori.common.valid.AddGroup;
import com.Kotori.common.valid.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

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
	@NotNull(message = "品牌id不能为空", groups={UpdateGroup.class})
	@Null(message = "品牌id必须要为空", groups={AddGroup.class})
	private Long brandId;
	/**
	 * brand name
	 */
	@NotBlank(message = "品牌名称不能为空", groups={AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * brand logo path
	 */
	@URL(message = "logo必须是合法的Url", groups={AddGroup.class, UpdateGroup.class} )
	@NotEmpty (groups = {AddGroup.class})
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
	@Pattern(regexp="/^[a-zA-Z]$/", message = "输入一个英文字母", groups={AddGroup.class, UpdateGroup.class})
	@NotEmpty(groups={AddGroup.class})
	private String firstLetter;
	/**
	 * sort
	 */
	@Min(value = 0, message = "排序必须大于0", groups={AddGroup.class, UpdateGroup.class})
	@NotNull(groups={AddGroup.class})
	private Integer sort;

}
