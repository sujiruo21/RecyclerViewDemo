package com.example.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.widget.Toast;

public class GridDividerItemDecoration extends ItemDecoration {

	private int[] attrs = new int[] { android.R.attr.listDivider };
	private Drawable mDivider;
	private int itemPosition;
	private int spanCount;
	private int childCount;

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
		for (int i = 0; i < childCount; i++) {
			if (i < (childCount - childCount % spanCount)) {
				View child = parent.getChildAt(i);
				LayoutParams params = (LayoutParams) child.getLayoutParams();
				int left = child.getLeft() - params.leftMargin;
				int top = child.getBottom() + params.bottomMargin;
				int right = child.getRight() + params.rightMargin
						+ mDivider.getIntrinsicWidth();
				int bottom = top + mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}
	}

	private void drawVertical(Canvas c, RecyclerView parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
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

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		int right = mDivider.getIntrinsicWidth();
		int bottom = mDivider.getIntrinsicHeight();
		itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams())
				.getViewLayoutPosition();
		spanCount = getSpanCount(parent);
		childCount = parent.getAdapter().getItemCount();
		if (isLastRow(parent, itemPosition, spanCount, childCount)) {
			bottom = 0;
		} else if (isLastCol(parent, itemPosition, spanCount)) {
			right = 0;
		}
		outRect.set(0, 0, right, bottom);
	}

	private boolean isLastCol(RecyclerView parent, int position, int spanCount) {
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			if ((position + 1) % spanCount == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isLastRow(RecyclerView parent, int position, int spanCount,
			int childCount) {
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			int lastRowPos = childCount - childCount % spanCount;
			if (position >= lastRowPos) {
				return true;
			}
		}
		return false;
	}

	private int getSpanCount(RecyclerView parent) {
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			GridLayoutManager manager = (GridLayoutManager) layoutManager;
			int spanCount = manager.getSpanCount();
			return spanCount;
		} else {
			Toast.makeText(parent.getContext(), "不是GridLayoutManager",
					Toast.LENGTH_SHORT).show();
		}
		return 0;
	}
}
