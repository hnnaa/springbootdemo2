package com.example.service;

import com.example.entity.T1;

public interface T1Service {

    T1 findOne(int id);

    boolean update(T1 t1);

    void  delete(int id);
}
