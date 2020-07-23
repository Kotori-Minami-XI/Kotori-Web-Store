package com.Kotori.store.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Kotori.common.utils.PageUtils;
import com.Kotori.common.utils.Query;

import com.Kotori.store.product.dao.CategoryDao;
import com.Kotori.store.product.entity.CategoryEntity;
import com.Kotori.store.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listCategoryTree() {
        // Obtain all categories
        List<CategoryEntity> allEntities = baseMapper.selectList(null);

        // Obtain categories in tree format
        List<CategoryEntity> categoryTree = new ArrayList();
        for (CategoryEntity entity : allEntities) {
            if (entity.getParentCid() == 0) {
                categoryTree.add(entity);
                entity.setChildren(findCategoryChildren(entity, allEntities));
            }
        }
        return categoryTree;
    }

    /***
     * Find children Category in regression
     * Terminate the regression once category level is beyond 3 since there are only three levels of classes
     */
    private List<CategoryEntity> findCategoryChildren(CategoryEntity parentEntity, List<CategoryEntity> allEntity) {
        if (parentEntity.getCatLevel() >= 3) {
            return null;
        }
        List<CategoryEntity> childList = new ArrayList();
        for (CategoryEntity entity : allEntity) {
            if (entity.getParentCid() == parentEntity.getCatId()) {
                childList.add(entity);
                entity.setChildren(findCategoryChildren(entity, allEntity));
            }
        }
        return childList;
    }
}