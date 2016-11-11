/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source DetailFragment
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiuzhou.porter.launcher.R;
import com.jiuzhou.porter.launcher.adapter.BookRecyclerAdapter;
import com.jiuzhou.porter.launcher.model.BookBean;
import com.jiuzhou.porter.launcher.widget.SpaceItemDecoration;

import java.util.LinkedList;

import static com.jiuzhou.porter.launcher.until.Constant.nameGroup1;
import static com.jiuzhou.porter.launcher.until.Constant.nameGroup2;
import static com.jiuzhou.porter.launcher.until.Constant.nameGroup7;
import static com.jiuzhou.porter.launcher.until.Constant.resId1;
import static com.jiuzhou.porter.launcher.until.Constant.resId2;
import static com.jiuzhou.porter.launcher.until.Constant.resId7;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private BookRecyclerAdapter adapter;

    public DetailFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailFragment.
     */
    @SuppressWarnings("unused")
    public static DetailFragment newInstance(String param1) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_grid, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.module_pic_m1_grid);
        if ("M1".equals(mParam1)) {
            LinkedList<BookBean> mList1 = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                BookBean bean = new BookBean();
                bean.setIcon(resId7[i]);
                bean.setName(nameGroup7[i]);
                bean.setPath("path null");
                mList1.add(bean);
            }
            adapter = new BookRecyclerAdapter(getContext(), mList1, "M2");
        } else {
            LinkedList<BookBean> mList2 = new LinkedList<>();
            for (int i = 0; i < 15; i++) {
                BookBean bean = new BookBean();
                if (i % 2 == 0) {
                    bean.setIcon(resId1[i % 5]);
                    bean.setName(nameGroup1[i % 5]);
                } else {
                    bean.setIcon(resId2[i % 5]);
                    bean.setName(nameGroup2[i % 5]);
                }
                bean.setPath("path null");
                mList2.add(bean);
            }
            adapter = new BookRecyclerAdapter(getContext(), mList2, "M2");
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        int right = (int) getResources().getDimension(R.dimen.px_positive_30);
        int bottom = (int) getResources().getDimension(R.dimen.px_positive_25);
        RecyclerView.ItemDecoration spacingInPixel = new SpaceItemDecoration(0, right, bottom, 0);
        mRecyclerView.addItemDecoration(spacingInPixel);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
