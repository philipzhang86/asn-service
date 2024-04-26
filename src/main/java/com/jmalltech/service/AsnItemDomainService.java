package com.jmalltech.service;

import com.jmalltech.entity.AsnItem;
import com.jmalltech.mapper.AsnItemMapper;
import com.jmalltech.repository.AsnItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsnItemDomainService {
    private AsnItemService service;
    private AsnItemMapper mapper;

    @Autowired
    public AsnItemDomainService(AsnItemService service, AsnItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public AsnItem insert(AsnItem asnItem) {
        service.save(asnItem);
        return asnItem;
    }

    public AsnItem getById(Long id) {
        return service.getById(id);
    }

    public AsnItem update(AsnItem asnItem) {
        boolean success = service.updateById(asnItem);
        if (!success) return null;
        return asnItem;
    }
    public boolean remove(Long id) {
        return service.removeById(id);
    }

    public boolean removeAsnItemsByAsnId(Long asnId) {
        return mapper.deleteAsnItemsByAsnId(asnId);
    }

    public List<AsnItem> getAsnItemListFromAsnId(Long asnId) {
        return mapper.selectAsnItemListByAsnId(asnId);
    }

    public AsnItem getAsnItemBySkuAndAsnId(String sku, Long asnId) {
        return mapper.selectAsnItemBySkuIdAndAsnId(sku, asnId);
    }


}
