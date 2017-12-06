package com.example.recyclerviewdemo;

import java.util.ArrayList;

import com.example.recyclerviewdemo.adapter.HeaderViewRecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class WrapRecyclerView extends RecyclerView {

	private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
	private Adapter mAdapter;
	private ArrayList<View> mFooterViewInfos = new ArrayList<View>();

	public WrapRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void addHeaderView(View v) {
		mHeaderViewInfos.add(v);
		if (mAdapter != null) {
			if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
				mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos,
						mFooterViewInfos, mAdapter);
			}
		}
	}

	@Override
	public void setAdapter(Adapter adapter) {
		if (mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0) {
			mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos,
					mFooterViewInfos, mAdapter);
		} else {
			mAdapter = adapter;
		}
		super.setAdapter(mAdapter);
	}

}
