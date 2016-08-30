package com.example.annomatation;

import android.app.Activity;
import android.os.Bundle;

import com.example.annotation.uitils.AnnotationInject;

/**
 * Created by liangbin on 2016/8/30.
 */
public class AnnotationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationInject.inject(this);
    }
}
