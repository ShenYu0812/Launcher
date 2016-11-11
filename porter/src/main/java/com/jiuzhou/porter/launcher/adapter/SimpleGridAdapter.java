/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source SimpleGridAdapter
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.model.DetailBean;

import java.util.List;

public class SimpleGridAdapter extends SimpleBaseAdapter {
    private List mList;
    private LayoutInflater inflater;

    public SimpleGridAdapter(Context mContext, List<?> mList) {
        super(mList);
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grid_books, parent, false);
            vh = new ViewHolder();
            vh.mImageView = (ImageView) convertView.findViewById(R.id.book_icon);
            vh.mTextView = (TextView) convertView.findViewById(R.id.book_name);
            vh.mTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                }
            });

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (mList.get(position) instanceof DetailBean) {
            DetailBean bean = (DetailBean) mList.get(position);
            if (bean != null) {
                vh.mTextView.setText(bean.getName());
                vh.mImageView.setImageResource(bean.getIcon());
            }
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView  mImageView;
        TextView mTextView;
    }
}
