/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source TextViewWithTTF
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;

/**
 * 一个自定义的TextView控件.
 * 加载 /system/font/** 下面的字库.
 * @author hailongqiu
 *
 */
public class TextViewWithTTF extends TextView {

    private static final TypeFaceMgr SFontMgr = new TypeFaceMgr();
    
	public TextViewWithTTF(Context context) {
		super(context);
	}

    public TextViewWithTTF(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewWithTTF);
        String str = array.getString(R.styleable.TextViewWithTTF_ttf_name);
        Typeface typeface = SFontMgr.getTypeface(str);
        setTypeface(typeface);
        array.recycle();
    }

    

    public TextViewWithTTF(Context context, String aTTFName)
    {
        super(context);
        setTypeface(SFontMgr.getTypeface(aTTFName));
    }
    
    public void setFont(String aTTFName) {
    	setTypeface(SFontMgr.getTypeface(aTTFName));
    }
}


