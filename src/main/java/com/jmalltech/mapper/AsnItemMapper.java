package com.jmalltech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmalltech.entity.AsnItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author philipzhang
* @description 针对表【mwms_asn_item】的数据库操作Mapper
* @createDate 2024-04-12 09:27:14
* @Entity com.jmalltech.entity.AsnItem
*/
public interface AsnItemMapper extends BaseMapper<AsnItem> {
    List<AsnItem> selectAsnItemListByAsnId(Long asnId) ;
    @Transactional
    boolean deleteAsnItemsByAsnId(Long asnId);

}




