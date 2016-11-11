/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source KeyRow
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * 按键的列数组.
 * 
 * @author hailong.qiu 356752238@qq.com
 *
 */
public class KeyRow {

	private List<SoftKey> mSoftKeys = new ArrayList<SoftKey>();

	public List<SoftKey> getSoftKeys() {
		return mSoftKeys;
	}

	public void setSoftKeys(List<SoftKey> mSoftKeys) {
		this.mSoftKeys = mSoftKeys;
	}

}
