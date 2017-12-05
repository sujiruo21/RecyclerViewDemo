package com.example.recyclerviewdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private MyRecyclerAdapter adapter;
	private ArrayList<String> list;
	private Toolbar toolbar;
	private GridDividerItemDecoration decor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.rv);
		list = new ArrayList<String>();
		for (int i = 0; i < 59; i++) {
			list.add("item " + i);
		}
		adapter = new MyRecyclerAdapter(list);
		// adapter = new MyStaggeredGridAdapter(list);
		LayoutManager layout = new GridLayoutManager(this, 5);
		// LayoutManager layout = new StaggeredGridLayoutManager(3,
		// StaggeredGridLayoutManager.VERTICAL);
		// LayoutManager layout = new LinearLayoutManager(MainActivity.this);
		// LayoutManager layout = new LinearLayoutManager(MainActivity.this,
		// LinearLayoutManager.HORIZONTAL, true);
		recyclerView.setLayoutManager(layout);
		recyclerView.setAdapter(adapter);
		// adapter.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(View v, int position) {
		// Toast.makeText(MainActivity.this, "点击了" + position,
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		// decor = new DividerItemDecoration(this,
		// LinearLayoutManager.HORIZONTAL);
		decor = new GridDividerItemDecoration(this);
		recyclerView.addItemDecoration(decor);

		// adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		// @Override
		// public void onItemLongClick(View v, int position) {
		// Toast toast = Toast.makeText(MainActivity.this, "长按了"
		// + position, Toast.LENGTH_SHORT);
		// toast.setGravity(Gravity.TOP, 0, 80);
		// toast.show();
		// }
		// });
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("RecyclerView");
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				return false;
			}
		});
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_menu_selectall_mtrl_alpha);
	}

	boolean isGrid = false;

	public void change(View v) {
		if (decor != null) {
			recyclerView.removeItemDecoration(decor);
		}
		if (!isGrid) {
			// decor = new DividerItemDecoration(this,
			// LinearLayoutManager.VERTICAL);
			recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
			// recyclerView.addItemDecoration(decor);
		} else {
			recyclerView.setLayoutManager(new LinearLayoutManager(
					MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
			// decor = new DividerItemDecoration(this,
			// LinearLayoutManager.HORIZONTAL);
			// recyclerView.addItemDecoration(decor);
		}
		isGrid = !isGrid;
	}

	// public void add(View v) {
	// adapter.addData(30);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit:
			Toast.makeText(MainActivity.this, "edit", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.action_share:
			Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			break;
		}
		return true;
	}
}
