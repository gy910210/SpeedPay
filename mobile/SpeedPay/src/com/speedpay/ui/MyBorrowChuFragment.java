package com.speedpay.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.speedpay.R;
import com.speedpay.bean.BorrowProof;
import com.speedpay.bean.User;
import com.speedpay.services.MyBorrowChuService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.Session;

public class MyBorrowChuFragment extends Fragment {

	ListView listView;
	List<BorrowProof> borrowList;
	Handler handler;
	List<Map<String, Object>> list;
	Dialog dialog;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_selection_myrepay,
				container, false);

		listView = (ListView) rootView.findViewById(R.id.listView_myrepay);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				BorrowProof borrowProof = borrowList.get(arg2);
				Intent intent = new Intent(getActivity(),
						MyBorrowChuItemActivity.class);
				intent.putExtra("BorrowProof", borrowProof);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// 传参数
				startActivity(intent);

			}
		});

		return rootView;
	}

	@SuppressLint("HandlerLeak")
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		LayoutInflater factory = LayoutInflater.from(getActivity());
		final View view = factory.inflate(R.layout.myrepay_item, null);
		
		Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/kai.ttf");
		TextView myborrow_content = (TextView) view.findViewById(
				R.id.myrepay_content);
		myborrow_content.setTypeface(face);

		handler = new Handler() {
			@SuppressWarnings("unchecked")
			public void handleMessage(Message msg) {
				borrowList = (List<BorrowProof>) msg.obj;

				list = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < borrowList.size(); i++) {

					String temp = (borrowList.get(i).getIsRepayed() == 0) ? "否"
							: "是";
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("myrepay_itemLogo", R.drawable.ic_launcher);
					map.put("myrepay_content", "借款凭据id:"
							+ borrowList.get(i).getBorrowProofId() + "\n借出款时间:"
							+ borrowList.get(i).getBorrowTime() + "\n借出款金额:"
							+ borrowList.get(i).getBorrowSum() + "\n对方是否还款:"
							+ temp);

					list.add(map);
				}
				if (list.size() != 0) {
					ListAdapter listItemAdapter = new ListAdapter(
							getActivity(), list, R.layout.myrepay_item,
							new String[] { "myrepay_itemLogo",
									"myrepay_content" },
							new int[] { R.id.myrepay_content,
									R.id.myrepay_content });

					listView.setAdapter(listItemAdapter);
				}
				Thread.currentThread().interrupt();
				dialog.dismiss();
			}
		};

		User user = (User) Session.getSession().get("user");
		MyBorrowChuService mbs = new MyBorrowChuService(user.getUser_id(),
				handler);
		new Thread(mbs).start();

		ProgressDialog d = new ProgressDialog();
		dialog = d.showRoundProcessDialog(getActivity());
	}
}
