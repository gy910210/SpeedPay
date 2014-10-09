package com.speedpay.ui;

import java.util.ArrayList;
import java.util.List;
import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.services.MyTradeService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.Session;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyTradeActivity extends ExpandableListActivity implements
		ActionBar.OnNavigationListener {

	List<String> group;
	List<List<String>> child;
	ContactsInfoAdapter adapter;
	ArrayList<ArrayList<String>> trade;
	Handler handler;
	private Dialog dialog;

	public MyTradeActivity() {
		super();

	}

	@SuppressLint("HandlerLeak")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mytrade);

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] { "超市消费记录", "商户消费记录",
								"转账凭证记录", "取款凭据记录" }), this);

		handler = new Handler() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				trade = (ArrayList<ArrayList<String>>) msg.obj;

				System.out.println("---------" + trade);
				initializeData();
				getExpandableListView().setAdapter(new ContactsInfoAdapter());
				getExpandableListView().setGroupIndicator(
						MyTradeActivity.this.getResources().getDrawable(
								R.drawable.indicator));
				@SuppressWarnings("deprecation")
				int width = getWindowManager().getDefaultDisplay().getWidth();
				getExpandableListView().setIndicatorBounds(width - 40,
						width - 10);
				getExpandableListView().setChildDivider(
						MyTradeActivity.this.getResources().getDrawable(
								R.drawable.line));
				Thread.currentThread().interrupt();

				dialog.dismiss();
			}
		};

		User user = (User) Session.getSession().get("user");
		MyTradeService myTradeService = new MyTradeService(user.getUser_id(),
				0, handler);
		new Thread(myTradeService).start();

		ProgressDialog d = new ProgressDialog();
		dialog = d.showRoundProcessDialog(MyTradeActivity.this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logo, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// TODO Auto-generated method stub

		User user = (User) Session.getSession().get("user");
		if (position != 0) {
			MyTradeService myTradeService = new MyTradeService(
					user.getUser_id(), position, handler);
			new Thread(myTradeService).start();

			ProgressDialog d = new ProgressDialog();
			dialog = d.showRoundProcessDialog(MyTradeActivity.this);
		}
		return true;
	}

	private void initializeData() {
		group = new ArrayList<String>();
		child = new ArrayList<List<String>>();

		for (int i = 0; i < 12; i++)
			System.out.println(trade.get(i).size());

		if (trade == null) {
			System.out.println("+++++++++++++++++++++++++");
		} else {
			if (trade.get(0) != null)
				addInfo("January  一月", trade.get(12));
			if (trade.get(1) != null)
				addInfo("February  二月", trade.get(13));
			if (trade.get(2) != null)
				addInfo("March  三月", trade.get(14));
			if (trade.get(3) != null)
				addInfo("April  四月", trade.get(15));
			if (trade.get(4) != null)
				addInfo("May  五月", trade.get(16));
			if (trade.get(5) != null)
				addInfo("June  六月", trade.get(17));
			if (trade.get(6) != null)
				addInfo("July  七月", trade.get(18));
			if (trade.get(7) != null)
				addInfo("Augest  八月", trade.get(19));
			if (trade.get(8) != null)
				addInfo("September  九月", trade.get(20));
			if (trade.get(9) != null)
				addInfo("October  十月", trade.get(21));
			if (trade.get(10) != null)
				addInfo("November  十一月", trade.get(22));
			if (trade.get(11) != null)
				addInfo("December  十二月", trade.get(23));
		}

	}

	private void addInfo(String g, List<String> c) {
		group.add(g);
		List<String> childitem = new ArrayList<String>();
		for (int i = 0; i < c.size(); i++) {
			childitem.add(c.get(i));
		}
		child.add(childitem);
	}

	class ContactsInfoAdapter extends BaseExpandableListAdapter {

		public Object getChild(int groupPosition, int childPosition) {
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return child.get(groupPosition).size();
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			String string = child.get(groupPosition).get(childPosition);
			return getGenericView(string);
		}

		// ----------------Group----------------//
		@Override
		public Object getGroup(int groupPosition) {
			return group.get(groupPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public int getGroupCount() {
			return group.size();
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String string = group.get(groupPosition);
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					dm.heightPixels * 5 / 72);
			TextView text = new TextView(getApplicationContext());
			text.setLayoutParams(lp);
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			text.setPadding(36, 0, 0, 0);
			text.setText(string);
			text.setTextColor(Color.BLACK);
			return text;
		}

		public TextView getGenericView(String s) {
			@SuppressWarnings("deprecation")
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 100);
			TextView text = new TextView(getApplicationContext());
			text.setLayoutParams(lp);
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			text.setPadding(36, 0, 0, 0);
			text.setText(s);
			text.setTextColor(Color.BLACK);
			return text;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
