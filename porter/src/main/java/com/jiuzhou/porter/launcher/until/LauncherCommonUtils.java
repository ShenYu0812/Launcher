/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source LauncherCommonUtils
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.until;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.jiuzhou.porter.launcher.model.AppBean;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.ApplicationInfo.FLAG_SYSTEM;

public class LauncherCommonUtils {
    private static final String TAG = LauncherCommonUtils.class.getSimpleName();

    public static List<AppBean> getAllApk(Context context) {
        List<AppBean> appBeanList = new ArrayList<>();
        AppBean bean;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : list) {
            bean = new AppBean();
            bean.setAppIcon(resolveInfo.activityInfo.loadIcon(packageManager));
            bean.setAppName(resolveInfo.activityInfo.loadLabel(packageManager).toString());

            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;
            bean.setAppPackageName(packageName);
            bean.setAppLaunchActivityName(activityName);

            int flags = resolveInfo.activityInfo.flags;
            //判断是否是属于系统的apk
            if ((flags & FLAG_SYSTEM) != 0){
                bean.setSystem(true);
            } else {
                bean.setSd(true);
            }
            appBeanList.add(bean);
        }
        return appBeanList;
    }

}
