package com.example.pojo;

import java.util.List;

public class A<T> {

    private int num;
    private String Str;
    private List<T> data;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return Str;
    }

    public void setStr(String str) {
        Str = str;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
