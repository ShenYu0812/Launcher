/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source SpaceItemDecoration
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int right, left, top, bottom;

    public SpaceItemDecoration(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public SpaceItemDecoration(Rect outRect) {
        this.top = outRect.top;
        this.right = outRect.right;
        this.bottom = outRect.bottom;
        this.left = outRect.left;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = top;
        outRect.right = right;
        outRect.bottom = bottom;
        outRect.left = left;
    }
}
