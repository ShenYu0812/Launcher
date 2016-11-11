/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source GridViewTV
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Scroller;

/**
 * GridView TV版本.
 *
 * @author hailongqiu 356752238@qq.com
 */
public class GridViewTV extends GridView {
    private static final String TAG = GridViewTV.class.getSimpleName();
    private Scroller mScroller;

    public GridViewTV(Context context) {
        this(context, null);
        init(context);
    }

    public GridViewTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public boolean isInTouchMode() {
        return !(hasFocus() && !super.isInTouchMode());
    }

    private void init(Context context) {
        this.setChildrenDrawingOrderEnabled(true);
        mScroller = new Scroller(context);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.getSelectedItemPosition() != -1) {
            if (i + this.getFirstVisiblePosition() == this.getSelectedItemPosition()) {// 这是原本要在最后一个刷新的item
                return childCount - 1;
            }
            if (i == childCount - 1) {// 这是最后一个需要刷新的item
                return this.getSelectedItemPosition() - this.getFirstVisiblePosition();
            }
        }
        return i;
    }

    public void setDefualtSelect(int pos) {
        requestFocusFromTouch();
        setSelection(pos);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    public void smoothScrollToNext(int position) {
        Log.d(TAG, "position=" + position);
        int start = getFirstVisiblePosition();
        int end = getLastVisiblePosition();
        Log.e(TAG, "start=" + start + ", end=" + end);
        if (position > end - 5) {
            int parentHeight = getHeight();
            View targetView = getChildAt(position);
            int top = targetView.getTop();
            int bottom = targetView.getBottom();
            Log.i(TAG, "parentHeight=" + parentHeight + ",top=" + top + ",bottom=" + bottom);
        }
        /*mScroller.startScroll(mLastX, 0, -500, 0);
        postInvalidate();

        childWidth = firstView.getWidth();
        int preLastPos = endItems[0] - 1;
        View specialView = getChildAt(preLastPos);
        if (specialView == null) return;
        specialLeft = specialView.getLeft();
        specialRight = parentWidth - specialLeft;
        Log.d(TAG, "一屏的倒数第二行位置是:" + preLastPos + ", 父容器宽度:" + parentWidth
                + ", 超出位置(极右位置):" + specialLeft + ", 不达位置(极左位置):" + specialRight);

        int targetPos = position - startItems[0];
        View targetView = getChildAt(targetPos);
        if (targetView == null) {
            Log.i(TAG, "TargetView is null!");
            return;
        }
        int targetLeft = targetView.getLeft();
        int targetRight = targetView.getRight();

        Log.d(TAG, "目标位置:" + targetPos + ", 目标左位置:" + targetLeft + ", 目标右位置:" + targetRight);

        if (targetLeft > specialLeft) {
            // 获得焦点的不全显示item将自动全显示。
            // 因此，到达极右位置只需移动下一个不全显示的偏置距离
            mLastX = targetLeft;
            mScroller.startScroll(targetLeft, 0, -childWidth / 2, 0);
            postInvalidate();
            Log.d(TAG, "<----");
        } else if (targetRight < specialRight) {
            // 到达极左位置
            mLastX = targetRight;
            mScroller.startScroll(targetRight, 0, childWidth / 2, 0);
            postInvalidate();
            Log.d(TAG, "---->");
        }*/
    }
}