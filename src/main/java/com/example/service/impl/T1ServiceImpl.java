package com.example.service.impl;

import com.example.aspect.MetricTime;
import com.example.dao.T1Mapper;
import com.example.entity.T1;
import com.example.entity.T1Example;
import com.example.service.T1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "em")
public class T1ServiceImpl implements T1Service {

    @Autowired
    private T1Mapper t1Mapper;

    @Override
    @Caching(
            cacheable = {
                    @Cacheable(key = "'id-'+#id")
            },
            put = {
                    @CachePut(key = "'name'+#result.name", condition = "#result != null")
            }
    )
    @MetricTime("findOne") //TODO:AOP失效
    public T1 findOne(int id) {
        return t1Mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#t1.id")
    public T1 update(T1 t1) {
        t1Mapper.updateByPrimaryKey(t1);
        return t1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public void delete(int id) {
        t1Mapper.deleteByPrimaryKey(id);
    }
}
