/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source PicLessonsBookDetailActivity
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
import android.widget.GridView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.adapter.SimpleGridAdapter;
import com.jiuzhou.porter.launcher.model.DetailBean;

import java.util.LinkedList;
import java.util.List;

public class PicLessonsBookDetailActivity extends Activity {
    private static final String TAG = PicLessonsBookDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_lessons_detail);
        GridView mGridView = (GridView) findViewById(R.id.detail_grid);
        List<DetailBean> mList = new LinkedList<>();
        for (int i=0;i<6;i++) {
            DetailBean bean = new DetailBean();
            bean.setName(detailNames[i]);
            bean.setIcon(detailRes[i]);
            mList.add(bean);
        }
        SimpleGridAdapter adapter = new SimpleGridAdapter(this, mList);
        mGridView.setAdapter(adapter);
    }

    private int[] detailRes = {R.mipmap.detail_m1, R.mipmap.detail_m2, R.mipmap.detail_m4, R.mipmap.detail_m5, R.mipmap.detail_m6, R.mipmap.detail_m7};
    private String[] detailNames = {"画面阅读", "整读", "故事回顾", "拓展延伸", "互动1", "互动2"};

    // TODO: 2016/11/8 此处需要做点击事件
}
