package com.jmalltech;

import com.jmalltech.entity.Product;
import com.jmalltech.service.ProductDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductDomainService service;

    @Test
    public void test1(){
        Product product = new Product();
        product.setName("Ipad pro 2023");
        product.setClientId(2L);
        product.setSellingPriceFromDouble(1099.0);
        product.setSku("A0006");
        product.setCurrency("USD");
        service.insert(product);
        System.out.println(service.getBySku("A0006")+"\n");
    }

    @Test
    public void test2(){
        System.out.println(service.getById(1L)+"\n");
        System.out.println(service.getBySku("A0001")+ "\n");
        System.out.println(service.getByProductName("Samsung Galaxy S20 Ultra"));
    }

    @Test
    public void test3(){
        Product p = service.getById(1L);
        p.setSellingPriceFromDouble(4200.0);
        service.update(p);
        System.out.println(service.getById(1L));
    }

    @Test
    public void test4(){
        Product p = service.getBySku("A0003");
        service.remove(p.getId());
    }


    @Test
    public void test5(){
        //service.remove(11L);
        List<Product> products = service.getProductListByClientId(2L);
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
