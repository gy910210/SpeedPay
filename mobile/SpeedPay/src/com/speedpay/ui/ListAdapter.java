package com.speedpay.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.speedpay.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListAdapter extends SimpleAdapter {

	List<? extends Map<String, ?>> list = new ArrayList<Map<String, Object>>();

	public ListAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
		list = data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		int a = list.get(position).toString().length();
		int flag = 0;
		for (int i = a - 1; i >= 0; i--) {
			if (list.get(position).toString().substring(i - 1, i).equals(",")) {
				flag = i;
				break;
			}
		}
		if (list.get(position).toString().substring(flag - 2, flag - 1)
				.equals("是"))
			view.setBackgroundResource(R.drawable.list_click_green);
		else
			view.setBackgroundResource(R.drawable.list_click_red);
		return view;
	}
}
