/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source NetWorkUtils
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.open.androidtvwidget.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 一些网络常用的接口.<br>
 * 记得要添加权限哈.<br>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * 
 * @author hailongqiu
 *
 */
public class NetWorkUtils {

	/**
	 * 网络是否可用
	 */
	public static boolean isNetWorkdetect(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = conn.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断有线网络.
	 */
	public static boolean checkEthernet(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conn.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
		return networkInfo.isConnected();
	}
	
}
