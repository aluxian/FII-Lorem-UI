package com.aluxian.loremui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aluxian.loremui.R;

public class FixedAspectRatioImageView extends ImageView {

    private int mAspectRatioWidth = 4;
    private int mAspectRatioHeight = 3;

    public FixedAspectRatioImageView(Context context) {
        super(context);
    }

    public FixedAspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FixedAspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioView);

        mAspectRatioWidth = array.getInt(R.styleable.FixedAspectRatioView_aspectRatioWidth, mAspectRatioWidth);
        mAspectRatioHeight = array.getInt(R.styleable.FixedAspectRatioView_aspectRatioHeight, mAspectRatioHeight);

        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int originalHeight = MeasureSpec.getSize(heightMeasureSpec);
        int calculatedWidth, calculatedHeight;

        if (originalHeight == 0) {
            calculatedWidth = originalWidth;
            calculatedHeight = originalWidth * mAspectRatioHeight / mAspectRatioWidth;
        } else {
            calculatedWidth = originalHeight * mAspectRatioWidth / mAspectRatioHeight;
            calculatedHeight = originalHeight;
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(calculatedWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(calculatedHeight, MeasureSpec.EXACTLY));
    }

}
