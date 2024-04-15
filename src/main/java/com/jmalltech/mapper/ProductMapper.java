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
    @Select("SELECT * FROM public.mwms_product WHERE name = #{name}")
    Product selectByProductName(@Param("name") String productName);

    @Select("SELECT * FROM public.mwms_product WHERE sku = #{sku}")
    Product selectProductBySku(@Param("sku") String sku);

    List<Product> selectProductsByClientId(@Param("clientId") Long clientId);

}




