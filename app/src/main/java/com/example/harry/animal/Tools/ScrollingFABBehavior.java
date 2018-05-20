package com.example.harry.animal.Tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import com.example.harry.animal.R;

/**
 * Created by Harry on 2017/5/13.
 */
public class ScrollingFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    private int toolbarHeight = 0;

    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            float ratio = (float)dependency.getY()/(float)toolbarHeight;
            fab.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}
