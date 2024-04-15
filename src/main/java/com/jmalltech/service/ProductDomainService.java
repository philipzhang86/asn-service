package com.jmalltech.service;

import com.jmalltech.entity.Product;
import com.jmalltech.mapper.ProductMapper;
import com.jmalltech.repository.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductDomainService {
    private ProductService service;
    private ProductMapper mapper;
    private CacheManager cacheManager;

    @Autowired
    public ProductDomainService(ProductService service, ProductMapper mapper, CacheManager cacheManager) {
        this.service = service;
        this.mapper = mapper;
        this.cacheManager = cacheManager;
    }

    public Product insert(Product product) {
        if(service.save(product)){
            Objects.requireNonNull(cacheManager.getCache("productList")).evict(product.getClientId());
        }
        return product;
    }

    @Cacheable(value = "product", key = "#id", unless = "#result == null")
    public Product getById(Long id) {
        return service.getById(id);
    }

    @Cacheable(value = "product", key = "#sku", unless = "#result == null")
    public Product getBySku(String sku) {
        return mapper.selectProductBySku(sku);
    }

    @Cacheable(value = "product", key = "#name", unless = "#result == null")
    public Product getByProductName(String name) {
        return mapper.selectByProductName(name);
    }

    @Caching(
            put = {
                    @CachePut(value = "product", key = "#product.id", condition = "#result != null", unless = "#result == null"),
                    @CachePut(value = "product", key = "#product.sku", condition = "#result != null", unless = "#result == null"),
                    @CachePut(value = "product", key = "#product.name", condition = "#result != null", unless = "#result == null")}
    )
    public Product update(Product product) {
        boolean success = service.updateById(product);
        if (success) {
            Objects.requireNonNull(cacheManager.getCache("productList")).evict(product.getClientId());
            service.getById(product.getId());
        }
        return null;
    }


    @Caching(evict = {
            @CacheEvict(value = "product", key = "#id"),
    })
    public boolean remove(Long id) {
        Product product = service.getById(id);
        if (product != null) {
            Objects.requireNonNull(cacheManager.getCache("product")).evict(product.getSku());
            Objects.requireNonNull(cacheManager.getCache("product")).evict(product.getName());
            Objects.requireNonNull(cacheManager.getCache("productList")).evict(product.getClientId());
        }
        return service.removeById(id);
    }

    @Cacheable(value = "productList", key = "#clientId", unless = "#result == null || #result.isEmpty()")
    public List<Product> getProductListByClientId(Long clientId) {
        return mapper.selectProductsByClientId(clientId);
    }
}
