/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source MenuSetObserver
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.menu;

/**
 * Created by hailongqiu on 2016/6/2.
 */
public abstract class MenuSetObserver {
    public void onShow(OpenMenu openMenu) {
    }

    public void onHide(OpenMenu openMenu) {
    }

    public void onChanged(OpenMenu openMenu) {
    }

    public void onChanged() {
    }

    public void onInvalidated() {
    }
}
