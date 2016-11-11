/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source SimplePagerAdapter
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;


public class SimplePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListOfFragments;

    public SimplePagerAdapter(FragmentManager fm, List<Fragment> mListOfFragments) {
        super(fm);
        this.mListOfFragments = mListOfFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListOfFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
