package com.example.liangbin.annotation;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.annomatation.AnnotationActivity;
import com.example.annotation.uitils.EventAnnotation;
import com.example.annotation.uitils.FieldAnnotation;
import com.example.annotation.uitils.LayoutAnnotation;

@LayoutAnnotation(R.layout.activity_main)
public class MainActivity extends AnnotationActivity {

    @FieldAnnotation(R.id.button)
    private Button button;

    @EventAnnotation(R.id.button)
    public void show(View view){
        Toast.makeText(MainActivity.this,"toast", Toast.LENGTH_LONG).show();
    }
}
