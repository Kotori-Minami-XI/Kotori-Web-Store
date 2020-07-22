package com.Kotori.store.product;

import com.Kotori.store.product.entity.BrandEntity;
import com.Kotori.store.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreProductApplicationTest {
    @Autowired
    private BrandService brandService;

    @Test
    public void testInsertBrand() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("huawei");
        brandService.save(brandEntity);
    }

    @Test
    public void testUpdateBrand() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setName("hanhan");
        brandService.updateById(brandEntity);
    }
}
