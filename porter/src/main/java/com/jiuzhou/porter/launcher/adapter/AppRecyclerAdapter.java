/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source SimpleRecyclerAdapter
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.model.AppBean;

import java.util.List;

/**
 * Created by txt on 2015/11/11.
 * Edited by ShenYu on 2016/10/26.
 */
public class AppRecyclerAdapter extends RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder>{
    private static final String TAG = AppRecyclerAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<AppBean> mListOfApps;
    private int currentPosition = 0;
    private Context context;

    public AppRecyclerAdapter(Context context, List<AppBean> mListOfApps){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mListOfApps = mListOfApps;
    }

    @SuppressWarnings("unused")
    public void setData(List<AppBean> mListOfApps){
        this.mListOfApps = mListOfApps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_grid_apps, parent, false);
        ViewHolder vh = new ViewHolder(view);
        vh.mImageView = (ImageView) view.findViewById(R.id.home_grid_item_icon);
        vh.mTextView = (TextView) view.findViewById(R.id.home_grid_item_name);
        return vh;
    }

    private View mOldFocus;
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImageView.setImageDrawable(mListOfApps.get(position).getAppIcon());
        holder.mTextView.setText(mListOfApps.get(position).getAppName());

        // 设置itemView可以获得焦点
        holder.itemView.setFocusable(true);
        holder.itemView.setTag(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentPosition = (int) holder.itemView.getTag();
                    mOnItemSelectListener.onItemSelect(holder.itemView, currentPosition);

                    if (v != mOldFocus) {
                        View vb = v.findViewById(R.id.home_back_2);
                        GradientDrawable gd = (GradientDrawable) vb.getBackground();
                        int width = (int) context.getResources().getDimension(R.dimen.px_positive_3);
                        int color = context.getResources().getColor(R.color.color0);
                        int radius = (int) context.getResources().getDimension(R.dimen.px_positive_25);
                        gd.setStroke(width, color);
                        gd.setCornerRadius(radius);

                        if (mOldFocus != null) {
                            View ovb = mOldFocus.findViewById(R.id.home_back_2);
                            GradientDrawable ogd = (GradientDrawable) ovb.getBackground();
                            ogd.setStroke(0, Color.parseColor("#00000000"));
                        }
                    }
                    mOldFocus = v;
                } else {
                    if (v != null) {
                        View ovb2 = v.findViewById(R.id.home_back_2);
                        GradientDrawable ogd2 = (GradientDrawable) ovb2.getBackground();
                        ogd2.setStroke(0, Color.parseColor("#00000000"));
                    }
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, currentPosition);
            }
        });

        holder.itemView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mOnItemKeyListener.OnItemKey(v, keyCode, event, currentPosition);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListOfApps.size();
    }

    private int index = 0;
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ImageView back2 = (ImageView) itemView.findViewById(R.id.home_back_2);
            GradientDrawable background = (GradientDrawable) back2.getBackground();
            TypedArray ta = context.getResources().obtainTypedArray(R.array.appBackgroundColors);
            int count = ta.length();
            int [] colorsArray = new int[count];
            for (int i=0;i<count;i++) {
                int resId = ta.getResourceId(i, -1);
                colorsArray[i] = resId;
            }
            /*Random random = new Random();
            int index = random.nextInt(count);
            while (oldIndex == index) {
                index = random.nextInt();
            }
            oldIndex = index;*/
            background.setColor(context.getResources().getColor(colorsArray[index]));
            if (index < count - 1) {
                index += 1;
            } else {
                index = 0;
            }

            ta.recycle();
        }
    }

    private OnItemSelectListener mOnItemSelectListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemKeyListener mOnItemKeyListener;

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public interface OnItemKeyListener {
        void OnItemKey(View view, int keyCode, KeyEvent event, int position);
    }

    public void setOnItemSelectListener(OnItemSelectListener listener){
        mOnItemSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public void setOnItemKeyListener(OnItemKeyListener mOnItemKeyListener) {
        this.mOnItemKeyListener = mOnItemKeyListener;
    }

}
