/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source SoftKeyBoardListener
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.keyboard;

public interface SoftKeyBoardListener {
	public void onCommitText(SoftKey key);
	public void onDelete(SoftKey key);
	public void onBack(SoftKey key);
}
