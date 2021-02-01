package com.example.controller;

import com.example.aspect.MetricTime;
import com.example.config.ConfigProperty;
import com.example.config.CustomPetBean;
import com.example.config.Student;
import com.example.dao.T1Mapper;
import com.example.entity.T1;
import com.example.entity.T1Example;
import com.example.pojo.LombokTest;
import com.example.pojo.Person;
import com.example.service.T1Service;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private Student student;
    private ConfigProperty configProperty;
    @Autowired
    private CustomPetBean customPetBean;

    @Autowired
    private T1Mapper t1Mapper;

    @Autowired
    private T1Service t1Service;

    @RequestMapping("/")
    public String index() {
        log.info("access index");
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
        log.info("receive pet request");
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
    public ResponseEntity<String> saveT1(T1 t1) {
        return ResponseEntity.ok("" + t1Mapper.insert(t1));
    }

    @RequestMapping(value = "findT1")
    public T1 findT1(@RequestParam("id") int id) {
        return t1Service.findOne(id);
    }

    @RequestMapping(value = "queryT1")
    @MetricTime("queryT1")
    public List<T1> queryT1(@RequestBody Map<String, Object> map) {
        int pageIndex = (Integer) map.get("pageIndex");
        int pageSize = (Integer) map.get("pageSize");
        // 分页
        PageHelper.startPage(pageIndex, pageSize);
        T1Example t1Example = new T1Example();
        t1Example.createCriteria()
                .andNameLike(String.valueOf(map.get("name")));
        return t1Mapper.selectByExample(t1Example);
    }

    @RequestMapping(value="/updateT1")
    public String updateT1(@RequestBody T1 t1) {
        t1Service.update(t1);
        return "ok";
    }

    @RequestMapping("/getlmk")
    public String getLombokTest() {
        val r = new Random();
        return String.valueOf(new LombokTest(r.nextInt(), "ddd").hashCode());
    }
}
