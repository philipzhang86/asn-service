package com.jmalltech;

import com.jmalltech.entity.Asn;
import com.jmalltech.service.AsnDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AsnTest {
    @Autowired
    private AsnDomainService service;

    @Test
    public void test1(){
//        Asn asn = new Asn();
//        asn.setClientId(1L);
//        asn.setExpectedArrivalTime(new Date(2024,4,30));
//        asn.setStatus((short) 1);
        //service.insert(asn);
        Asn asn = service.getById(1L);
        LocalDate date = LocalDate.of(2024, 4, 30);
        Date dateConverted = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        asn.setExpectedArrivalTime(dateConverted);
        service.update(asn);

        System.out.println(service.getById(1L).getExpectedArrivalTime());
    }

    @Test
    public void test2(){
        Asn asn = new Asn();
        asn.setClientId(1L);
        LocalDate date = LocalDate.of(2024, 5, 30);
        Date dateConverted = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        asn.setExpectedArrivalTime(dateConverted);
        asn.setStatus((short) 1);
        service.insert(asn);
        System.out.println(service.getById(3L));
    }

    @Test
    public void test3(){
        List<Asn> list = service.getListByClientIdAndStatus(1L, (short) 1);
        for (Asn asn : list) {
            System.out.println(asn);
        }
    }

    @Test
    public void test4(){
        Asn asn = service.getById(1L);
        System.out.println(asn);
    }
}
