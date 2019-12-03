package com.lihao.stubbing;

public class StubbingService {

    public String getS() {
        System.out.println("get s");
        throw new RuntimeException();
    }
    public Integer getI() {
        return Integer.valueOf(10);
    }
}
