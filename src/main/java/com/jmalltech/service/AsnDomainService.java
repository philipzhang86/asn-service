package com.jmalltech.service;

import com.jmalltech.entity.Asn;
import com.jmalltech.mapper.AsnMapper;
import com.jmalltech.repository.AsnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsnDomainService {
    private AsnService service;
    private AsnMapper mapper;
    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();*/

    @Autowired
    public AsnDomainService(AsnService service, AsnMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Asn insert(Asn asn) {
        service.save(asn);
        return asn;
    }

    @Cacheable(value = "asn", key = "#id.toString()", unless = "#result == null")
    public Asn getById(Long id) {
        return service.getById(id);
    }

    @CachePut(value = "asn", key = "#asn.id.toString()", condition = "#result != null", unless = "#result == null")
    public Asn update(Asn asn) {
        boolean success = service.updateById(asn);
        if (!success) return null;
        return service.getById(asn.getId());
    }

    @CacheEvict(value = "asn", key = "#id.toString()")
    public boolean remove(Long id) {
        Asn asn = service.getById(id);
        if (asn != null) {
            service.removeById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Asn> getListByClientIdAndStatus(Long clientId, Short status) {
        return mapper.getListByClientIdAndStatusSortByArrivalTime(clientId, status);
    }

    public List<Asn> getListByClientId(Long clientId) {
        return mapper.getListByClientId(clientId);
    }

    /*public void sendAsnCreatedMessage(Asn asn) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(asn);
        kafkaTemplate.send("asn-inbound-topic", message);
    }

    @KafkaListener(topics = "asn-update-status-topic")
    public void handleInventoryUpdateMessage(String message) throws Exception {
        AsnStatusUpdate statusUpdate = objectMapper.readValue(message, AsnStatusUpdate.class);
        updateAsnStatus(statusUpdate.getAsnId(), (short)statusUpdate.getStatus());
    }

    private void updateAsnStatus(Long asnId, short status) {
        Asn asn = service.getById(asnId);
        if(asn == null) System.out.println("Asn not found for ID: " + asnId);
        else{
            asn.setStatus(status);
            service.updateById(asn);
        }
    }*/
}
