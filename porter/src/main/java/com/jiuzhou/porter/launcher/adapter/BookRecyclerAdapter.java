/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source BookRecyclerAdapter
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.activity.PicEnjoyBookDetailActivity;
import com.jiuzhou.porter.launcher.activity.PicLessonsBookDetailActivity;
import com.jiuzhou.porter.launcher.activity.VideoDetailActivity;
import com.jiuzhou.porter.launcher.model.BookBean;

import java.util.List;

import static android.view.KeyEvent.KEYCODE_DPAD_CENTER;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private static final String TAG = BookRecyclerAdapter.class.getSimpleName();
    private String tag;
    private LayoutInflater mInflater;
    private List<BookBean> mListOfBooks;
    private Context context;

    public BookRecyclerAdapter(Context context, List<BookBean> mListOfBooks, String tag){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mListOfBooks = mListOfBooks;
        this.tag = tag;
    }

    @SuppressWarnings("unused")
    public void setData(List<BookBean> mListOfBooks){
        this.mListOfBooks = mListOfBooks;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if ("M4".equals(tag)) {
            view = mInflater.inflate(R.layout.item_grid_books2, parent, false);
        } else if ("M5".equals(tag)) {
            view = mInflater.inflate(R.layout.item_grid_books3, parent, false);
        } else {
            view = mInflater.inflate(R.layout.item_grid_books, parent, false);
        }
        ViewHolder vh = new ViewHolder(view);
        vh.mImageView = (ImageView) view.findViewById(R.id.book_icon);
        vh.mTextView = (TextView) view.findViewById(R.id.book_name);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mImageView.setImageResource(mListOfBooks.get(position).getIcon());
        holder.mTextView.setText(mListOfBooks.get(position).getName());
        holder.mImageView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KEYCODE_DPAD_CENTER) {
                    Intent intent = new Intent();
                    if ("M1".equals(tag)) {
                        intent.setClass(context, PicLessonsBookDetailActivity.class);
                        intent.putExtra("position", holder.getAdapterPosition());
                        context.startActivity(intent);
                    } else if ("M2".equals(tag)) {
                        intent.setClass(context, PicEnjoyBookDetailActivity.class);
                        intent.putExtra("position", holder.getAdapterPosition());
                        context.startActivity(intent);
                    } else {
                        intent.setClass(context, VideoDetailActivity.class);
                        intent.putExtra("position", holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListOfBooks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
