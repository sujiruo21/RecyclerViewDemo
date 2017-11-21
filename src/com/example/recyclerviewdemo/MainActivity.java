package com.example.recyclerviewdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewdemo.MyRecyclerAdapter.OnItemClickListener;

public class MainActivity extends Activity {

	private RecyclerView recyclerView;
	private MyRecyclerAdapter adapter;
	private ArrayList<String> list;

	// private MyStaggeredGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.rv);
		list = new ArrayList<String>();
		for (int i = 0; i < 60; i++) {
			list.add("item " + i);
		}
		adapter = new MyRecyclerAdapter(list);
		// adapter = new MyStaggeredGridAdapter(list);
		// LayoutManager layout = new GridLayoutManager(this, 3);
		LayoutManager layout = new StaggeredGridLayoutManager(3,
				StaggeredGridLayoutManager.VERTICAL);
		// LayoutManager layout = new LinearLayoutManager(MainActivity.this);
		// LayoutManager layout = new LinearLayoutManager(MainActivity.this,
		// LinearLayoutManager.HORIZONTAL, true);
		recyclerView.setLayoutManager(layout);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View v, int position) {
				Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	boolean isGrid = false;

	public void change(View v) {
		if (!isGrid) {
			recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		} else {
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
		}
		isGrid = !isGrid;
	}
	
	public void add(View v){
		adapter.addData(30);
	}
}
