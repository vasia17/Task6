package com.example.shon.boost4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MyView extends View {
    Paint paint;
    Path path;
    Paint paint2;
    Path path2;
    Paint paint3;
    Path path3;
    Firebase ref = new Firebase("https://blistering-inferno-8458.firebaseio.com/parameters");
    public MyView(Context context) {
        super(context);
        init();
        init2();
        init3();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        init2();
        init3();
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        init2();
        init3();
    }
    private static class Parameters {
        float x;
        float y;
        float z;
        public Parameters() {
        }
        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }
        public float getZ() {
            return z;
        }

    }
    private void init() {
        paint = new Paint();
        paint.setColor(0xFFFF0000);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        path = new Path();
        path.lineTo(0, 0);
        path.lineTo(25, 250);
        path.lineTo(100, 250);
        path.lineTo(100, 150);
        path.lineTo(175, 175);

    }
    private void init2() {
        paint2 = new Paint();
        paint2.setColor(0xFF0000FF);
        paint2.setStrokeWidth(3);
        paint2.setStyle(Paint.Style.STROKE);

        path2 = new Path();
        path2.lineTo(200, 200);
        path2.lineTo(50, 500);
        path2.lineTo(200, 500);
        path2.lineTo(200, 300);
        path2.lineTo(350, 300);

    }
    private void init3() {
        paint3 = new Paint();
        paint3.setColor(0xFF00FF00);
        paint3.setStrokeWidth(3);
        paint3.setStyle(Paint.Style.STROKE);

        path3 = new Path();
        path3.lineTo(100, 25);
        path3.lineTo(150, 50);
        path3.lineTo(200, 75);
        path3.lineTo(250, 100);
        path3.lineTo(300, 125);

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint2);
        canvas.drawPath(path3, paint3);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(720, 500);
    }
}
