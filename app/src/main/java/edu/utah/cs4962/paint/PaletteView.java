package edu.utah.cs4962.paint;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jesus Zarate on 9/15/14.
 */
public class PaletteView extends ViewGroup {


    public PaletteView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
        int width = Math.max(widthSpec, getSuggestedMinimumWidth());
        int height = Math.max(heightSpec, getSuggestedMinimumHeight());

        int childState = 0;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {

            View child = getChildAt(childIndex);
            //child.measure(MeasureSpec.AT_MOST | 75, MeasureSpec.AT_MOST | 75);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }

        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, childState),
                resolveSizeAndState(height, heightMeasureSpec, childState));
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

        int childWidthMax = 0;
        int childHeightMax = 0;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {

            View child = getChildAt(childIndex);
            if (child.getVisibility() == GONE) {

            }
            childHeightMax = Math.max(childWidthMax, child.getMeasuredWidth());
            childWidthMax = Math.max(childHeightMax, child.getMeasuredHeight());
        }

        Rect layoutRect = new Rect();
        layoutRect.left = getPaddingLeft() + childWidthMax / 2;
        layoutRect.top = getPaddingTop() + childHeightMax / 2;
        layoutRect.right = getWidth() - getPaddingRight() - childWidthMax / 2;
        layoutRect.bottom = getHeight() - getPaddingBottom() - childHeightMax / 2;

        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {

            double angle = (double) childIndex / (double) getChildCount() * 2.0 * Math.PI;
            int childCenterX = (int)(layoutRect.centerX() +  layoutRect.width() * 0.5 * Math.cos(angle));
            int childCenterY = (int)(layoutRect.centerY() + layoutRect.height() * 0.5 * Math.sin(angle));

            View child = getChildAt(childIndex);
            Rect childLayout = new Rect();
            childLayout.left = childCenterX - childWidthMax / 2;
            childLayout.top = childCenterY - childHeightMax / 2;
            childLayout.right = childCenterX + childWidthMax / 2;
            childLayout.bottom = childCenterY + childHeightMax / 2;

            child.layout(childLayout.left, childLayout.top, childLayout.right, childLayout.bottom);
        }
    }
}
