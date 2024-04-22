package com.jmalltech.client;

import com.alibaba.fastjson2.JSON;
import com.jmalltech.entity.Asn;
import com.jmalltech.service.AsnDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryServiceListener {
    @Autowired
    private AsnDomainService asnDomainService;

    @KafkaListener(topics = "asn-service-topic2")
    private void listen(String message) {
        if (!message.isEmpty()) {
            Asn asn = JSON.parseObject(message, Asn.class);
            asnDomainService.update(asn);
        }
    }
}
