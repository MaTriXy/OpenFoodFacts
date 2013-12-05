package com.flavienlaurent.openfoodfacts.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by f.laurent on 08/11/13.
 */
public class UnderlineTextView extends TextView {

    private Paint mPaint;
    private int mLineTickness = 2;
    private int mLineColor = Color.BLACK;

    public UnderlineTextView(Context context) {
        this(context, null);
    }

    public UnderlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializePaint();
    }

    public void setLineTickness(int lineTickness) {
        this.mLineTickness = lineTickness;
    }

    public void setLineColor(int lineColor) {
        this.mLineColor = lineColor;
        mPaint.setColor(mLineColor);
    }

    private void initializePaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mLineColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, getHeight() - mLineTickness, getWidth(), getHeight(), mPaint);
    }
}
