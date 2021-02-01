package com.example.pojo;

public class B extends A<C> {
    private String bv;

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }
}
