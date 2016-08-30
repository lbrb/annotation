package com.example.annotation.uitils;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liangbin on 2016/8/30.
 */
public class AnnotationInject {
    public static void inject(Activity activity) {
        //布局
        inject_layout(activity);

        //属性
        inject_field(activity);

        //事件
        inject_event(activity);
    }

//    view.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    });
//    private static void inject_event(final Activity activity) {
//        Method[] methods = activity.getClass().getDeclaredMethods();
//        for (final Method method :
//                methods) {
//            AnnotationInvocationHandler[] annotations = method.getAnnotations();
//            for (AnnotationInvocationHandler annotation :
//                    annotations) {
//                if (annotation.annotationType() == EventAnnotation.class){
//                    int[] ids = ((EventAnnotation)annotation).value();
//                    for (int id :
//                            ids) {
//                        View v = activity.findViewById(id);
//                        v.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Log.d("asdf", "onClick: ");
//                                try {
//                                    method.invoke(activity,v);
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                } catch (InvocationTargetException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//
//                    }
//                }
//            }
//        }
//    }

    private static void inject_event(final Activity activity) {
        try {
            Method[] methods = activity.getClass().getDeclaredMethods();
            for (final Method method :
                    methods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation :
                        annotations) {
                    if (annotation.annotationType() == EventAnnotation.class) {
                        EventAnnotation eventAnnotation = (EventAnnotation) annotation;
                        EventBaseAnnotation eventBaseAnnotation = eventAnnotation.annotationType().getAnnotation(EventBaseAnnotation.class);
                        if (eventBaseAnnotation!=null){
                            Class listener_type = eventBaseAnnotation.listenerType();
                            //setOnClickListener(new View.OnClickListener()
                            String listener_method = eventBaseAnnotation.listenerMethod();
                            //onClick(View v)
                            String callback = eventBaseAnnotation.callbackMethod();

                            int[] ids = ((EventAnnotation) annotation).value();
                            for (int id :
                                    ids) {
                                View v = activity.findViewById(id);

                                Object proxy = Proxy.newProxyInstance(listener_type.getClassLoader(), new Class[]{listener_type}, new AnnotationInvocationHandler(activity, method));
                                Method setListenerMethod = v.getClass().getMethod(listener_method, listener_type);
                                setListenerMethod.invoke(v, proxy);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void inject_field(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field :
                fields) {
            FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
            if (fieldAnnotation != null) {
                field.setAccessible(true);
                try {
                    field.set(activity, activity.findViewById(fieldAnnotation.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void inject_layout(Activity activity) {
        Annotation[] annotations = activity.getClass().getAnnotations();
        for (Annotation annotation :
                annotations) {
            if (annotation.annotationType() == LayoutAnnotation.class) {
                activity.setContentView(((LayoutAnnotation) annotation).value());
            }
        }
    }
}
