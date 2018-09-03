package com.shenma.model;

interface BaseEnum {
    int getCode();
}

public enum  adsenseType implements BaseEnum{
    PC(1,"PC"),
    APP(2,"APP");

    private int code;
    private String desc;
    adsenseType(int code, String desc) {
        this.code=code;
        this.desc=desc;
    }
    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
