package com.jmalltech;

import com.jmalltech.entity.AsnItem;
import com.jmalltech.entity.Product;
import com.jmalltech.service.AsnItemDomainService;
import com.jmalltech.service.ProductDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AsnItemTest {
    @Autowired
    private AsnItemDomainService service;
    @Autowired
    private ProductDomainService productService;

    @Test
    public void test1(){
        AsnItem aItem1 = new AsnItem();
        aItem1.setAsnId(1L);
        aItem1.setSkuId("A0003");
        aItem1.setQuantity(50);
        aItem1.setAsnItemName(productService.getBySku("A0003").getName());
        aItem1.setProductId(productService.getBySku("A0003").getId());
        service.insert(aItem1);
    }

    @Test
    public void test2(){
        AsnItem aItem2 = service.getById(1L);
        aItem2.setAsnItemName(productService.getById(1L).getName());
        service.update(aItem2);
        System.out.println(aItem2);
    }

    @Test
    public void test3(){
        List<AsnItem> list = service.getAsnItemListFromAsnId(1L);
        for (AsnItem aItem : list) {
            System.out.println(aItem);
        }
    }

    @Test
    public void test4(){
        Product p1 = new Product();
        p1.setSku("A0005");
        p1.setName("Asus G16");
        p1.setSellingPriceFromDouble(2799.0);
        p1.setClientId(1L);
        p1.setCurrency("USD");
        productService.insert(p1);
    }

    @Test
    public void test5(){
        AsnItem aItem3 = new AsnItem();
        aItem3.setAsnId(2L);
        aItem3.setSkuId("A0005");
        aItem3.setQuantity(99);
        aItem3.setAsnItemName(productService.getBySku("A0005").getName());
        aItem3.setProductId(productService.getBySku("A0005").getId());
        service.insert(aItem3);
    }

    @Test
    public void test6(){
        System.out.println(service.removeAsnItemsByAsnId(2L));
    }

    @Test
    public void test7(){
        System.out.println(service.getAsnItemListFromAsnId(1L));
    }

    @Test
    public void test8(){
        System.out.println(service.getAsnItemBySkuAndAsnId("A0001", 1L));
    }

}
