package com.example.annotation.uitils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liangbin on 2016/8/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBaseAnnotation {
    String listenerMethod();
    Class listenerType();
    String callbackMethod();

}
