package com.example.service.impl;

import com.example.dao.T1Mapper;
import com.example.entity.T1;
import com.example.service.T1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "em")
public class T1ServiceImpl implements T1Service {

    @Autowired
    private T1Mapper t1Mapper;

    @Override
    @Cacheable(key = "#p0")
    public T1 findOne(int id) {
        return t1Mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#p0.id")
    public boolean update(T1 t1) {
        return t1Mapper.updateByPrimaryKey(t1) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#p0")
    public void delete(int id) {
        t1Mapper.deleteByPrimaryKey(id);
    }
}
