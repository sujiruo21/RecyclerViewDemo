package com.example.recyclerviewdemo;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyRecyclerAdapter extends Adapter<MyRecyclerAdapter.MyViewHolder> {

	private List<String> list;
	private OnItemClickListener mOnItemClickListener;
	private OnItemLongClickListener mOnItemLongClickListener;

	public MyRecyclerAdapter(List<String> list) {
		this.list = list;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		holder.tv.setText(list.get(position));
		if (mOnItemClickListener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// mOnItemClickListener.onItemClick(v, position);
					mOnItemClickListener.onItemClick(v,
							holder.getLayoutPosition());
				}
			});
		}
		if (mOnItemLongClickListener != null) {
			holder.itemView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					mOnItemLongClickListener.onItemLongClick(v, position);
					return true;
				}
			});
		}
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup view, int arg1) {
		MyViewHolder holder = new MyViewHolder(View.inflate(view.getContext(),
				R.layout.recyclerview_list, null));
		return holder;
	}

	public void addData(int location) {
		list.add(location, "aaa" + location);
		notifyItemInserted(location);
	}

	public interface OnItemClickListener {
		void onItemClick(View v, int position);
	}

	public interface OnItemLongClickListener {
		void onItemLongClick(View v, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		mOnItemLongClickListener = listener;
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		private TextView tv;

		public MyViewHolder(View view) {
			super(view);
			tv = (TextView) view.findViewById(R.id.tv);
		}

	}

}
