package com.ouyang.sericefeign.controller;

import com.ouyang.sericefeign.service.ServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    ServiceHi serviceHi;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return serviceHi.sayHiFromClientOne(name)+",feign";
    }
}