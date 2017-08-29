package com.test.proxy;

import java.text.ParseException;

/**
 * Created by MZ on 2017/8/28.
 */
public class ProxyMain {

    public static void main(String[] args) throws ParseException {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("Zhang San");

        Hello hello1 = CGLibSingleProxy.getInstance().getProxy(HelloImpl.class);
        String name = hello1.say("Li Si");
        System.out.println(name);
    }
}
