package com.aluxian.loremui.recycler;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class LoremImagesDecoration extends RecyclerView.ItemDecoration {

    private static final DisplayMetrics DM = Resources.getSystem().getDisplayMetrics();
    private static final int MARGIN = toPx(8);
    private static final int TOOLBAR_HEIGHT_PORTRAIT = toPx(56);
    private static final int TOOLBAR_HEIGHT_LANDSCAPE = toPx(48);

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition();

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.left = MARGIN;
            outRect.right = MARGIN;
            outRect.top = position == 0 ? TOOLBAR_HEIGHT_PORTRAIT + MARGIN : 0;
            outRect.bottom = MARGIN;
        } else {
            outRect.left = position == 0 ? MARGIN : 0;
            outRect.right = MARGIN;
            outRect.top = TOOLBAR_HEIGHT_LANDSCAPE + MARGIN;
            outRect.bottom = MARGIN;
        }
    }

    private static int toPx(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, DM));
    }

}
