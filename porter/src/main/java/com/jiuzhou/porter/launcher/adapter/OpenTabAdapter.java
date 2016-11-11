package com.jiuzhou.porter.launcher.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jiuzhou.porter.launcher.R;
import com.open.androidtvwidget.adapter.BaseTabTitleAdapter;
import com.open.androidtvwidget.view.TextViewWithTTF;

import java.util.ArrayList;
import java.util.List;

public class OpenTabAdapter extends BaseTabTitleAdapter {
	private static final String TAG = OpenTabAdapter.class.getSimpleName();
	private List<String> mList;

	public OpenTabAdapter(List<String> mList) {
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	/**
	 * 为何要设置ID标识。<br>
	 * 因为PAGE页面中的ITEM如果向上移到标题栏， <br>
	 * 它会查找最近的，你只需要在布局中设置 <br>
	 * android:nextFocusUp="@+id/title_bar1" <br>
	 * 就可以解决焦点问题哦.
	 */
	private List<Integer> ids = new ArrayList<Integer>() {
		/*{
			add(R.id.title_bar1);
			add(R.id.title_bar2);
			add(R.id.title_bar3);
			add(R.id.title_bar4);
		}*/
	};

	@Override
	public Integer getTitleWidgetID(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		parent.getContext();
		String title = mList.get(position);
		if (convertView == null) {
			convertView = newTabIndicator(parent.getContext(), title, false);
//			convertView.setId(ids.get(position)); // 设置ID.
		} else {
			// ... ...
		}
		return convertView;
	}

	/**
	 * 这里只是demo，你可以设置自己的标题栏.
     */
	private View newTabIndicator(Context context, String tabName, boolean focused) {
		View viewC = View.inflate(context, R.layout.detail_tab2, null);
		TextViewWithTTF view = (TextViewWithTTF) viewC.findViewById(R.id.tab_ttf);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 0, 10, 0);
		view.setLayoutParams(lp);

		// mTabWidget.setPadding(getResources().getDimensionPixelSize(R.dimen.tab_left_offset),
		// 0, 0, 0);
		view.setText(tabName);

		if (focused) {
			Resources res = context.getResources();
			/*view.setTextColor(res.getColor(android.R.color.white));
			view.setTypeface(null, Typeface.BOLD);*/
			view.requestFocus();
		}
		return viewC;
	}
}
