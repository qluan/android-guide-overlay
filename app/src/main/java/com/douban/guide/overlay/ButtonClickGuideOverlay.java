package com.douban.guide.overlay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.douban.guide.overlay.library.AbsGuideOverlay;
import com.douban.guide.overlay.library.OverlayView;
import com.douban.guide.overlay.library.Shape;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by qluan on 2015/3/27.
 */
public class ButtonClickGuideOverlay extends RelativeLayout implements AbsGuideOverlay, View.OnClickListener{

    @InjectView(R.id.overlay_view)
    public OverlayView mOverlayView;
    @InjectView(R.id.overlay_content)
    public View mContent;

    @InjectView(R.id.overlay_content_image)
    public ImageView mImageView;
    @InjectView(R.id.overlay_content_text)
    public TextView mMessage;

    public ButtonClickGuideOverlay(Context context) {
        super(context);
        init();
    }

    public ButtonClickGuideOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonClickGuideOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.view_overlay, this, true);
        ButterKnife.inject(this, this);

        setOnClickListener(this);
    }

    @Override
    public void hide() {
        setVisibility(View.GONE);
    }

    @Override
    public void show(Shape shape) {
        if (null == shape) {
            return;
        }
        resetContent(shape);
        setVisibility(View.VISIBLE);
    }

    private void resetContent(Shape shape) {
        mOverlayView.setShape(shape);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mContent.getLayoutParams();
        if (null == layoutParams) {
            layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        layoutParams.topMargin = (int) shape.getBottom();
        mContent.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        if (null == v) {
            return;
        }
        hide();
    }
}
