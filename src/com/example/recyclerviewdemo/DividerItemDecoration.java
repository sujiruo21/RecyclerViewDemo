package com.example.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {

	private int orientation;

	private Drawable mDrivider;

	private int[] attrs = new int[] { android.R.attr.listDivider };

	public DividerItemDecoration(Context context, int orientation) {
		TypedArray attr = context.obtainStyledAttributes(attrs);
		mDrivider = attr.getDrawable(0);
		attr.recycle();
		setOrientation(orientation);
	}

	public void setOrientation(int orientation) {
		if (orientation != LinearLayoutManager.HORIZONTAL
				&& orientation != LinearLayoutManager.VERTICAL) {
			throw new IllegalArgumentException("不是水平或者垂直排列");
		}
		this.orientation = orientation;
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		if(orientation == LinearLayoutManager.VERTICAL){
			drawVerticalDrivider(c,parent);
		}else{
			drawHorizontalDrivider(c,parent);
		}
		super.onDraw(c, parent, state);
	}

	private void drawHorizontalDrivider(Canvas c, RecyclerView parent) {
		int top = parent.getPaddingTop();
		int bottom = parent.getHeight() - parent.getPaddingBottom();
		int childCount = parent.getChildCount();
		for(int i = 0;i < childCount; i++){
			View child = parent.getChildAt(i);
			LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
			int left = child.getLeft() + layoutParams.leftMargin + Math.round(ViewCompat.getTranslationX(child));
			int right = left + mDrivider.getIntrinsicWidth();
			mDrivider.setBounds(left, top, right, bottom);
			mDrivider.draw(c);
		}
	}

	private void drawVerticalDrivider(Canvas c, RecyclerView parent) {
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		int childCount = parent.getChildCount();
		for(int i = 0;i < childCount; i++){
			View child = parent.getChildAt(i);
			LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
			int top = child.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
			int bottom = top + mDrivider.getIntrinsicHeight();
			mDrivider.setBounds(left, top, right, bottom);
			mDrivider.draw(c);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		if (orientation == LinearLayoutManager.VERTICAL) {
			outRect.set(0, 0, 0, mDrivider.getIntrinsicHeight());
		} else {
			outRect.set(0, 0, mDrivider.getIntrinsicWidth(), 0);
		}
	}
}
