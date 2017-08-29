package com.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by MZ on 2017/8/28.
 */
public class CGLibSingleProxy implements MethodInterceptor {
    private static CGLibSingleProxy cgLibSingleProxy = new CGLibSingleProxy();

    private CGLibSingleProxy() {
    }

    public static CGLibSingleProxy getInstance() {
        return cgLibSingleProxy;
    }

    public <T> T getProxy(Class<T> tClass) {
        return (T) Enhancer.create(tClass, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}
