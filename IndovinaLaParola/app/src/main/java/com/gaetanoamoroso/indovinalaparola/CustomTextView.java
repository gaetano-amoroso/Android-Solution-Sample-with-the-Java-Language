package com.gaetanoamoroso.indovinalaparola;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {

        public CustomTextView(Context context) {
            super(context);

}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Ignora tutti gli eventi di tocco
        return false;
    }
}