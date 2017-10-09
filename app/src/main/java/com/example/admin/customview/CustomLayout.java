package com.example.admin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 10/8/2017.
 */

public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context) {
        super(context);
        init(null);
    }

    public CustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

//    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        //super(context, attrs, defStyleAttr, defStyleRes);
//        init(attrs);
//    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        for (int j = 0; j < count; j++) {
            View child = getChildAt(j);

            if (child.getVisibility() == GONE)
                return;

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();
            //wrap is reach to the end
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            //do the layout
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int mLeftWidth = 0;
        int rowCount = 0;
        int deviceWidth = getMeasuredWidth();
        Log.d(TAG, "onMeasure: "+deviceWidth);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                continue;

            // Measure the child.
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            maxWidth += Math.max(maxWidth, child.getMeasuredWidth());
            mLeftWidth += child.getMeasuredWidth();

            if ((mLeftWidth / deviceWidth) > rowCount) {
                maxHeight += child.getMeasuredHeight();
                rowCount++;
            } else {
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }

        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        // Report our final dimensions.
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));

    }

    public void init(@Nullable AttributeSet attrs)
    {

    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setColor(android.graphics.Color.RED);

        canvas.drawLine(0, 0, this.getWidth() - 50, 0, paint);
        canvas.drawLine(0, 0, 0, this.getHeight() - 50, paint);
        canvas.drawLine(this.getWidth() - 50, 0, this.getWidth() - 50,
                this.getHeight() - 50, paint);
        canvas.drawLine(0, this.getHeight() - 50, this.getWidth() - 50,
                this.getHeight() - 50, paint);
    }
}
