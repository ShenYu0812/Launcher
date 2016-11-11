/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source ArtFancyActivity
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.adapter.BookRecyclerAdapter;
import com.jiuzhou.porter.launcher.model.BookBean;
import com.jiuzhou.porter.launcher.widget.SimpleRecycleView;
import com.jiuzhou.porter.launcher.widget.SpaceItemDecoration;

import java.util.LinkedList;

import static com.jiuzhou.porter.launcher.until.Constant.nameGroup3;
import static com.jiuzhou.porter.launcher.until.Constant.resId3;

public class ArtFancyActivity extends Activity {

    private LinkedList<BookBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_grid);
        View view1 = findViewById(R.id.module_header);
        TextView tv1 = (TextView) view1.findViewById(R.id.module_name);
        tv1.setText("美术创想");
        View view = findViewById(R.id.book_relative);
        view.setBackgroundResource(R.mipmap.module_pic_lessons_background);
        SimpleRecycleView mRecyclerView = (SimpleRecycleView) findViewById(R.id.module_pic_m1_grid);
        // TODO 此处为虚拟数据，实际需要使用ContentProvider
        mList = new LinkedList<>();
        for (int i=0;i<30;i++) {
            BookBean bean = new BookBean();
            bean.setIcon(resId3[i%10]);
            bean.setName(nameGroup3[i%10]);
            bean.setPath("path null");
            mList.add(bean);
        }
        BookRecyclerAdapter adapter = new BookRecyclerAdapter(this, mList, "M2");
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        int right = (int) getResources().getDimension(R.dimen.px_positive_30);
        int bottom = (int) getResources().getDimension(R.dimen.px_positive_25);
        RecyclerView.ItemDecoration spacingInPixel = new SpaceItemDecoration(0, right, bottom, 0);
        mRecyclerView.addItemDecoration(spacingInPixel);
    }
}
