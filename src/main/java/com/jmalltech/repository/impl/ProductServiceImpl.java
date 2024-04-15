package com.jmalltech.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jmalltech.entity.Product;
import com.jmalltech.repository.ProductService;
import com.jmalltech.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author philipzhang
* @description 针对表【mwms_product】的数据库操作Service实现
* @createDate 2024-04-12 09:53:28
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




