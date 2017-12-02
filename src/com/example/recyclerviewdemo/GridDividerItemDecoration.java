package com.example.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class GridDividerItemDecoration extends ItemDecoration {

	private int[] attrs = new int[] { android.R.attr.listDivider };
	private Drawable mDivider;

	public GridDividerItemDecoration(Context context) {
		TypedArray attr = context.obtainStyledAttributes(attrs);
		mDivider = attr.getDrawable(0);
		attr.recycle();
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		drawVertical(c, parent);
		drawHorzontal(c, parent);
	}

	private void drawHorzontal(Canvas c, RecyclerView parent) {
		int childCount = parent.getChildCount();
		for(int i = 0;i < childCount; i++){
			View child = parent.getChildAt(i);
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			int left = child.getLeft() - params.leftMargin;
			int top = child.getBottom() + params.bottomMargin;
			int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
			int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	private void drawVertical(Canvas c, RecyclerView parent) {
		int childCount = parent.getChildCount();
		for(int i = 0;i < childCount; i++){
			if(i % 3 != 2){
				View child = parent.getChildAt(i);
				LayoutParams params = (LayoutParams) child.getLayoutParams();
				int left = child.getRight() + params.rightMargin;
				int top = child.getTop() - params.topMargin;
				int right = left + mDivider.getIntrinsicWidth();
				int bottom = child.getBottom() + params.bottomMargin;
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		int right = mDivider.getIntrinsicWidth();
		int bottom = mDivider.getIntrinsicHeight();
		outRect.set(0, 0, right, bottom);
	}
}
