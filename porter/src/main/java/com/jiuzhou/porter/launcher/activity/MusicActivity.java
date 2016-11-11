/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source MusicActivity
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.activity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.adapter.OpenTabAdapter;
import com.jiuzhou.porter.launcher.adapter.SimplePagerAdapter;
import com.open.androidtvwidget.view.OpenTabHost;
import com.open.androidtvwidget.view.TextViewWithTTF;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MusicActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, OpenTabHost.OnTabSelectListener,
        DetailFragment.OnFragmentInteractionListener {
    private static final String TAG = "MusicActivity";
    private OpenTabHost mOpenTabHost;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_enjoy);
        onInit();
    }

    private void onInit() {
        View view1 = findViewById(R.id.module_header);
        TextView tv1 = (TextView) view1.findViewById(R.id.module_name);
        tv1.setText("音乐律动");
        mOpenTabHost = (OpenTabHost) findViewById(R.id.detail_tab);
        List<String> mList = getData();
        OpenTabAdapter mOpenTabAdapter = new OpenTabAdapter(mList);
        mOpenTabHost.setOnTabSelectListener(this);
        mOpenTabHost.setAdapter(mOpenTabAdapter);

        mViewPager = (ViewPager) findViewById(R.id.detail_pager);
        int page = mOpenTabAdapter.getCount();
        List<Fragment> mListOfFragments = new LinkedList<>();
        for (int i=0;i<page;i++) {
            mListOfFragments.add(DetailFragment.newInstance("M1"));
        }
        SimplePagerAdapter adapter = new SimplePagerAdapter(getSupportFragmentManager(), mListOfFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(OpenTabHost openTabHost, View titleWidget, int position) {
        Log.i(TAG, "onTabSelect");
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG, "onPageScrolled:position=" + position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected:position=" + position);
        switchTab(mOpenTabHost, position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i(TAG, "onPageScrollStateChanged");
    }

    /**
     * demo (翻页的时候改变状态)
     * 将标题栏的文字颜色改变. <br>
     * 你可以写自己的东西，我这里只是DEMO.
     */
    public void switchTab(OpenTabHost openTabHost, int position) {
        List<View> viewList = openTabHost.getAllTitleView();
        for (int i = 0; i < viewList.size(); i++) {
            TextViewWithTTF view = (TextViewWithTTF) openTabHost.getTitleViewIndexAt(i);
            if (view != null) {
                Resources res = view.getResources();
                if (res != null) {
                    if (i == position) {
                        view.setSelected(true);
                    } else {
                        view.setSelected(false);
                    }
                }
            }
        }
    }


    private List<String> getData() {
        List<String> mList = new LinkedList<>();
        String[] array = getResources().getStringArray(R.array.detailCategory2);
        Collections.addAll(mList, array);
        return mList;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i(TAG, "onFragmentInteraction");
    }
}
