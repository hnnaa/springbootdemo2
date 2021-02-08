package com.example.enums;

/**
 * @author hnn
 * @date 2021/02/01
 */
public enum ColorEnum {
    BLUE("1"),
    YELLOW("2"),
    RED("3");

    private String value;

    ColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
