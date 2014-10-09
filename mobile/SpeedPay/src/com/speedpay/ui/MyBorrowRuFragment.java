package com.speedpay.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.speedpay.R;
import com.speedpay.bean.BorrowProof;
import com.speedpay.bean.User;
import com.speedpay.services.MyBorrowRuService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.Session;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MyBorrowRuFragment extends Fragment {

	ListView listView;
	List<BorrowProof> borrowList;
	Handler handler;
	List<Map<String, Object>> list;
	Dialog dialog;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_selection_myborrow,
				container, false);

		listView = (ListView) rootView.findViewById(R.id.listView_myborrow);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				BorrowProof borrowProof = borrowList.get(arg2);
				Intent intent = new Intent(getActivity(),
						MyBorrowRuItemActivity.class);
				intent.putExtra("BorrowProof", borrowProof);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// 传参数
				startActivity(intent);

			}
		});

		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		LayoutInflater factory = LayoutInflater.from(getActivity());
		final View view = factory.inflate(
				R.layout.myborrow_item, null);
		
		Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/kai.ttf");
		TextView myborrow_content = (TextView) view.findViewById(
				R.id.myborrow_content);
		myborrow_content.setTypeface(face);

		handler = new Handler() {
			public void handleMessage(Message msg) {
				borrowList = (List<BorrowProof>) msg.obj;

				list = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < borrowList.size(); i++) {
					String temp = (borrowList.get(i).getIsRepayed() == 0) ? "否"
							: "是";
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("myborrow_itemLogo", R.drawable.ic_launcher);
					map.put("myborrow_content", "借款凭据id:"
							+ borrowList.get(i).getBorrowProofId() + "\n借入款时间:"
							+ borrowList.get(i).getBorrowTime() + "\n借入款金额:"
							+ borrowList.get(i).getBorrowSum() + "\n我是否还款:"
							+ temp);
					list.add(map);
				}

				if (list.size() != 0) {

					ListAdapter listItemAdapter = new ListAdapter(
							getActivity(), list, R.layout.myborrow_item,
							new String[] { "myborrow_itemLogo",
									"myborrow_content" }, new int[] {
									R.id.myborrow_itemLogo,
									R.id.myborrow_content });

					listView.setAdapter(listItemAdapter);
				}
				Thread.currentThread().interrupt();
				dialog.dismiss();
			}
		};

		User user = (User) Session.getSession().get("user");
		MyBorrowRuService mbs = new MyBorrowRuService(user.getUser_id(),
				handler);
		new Thread(mbs).start();

		ProgressDialog d = new ProgressDialog();
		dialog = d.showRoundProcessDialog(getActivity());
	}

}
