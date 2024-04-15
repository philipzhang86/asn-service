package com.jmalltech.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jmalltech.entity.AsnItem;
import com.jmalltech.mapper.AsnItemMapper;
import com.jmalltech.repository.AsnItemService;
import org.springframework.stereotype.Service;

/**
* @author philipzhang
* @description 针对表【mwms_asn_item】的数据库操作Service实现
* @createDate 2024-04-12 09:27:14
*/
@Service
public class AsnItemServiceImpl extends ServiceImpl<AsnItemMapper, AsnItem>
    implements AsnItemService{

}




