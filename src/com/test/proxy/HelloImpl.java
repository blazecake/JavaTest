package com.test.proxy;

/**
 * Created by MZ on 2017/8/28.
 */
public class HelloImpl implements Hello {

    @Override
    public String say(String name) {
        System.out.println("Hello " + name);
        return name;
    }
}
