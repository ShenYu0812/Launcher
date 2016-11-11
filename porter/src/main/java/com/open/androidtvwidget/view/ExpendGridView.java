/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source ExpendGridView
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
import android.widget.GridView;

/**
 * 扩展Gridview.必需嵌套滚动窗口.
 * @author hailongqiu
 *
 */
public class ExpendGridView extends GridView {
	public ExpendGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ExpendGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
