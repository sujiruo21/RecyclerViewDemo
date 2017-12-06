package com.example.recyclerviewdemo.adapter;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public class HeaderViewRecyclerAdapter extends Adapter {
	
	private ArrayList<View> mHeaderViewInfos;
	private ArrayList<View> mFooterViewInfos;
	private Adapter mAdapter;
	private int HEADERVIEW = 0;
	private int FOOTERVIEW = 1;

	public HeaderViewRecyclerAdapter(ArrayList<View> headerViewInfos,
			ArrayList<View> footerViewInfos, Adapter adapter) {
		mAdapter = adapter;

        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<View>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<View>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
	}

	@Override
	public int getItemCount() {
		if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		int numHeaders = getHeadersCount();
		// Header
        if (position < numHeaders) {
            return ;
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
            	mAdapter.bindViewHolder(holder, adjPosition);
                return ;
            }
        }

	}
	
	@Override
	public int getItemViewType(int position) {
		int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return HEADERVIEW ;
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return FOOTERVIEW ;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(viewType == HEADERVIEW){
			return new HeaderViewViewHolder(mHeaderViewInfos.get(0));
		}else if(viewType == FOOTERVIEW){
			return new HeaderViewViewHolder(mFooterViewInfos.get(0));
		}
		return super.createViewHolder(parent, viewType);
	}
	
	public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }
	
	public int getFootersCount() {
        return mFooterViewInfos.size();
    }
	
	private static class HeaderViewViewHolder extends ViewHolder{

		public HeaderViewViewHolder(View arg0) {
			super(arg0);
		}
		
	}

}
