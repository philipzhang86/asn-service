package com.jmalltech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmalltech.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author philipzhang
* @description 针对表【mwms_product】的数据库操作Mapper
* @createDate 2024-04-12 09:53:28
* @Entity com.jmalltech.entity.Product
*/
public interface ProductMapper extends BaseMapper<Product> {
    Product selectByIdAndClientId(@Param("id") Long id, @Param("clientId") Long clientId);

    @Select("SELECT * FROM public.mwms_product WHERE name = #{name}")
    Product selectByProductName(@Param("name") String productName);

    Product selectByNameAndClientId(@Param("name") String name, @Param("clientId") Long clientId);

    @Select("SELECT * FROM public.mwms_product WHERE sku = #{sku}")
    Product selectProductBySku(@Param("sku") String sku);

    Product selectBySkuAndClientId(@Param("sku") String sku, @Param("clientId") Long clientId);

    List<Product> selectProductsByClientId(@Param("clientId") Long clientId);


}




