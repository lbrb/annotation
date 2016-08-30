package com.example.annotation.uitils;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liangbin on 2016/8/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBaseAnnotation(listenerMethod = "setOnClickListener", listenerType = View.OnClickListener.class, callbackMethod = "onClick")
public @interface EventAnnotation {
    int[] value();
}
