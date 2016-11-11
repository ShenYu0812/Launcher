/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source TypeFaceMgr
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.view;

import android.graphics.Typeface;

import java.util.HashMap;

class TypeFaceMgr {
    private HashMap<String, Typeface> mTypefaces;

    TypeFaceMgr() {
        mTypefaces = new HashMap<>();
    }

    Typeface getTypeface(String aTTFName) {
        if(mTypefaces.containsKey(aTTFName)) {
            return mTypefaces.get(aTTFName);
        } else {
            Typeface font;
            try {
                String path = "/system/fonts/"+aTTFName;
                font = Typeface.createFromFile(path);
                if (font != null) {
                    mTypefaces.put(aTTFName, font);
                    return font;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
