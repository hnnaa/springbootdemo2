package com.example.controller;

import com.example.config.*;
import com.example.dao.T1Mapper;
import com.example.entity.T1;
import com.example.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Student student;
    private ConfigProperty configProperty;
    @Autowired
    private CustomPetBean customPetBean;

    @Autowired
    private T1Mapper t1Mapper;

    @RequestMapping("/")
    public String index() {
        logger.info("access index");
        return "Index";
    }

    @RequestMapping(value = {"/pick", "/person"})
    public Person pick() {
        return new Person("a", 1, LocalDate.of(2000, 1, 1));
    }

    @RequestMapping("/student")
    public String student() {
        return student.getId() + ";" + student.getName();
    }

    @RequestMapping("/config")
    public String config() {
        return configProperty.getId() + ";" + configProperty.getValue() + ";" + configProperty.getRandom();
    }

    @RequestMapping("/pet")
    public String getPet() {
        logger.info("receive pet request");
        return customPetBean.getName() + ";" + customPetBean.getNo() + ";" + customPetBean.getNickName();
    }

    //set方式注入，不报警，因为体现了类不强行依赖于容器内的对象
    @Autowired
    public void setConfigProperty(ConfigProperty configProperty) {
        this.configProperty = configProperty;
    }

    //请求中的参数 http://localhost:8888/hello?name=cc
    @GetMapping(value = "/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "hello:" + name;
    }

    //请求中的参数 http://localhost:8888/hello/jj
    @GetMapping(value = "/hello2/{name}")
    public String sayHello2(@PathVariable("name") String name) {
        return "hello2:" + name;
    }

    @GetMapping(value = "/test/error")
    public String testError() {
        int a = 1;
        int b = 0;
        return String.valueOf(a / b);
    }

    @RequestMapping(value = "/saveT1")
    public String saveT1(@RequestParam("name") String name) {
        T1 t = new T1();
        t.setName(name);
        return ""+t1Mapper.insert(t);
    }
}
