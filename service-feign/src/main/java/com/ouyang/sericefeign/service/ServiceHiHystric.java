package com.ouyang.sericefeign.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceHiHystric implements ServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}