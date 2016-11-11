/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source MainActivity
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.adapter.AppRecyclerAdapter;
import com.jiuzhou.porter.launcher.model.AppBean;
import com.jiuzhou.porter.launcher.until.LauncherCommonUtils;
import com.jiuzhou.porter.launcher.widget.SimpleRecycleView;
import com.jiuzhou.porter.launcher.widget.SpaceItemDecoration;
import com.open.androidtvwidget.bridge.BaseEffectBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.view.GridMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;

import java.util.List;

import static android.view.KeyEvent.KEYCODE_DPAD_CENTER;
import static android.view.KeyEvent.KEYCODE_DPAD_LEFT;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements View.OnClickListener, AppRecyclerAdapter.OnItemClickListener,
        ViewTreeObserver.OnGlobalFocusChangeListener, AppRecyclerAdapter.OnItemKeyListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainUpView mUpView;
    private SimpleRecycleView mRecyclerView;
    private View mOldFocus;
    private GridMainLayout mGridLayout;
    private Drawable rectDrawable, shadowDrawable;
    private View module10;
    private Handler mHandler;
    private List<AppBean> mListOfApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmoothHorizontalScrollView mScrollView = (SmoothHorizontalScrollView) findViewById(R.id.home_scroll);
        mGridLayout = (GridMainLayout) findViewById(R.id.home_grid);
        module10 = findViewById(R.id.module10);

        mRecyclerView = (SimpleRecycleView) findViewById(R.id.home_apps);
        mListOfApps = LauncherCommonUtils.getAllApk(this);
        AppRecyclerAdapter mAdapter = new AppRecyclerAdapter(this, mListOfApps);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置布局管理器:瀑布流式
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3 , StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        int right = (int) getResources().getDimension(R.dimen.px_positive_5);
        int bottom = (int) getResources().getDimension(R.dimen.px_positive_1);
        RecyclerView.ItemDecoration spacingInPixel = new SpaceItemDecoration(0, right, bottom, 0);
        mRecyclerView.addItemDecoration(spacingInPixel);
        mRecyclerView.setAdapter(mAdapter);

        mUpView = (MainUpView) findViewById(R.id.home_up);
        BaseEffectBridge baseEffectBridge = mUpView.getEffectBridge();
        rectDrawable = baseEffectBridge.getUpRectDrawable();
        shadowDrawable = baseEffectBridge.getShadowDrawable();

        ViewTreeObserver observer = mGridLayout.getViewTreeObserver();
        observer.addOnGlobalFocusChangeListener(this);

        setOnClickListener(this, R.id.module1,R.id.module2,R.id.module3,
                R.id.module4,R.id.module5,R.id.module6,R.id.module7,R.id.module8,R.id.module9);

        mAdapter.setOnItemSelectListener(new AppRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {
                mRecyclerView.smoothHorizontalScrollToNext(position);
            }
        });

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemKeyListener(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 100:
                        mUpView.setEffectBridge(null);
                        mUpView.setEnabled(false);
                        mRecyclerView.getChildAt(0).requestFocus();
                        break;
                    case 101:
                        mUpView.setEnabled(true);
                        int pos = (int) msg.obj;
                        int index = 6;
                        if (pos == 2) {
                            index = 8;
                        }
                        View newFocus = mGridLayout.getChildAt(index);
                        if (newFocus != null) {
                            newFocus.bringToFront();
                            newFocus.requestFocus();
                            BaseEffectBridge baseEffectBridge = new OpenEffectBridge();
                            baseEffectBridge.onInitBridge(mUpView);
                            baseEffectBridge.setMainUpView(mUpView);
                            mUpView.setUpRectDrawable(rectDrawable);
                            mUpView.setShadowDrawable(shadowDrawable);
                            mUpView.setEffectBridge(baseEffectBridge);
                            mUpView.setUpImageRes();
                            mUpView.setFocusView(newFocus, mOldFocus, 1.12f);
                            mOldFocus = newFocus;
                        }
                        mGridLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public void setOnClickListener(@Nullable View.OnClickListener l, int... ids) {
        for (int id : ids) findViewById(id).setOnClickListener(l);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.module1:
                startActivity(PicLessonsActivity.class);
                break;
            case R.id.module2:
                startActivity(ArtFancyActivity.class);
                break;
            case R.id.module3:
                startActivity(EnglishActivity.class);
                break;
            case R.id.module4:
                startActivity(PicEnjoyActivity.class);
                break;
            case R.id.module5:
                startActivity(ScienceActivity.class);
                break;
            case R.id.module6:
                startActivity(MusicActivity.class);
                break;
            case R.id.module7:
                startActivity(PrepareLessonsActivity.class);
                break;
            case R.id.module8:
                startActivity(InterestActivity.class);
                break;
            case R.id.module9:
                startActivity(ChineseActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        float scale = 1.12f;
        if (newFocus != null) newFocus.bringToFront();
        if (newFocus == module10) {
            Log.d(TAG, "移动到了Module10");
            mUpView.setFocusView(newFocus, mOldFocus, 1);
            mOldFocus = newFocus;
            mGridLayout.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
            Message.obtain(mHandler, 100).sendToTarget();
            return;
        }

        mUpView.setFocusView(newFocus, mOldFocus, scale);
        mOldFocus = newFocus;
    }


    @Override
    public void OnItemKey(View view, int keyCode, KeyEvent event, int position) {
        Log.d(TAG, "OnItemKey");
        if (position <= 2 && keyCode == KEYCODE_DPAD_LEFT) {
            // TODO BUGS <<<
            Message.obtain(mHandler, 101, position).sendToTarget();
        }

        if (keyCode == KEYCODE_DPAD_CENTER) {
            Log.i(TAG, "按下了OK键");
            AppBean whatsApp = mListOfApps.get(position);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            String packageName = whatsApp.getAppPackageName();
            String activityName = whatsApp.getAppLaunchActivityName();
            ComponentName cn = new ComponentName(packageName, activityName);
            intent.setComponent(cn);
            startActivity(intent);
        }
    }

}
