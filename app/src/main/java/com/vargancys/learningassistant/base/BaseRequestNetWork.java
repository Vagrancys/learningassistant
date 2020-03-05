package com.vargancys.learningassistant.base;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class BaseRequestNetWork {
    private String http;
    private Object config;
    public BaseRequestNetWork(String http,Object config){
        this.http = http;
        this.config = config;
    }
    private void LinkWork(){

    }
    public Object getBean(){
        return new Object();
    }
}
