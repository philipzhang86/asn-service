package com.jmalltech.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmalltech.entity.Asn;

import java.util.List;

/**
* @author philipzhang
* @description 针对表【mwms_asn】的数据库操作Mapper
* @createDate 2024-04-11 16:50:39
* @Entity com.jmalltech.domain.Asn
*/
public interface AsnMapper extends BaseMapper<Asn> {

    List<Asn> getListByClientIdAndStatusSortByArrivalTime(Long clientId, short status);
}




