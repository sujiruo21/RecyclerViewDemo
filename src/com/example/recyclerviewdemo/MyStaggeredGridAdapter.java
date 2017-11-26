package com.example.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MyStaggeredGridAdapter extends Adapter<MyStaggeredGridAdapter.MyViewHolder> {
	
	private List<String> list;
	private ArrayList<Integer> heights;

	public MyStaggeredGridAdapter(List<String> list) {
		super();
		this.list = list;
		heights = new ArrayList<Integer>();
		for(int i = 0; i < list.size(); i++){
			heights.add((int)(200+Math.random()*50));
		}
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		LayoutParams params = holder.tv.getLayoutParams();
		params.height = heights.get(position);
		holder.tv.setBackgroundColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
//		holder.tv.setBackgroundColor(Color.rgb(100, (int)(Math.random()*255), (int)(Math.random()*255)));
		holder.tv.setLayoutParams(params);
		holder.tv.setText(list.get(position));
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		//inflate()
		MyViewHolder viewHolder = new MyViewHolder(View.inflate(viewGroup.getContext(), R.layout.recyclerview_list, null));
		return viewHolder;
	}
	
	class MyViewHolder extends ViewHolder {

		private TextView tv;

		public MyViewHolder(View v) {
			super(v);
			tv = (TextView) v.findViewById(R.id.tv);
		}

	}

}
