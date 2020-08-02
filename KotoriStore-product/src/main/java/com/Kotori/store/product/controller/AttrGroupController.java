package com.Kotori.store.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.Kotori.store.product.entity.AttrEntity;
import com.Kotori.store.product.service.AttrAttrgroupRelationService;
import com.Kotori.store.product.service.AttrService;
import com.Kotori.store.product.service.CategoryService;
import com.Kotori.store.vo.AttrGroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Kotori.store.product.entity.AttrGroupEntity;
import com.Kotori.store.product.service.AttrGroupService;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.R;



/**
 * 属性分组
 *
 * @author Kotori
 * @email 543566812@qq.com
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService CategoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * Obtain attr by attrgroupId
     */
    @RequestMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> list = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", list);
    }

    /**
     * Obtain no attr by attrgroupId
     */
    @RequestMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@RequestParam Map<String, Object> params, @PathVariable("attrgroupId") Long attrgroupId){
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);

        return R.ok().put("page", page);
    }

    /**
     * Obtain no attr by attrgroupId
     */
    @RequestMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> attrGroupRelationVos){
        attrAttrgroupRelationService.saveBatch(attrGroupRelationVos);
        return R.ok();
    }

    /**
     * delete AttrGroup Relations
     */
    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] attrGroupRelationVos){
        attrService.deleteRelation(attrGroupRelationVos);

        return R.ok();
    }


    /**
     * Get all categories in page
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId){
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * Obtain attrGroup by attrGroupId
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();

        Long[] path = CategoryService.findCategoryPath(catelogId);
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
