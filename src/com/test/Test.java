package com.test;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/6/8.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    String value() default "";
}
