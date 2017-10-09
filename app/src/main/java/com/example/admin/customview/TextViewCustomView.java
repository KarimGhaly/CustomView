package com.example.admin.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Admin on 10/8/2017.
 */

public class TextViewCustomView extends android.support.v7.widget.AppCompatEditText {
    public TextViewCustomView(Context context) {
        super(context);
        init(null);
    }

    public TextViewCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextViewCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public void init(@Nullable  AttributeSet set)
    {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setTextColor(color);
        return super.onKeyDown(keyCode, event);
    }
}
