package com.douban.guide.overlay.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qluan on 2015/3/26.
 */
public class OverlayView extends View {

    /**
     * Paint used to draw the normal bitmap
     */
    private Paint mPaint = new Paint();

    /**
     * Paint used to draw the shader view
     */
    private Paint mPaintTemp = new Paint();

    /**
     * Paint used to draw the XFer-mode view
     */
    private Paint mPaintTransparent = new Paint();

    /**
     * Temp canvas to contain the XFer-mode view
     */
    private Canvas mCanvasTemp = new Canvas();

    /**
     * Temp canvas bitmap
     */
    private Bitmap mOverlay;

    /**
     * Overlay color
     */
    private int mOverlayColor = Color.TRANSPARENT;

    private boolean mSetup = false;

    private Shape mShape;

    public OverlayView(Context context) {
        super(context);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Overlay, defStyleAttr, 0);
        mOverlayColor = a.getColor(R.styleable.Overlay_overlayColor, Color.TRANSPARENT);
        a.recycle();
    }

    /**
     * Set overlay color
     * @param color
     */
    public void setOverlayColor(int color) {
        mOverlayColor = color;
    }

    /**
     * Set overlay color resource id
     * @param colorResId
     */
    public void setOverlayColorResId(int colorResId) {
        mOverlayColor = getResources().getColor(colorResId);
    }

    /**
     * Set shade shape
     * @param shape
     */
    public void setShape(Shape shape) {
        mShape = shape;
    }

    private void setupPaint() {
        if (mSetup) {
            return;
        }
        mSetup = true;
        mOverlay = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvasTemp = new Canvas(mOverlay);
        mPaintTemp.setColor(mOverlayColor);

        mPaintTransparent = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTransparent.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setupPaint();
        mCanvasTemp.drawRect(0, 0, mCanvasTemp.getWidth(), mCanvasTemp.getHeight(), mPaintTemp);
        mShape.render(mCanvasTemp, mPaintTransparent);
        canvas.drawBitmap(mOverlay, 0, 0, mPaint);
    }

}
