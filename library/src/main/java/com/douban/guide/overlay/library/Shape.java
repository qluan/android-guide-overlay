package com.douban.guide.overlay.library;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by qluan on 2015/3/26.
 */
public interface Shape {

    public void render(Canvas canvas, Paint paint);

    public float getTop();

    public float getBottom();

    /**
     * Round shape
     */
    public static class Round implements Shape {

        public static final String KEY_X = "x";
        public static final String KEY_Y = "y";
        public static final String KEY_RADIUS = "radius";

        public float x;
        public float y;
        public float radius;

        public Round(float x, float y, float radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void render(Canvas canvas, Paint paint) {
            if (null == canvas || null == paint) {
                return;
            }
            canvas.drawCircle(this.x, this.y, this.radius, paint);
        }

        @Override
        public float getTop() {
            return x - radius;
        }

        @Override
        public float getBottom() {
            return x + radius;
        }

    }

    /**
     * Oval shape
     */
    public static class Oval implements Shape {

        public static final String KEY_LEFT = "left";
        public static final String KEY_TOP = "top";
        public static final String KEY_RIGHT = "right";
        public static final String KEY_BOTTOM = "bottom";

        public float left;
        public float top;
        public float right;
        public float bottom;

        public Oval(float left, float top, float right, float bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        @Override
        public void render(Canvas canvas, Paint paint) {
            if (null == canvas || null == paint) {
                return;
            }
            RectF rectF = new RectF(this.left, this.top, this.right, this.bottom);
            canvas.drawOval(rectF, paint);
        }

        @Override
        public float getTop() {
            return this.top;
        }

        @Override
        public float getBottom() {
            return this.bottom;
        }
    }

    /**
     * Rect shape
     */
    public static class Rect implements Shape {

        public static final String KEY_LEFT = "left";
        public static final String KEY_TOP = "top";
        public static final String KEY_RIGHT = "right";
        public static final String KEY_BOTTOM = "bottom";

        public float left;
        public float top;
        public float right;
        public float bottom;

        public Rect(float left, float top, float right, float bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        @Override
        public void render(Canvas canvas, Paint paint) {
            if (null == canvas || null == paint) {
                return;
            }
            canvas.drawRect(this.left, this.top, this.right, this.bottom, paint);
        }

        @Override
        public float getTop() {
            return this.top;
        }

        @Override
        public float getBottom() {
            return this.bottom;
        }
    }


}
