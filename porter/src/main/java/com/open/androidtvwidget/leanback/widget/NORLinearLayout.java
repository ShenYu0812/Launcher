/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source NORLinearLayout
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 优化过的LinerLayout. --> hasOverlappingRendering (false)
 * Created by hailongqiu on 2016/8/22.
 */
public class NORLinearLayout extends LinearLayout {
    public NORLinearLayout(Context context) {
        this(context, null);
    }

    public NORLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NORLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
