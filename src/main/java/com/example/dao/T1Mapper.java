package com.example.dao;

import com.example.entity.T1;

public interface T1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(T1 record);

    int insertSelective(T1 record);

    T1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T1 record);

    int updateByPrimaryKey(T1 record);
}