/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source DrawUtils
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.utils;

import android.graphics.Path;
import android.graphics.RectF;

public class DrawUtils {

	public static Path addRoundPath3(int width, int height, float radius) {
		Path path = new Path();
		path.addRoundRect(new RectF(0, 0, width, height), radius, radius, Path.Direction.CW);
		return path;
	}
	
}
