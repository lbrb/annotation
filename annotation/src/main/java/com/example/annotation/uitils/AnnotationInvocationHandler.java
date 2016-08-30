package com.example.annotation.uitils;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liangbin on 2016/8/30.
 */
public class AnnotationInvocationHandler implements InvocationHandler {

    private Activity activity;
    private Method mMethod;
    AnnotationInvocationHandler(Activity activity, Method method){
        this.activity = activity;
        this.mMethod = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("asdf", "invoke: ");
        if (mMethod!= null){
            return mMethod.invoke(activity,args);
        }else {
            return method.invoke(proxy, args);
        }
    }
}
