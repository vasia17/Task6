package com.example.shon.boost4;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class MyView extends View {

    private List<Measurement> measurements;

    private Paint X, Y, X1, Y1, Z;

    public MyView(Context context) {
        this(context, null);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        X = new Paint(Paint.ANTI_ALIAS_FLAG);
        X.setColor(Color.rgb(255, 153, 51));
        X.setStrokeWidth(2);
        X.setTextSize(16);

        X1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        X1.setStyle(Paint.Style.STROKE);
        X1.setColor(Color.rgb(255, 0, 0));
        X1.setStrokeWidth(getResources().getDisplayMetrics().density * 2.0f);

        Y1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        Y1.setStyle(Paint.Style.STROKE);
        Y1.setColor(Color.rgb(0, 0, 255));
        Y1.setStrokeWidth(getResources().getDisplayMetrics().density * 2.0f);

        Z = new Paint(Paint.ANTI_ALIAS_FLAG);
        Z.setStyle(Paint.Style.STROKE);
        Z.setColor(Color.rgb(0, 255, 0));
        Z.setStrokeWidth(getResources().getDisplayMetrics().density * 2.0f);
    }

    public void setSamples(List<Measurement> m) {
        this.measurements = m;
    }

    public void onViewDataChange() {
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (measurements.isEmpty()) {
            return;
        }

        final float midHeight = getHeight() / 2.0f;
        final float unitHeight = getHeight() / 20.0f;
        final float unitWidth = (float) getWidth() / 21.0f;

        canvas.drawLine(0, midHeight, getWidth(), midHeight, X);
        canvas.drawLine(unitWidth, 0, unitWidth, getHeight(), X);
        canvas.drawText("10", 8, 16, X);
        canvas.drawText("-10", 8, getHeight()-8, X);
        canvas.drawText("0", 16, midHeight-10, X);

        float x = unitWidth, y1 = midHeight, y2 = midHeight, y3 = midHeight,
                sx, sy1, sy2, sy3;

        for (int i = 0; i < measurements.size(); i++) {
            sx = x + unitWidth;

            sy1 = unitHeight * -measurements.get(i).getX() + midHeight;
            canvas.drawLine(x, y1, sx, sy1, X1);

            sy2 = unitHeight * -measurements.get(i).getY() + midHeight;
            canvas.drawLine(x, y2, sx, sy2, Y1);

            sy3 = unitHeight * -measurements.get(i).getZ() + midHeight;
            canvas.drawLine(x, y3, sx, sy3, Z);

            x = sx;
            y1 = sy1;
            y2 = sy2;
            y3 = sy3;
        }
    }
}