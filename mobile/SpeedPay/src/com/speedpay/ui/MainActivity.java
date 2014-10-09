package com.speedpay.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.speedpay.R;
import com.speedpay.bean.Goods;
import com.speedpay.bean.MyMessage;
import com.speedpay.bean.User;
import com.speedpay.services.GetMessageService;
import com.speedpay.services.GetUserService;
import com.speedpay.services.InsertBorrowProofService;
import com.speedpay.services.InsertTransferProofService;
import com.speedpay.services.OKRenewalApplyService;
import com.speedpay.ui.MainActivity.GetUserThread;
import com.speedpay.utils.ErCodeParse;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Intent intent;

	MyMessage message;
	ErCodeParse ercodeParse;
	SimpleDateFormat sdf;
	User user;
	Map<String, Object> map;
	String[] strs;
	SharedPreferences sharedPreferenceds;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);

		Session session = Session.getSession();
		/*List<Goods> goodsList = new ArrayList<Goods>();
		Goods good = new Goods(1, "HongBaoShu", 43.8, "9787560422862");
		goodsList.add(good);
		good=new Goods(2, "Data Structure", 39.5, "9787121205262");
		goodsList.add(good);
		session.put("goodsList", goodsList);*/
		user = (User) session.get("user");

		LinearLayout linearLayout1 = (android.widget.LinearLayout) findViewById(R.id.main_1);
		LinearLayout linearLayout2 = (android.widget.LinearLayout) findViewById(R.id.main_2);
		LinearLayout linearLayout3 = (android.widget.LinearLayout) findViewById(R.id.main_3);
		LinearLayout linearLayout4 = (android.widget.LinearLayout) findViewById(R.id.main_4);
		LinearLayout linearLayout5 = (android.widget.LinearLayout) findViewById(R.id.main_5);
		LinearLayout linearLayout6 = (android.widget.LinearLayout) findViewById(R.id.main_6);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/huakangshaonv.ttf");
		Typeface face1 = Typeface.createFromAsset(getAssets(), "fonts/pop.ttf");

		TextView text_1 = (TextView) findViewById(R.id.text_1);
		TextView text_2 = (TextView) findViewById(R.id.text_2);
		TextView text_3 = (TextView) findViewById(R.id.text_3);
		TextView text_4 = (TextView) findViewById(R.id.text_4);
		TextView text_5 = (TextView) findViewById(R.id.text_5);
		TextView text_6 = (TextView) findViewById(R.id.text_6);
		TextView text_userType = (TextView) findViewById(R.id.text_userType);
		if (user.getUser_type() == 0) {
			text_userType.setText("个人端");
		}
		if (user.getUser_type() == 2) {
			text_userType.setText("商户端");
		}

		text_1.setTypeface(face);
		text_2.setTypeface(face);
		text_3.setTypeface(face);
		text_4.setTypeface(face);
		text_5.setTypeface(face);
		text_6.setTypeface(face);
		text_userType.setTypeface(face1);

		linearLayout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, CameraTestActivity.class);
				startActivity(intent);
			}
		});

		linearLayout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User user = (User) Session.getSession().get("user");
				if (user.getUser_type() == User.USER_MARKET) {
					intent = new Intent(MainActivity.this,
							MarketMakeErcodeActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					// MainActivity.this.finish();
				}
				if (user.getUser_type() == User.USER_CUSTOM) {
					intent = new Intent(MainActivity.this,
							UserMakeErcodeActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					// MainActivity.this.finish();
				}
			}
		});

		linearLayout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, BorrowActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		linearLayout4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, MyTradeActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		linearLayout5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this,
						MessageListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		linearLayout6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, UserInfoActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	class GetUserThread implements Runnable {

		String user_id;

		public GetUserThread(String user_id) {
			super();
			this.user_id = user_id;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			GetUserService gus = new GetUserService();
			User user = gus.getUser(user_id);
			if (user != null) {
				Session.getSession().put("user", user);
			} else {
				Toast.makeText(getApplicationContext(), "得到user错误",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if (user != null) {
			new Thread(new GetUserThread(String.valueOf(user.getUser_id())))
					.start();
		}

		/*
		 * Handler handler = new Handler() {
		 * 
		 * public void handleMessage(Message msg) { message = (MyMessage)
		 * msg.obj; if(message==null) return;
		 * 
		 * ErCodeUtils codeUtils = new ErCodeUtils(); sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); ercodeParse = new ErCodeParse();
		 * 
		 * switch (message.getMessage_type()) { case 0: try { Bitmap bitmap =
		 * codeUtils.Create2DCode(message .getMessage_content()); ImageView img
		 * = new ImageView(getApplicationContext()); img.setImageBitmap(bitmap);
		 * map.clear(); map=ercodeParse.parse(message.getMessage_content());
		 * 
		 * new AlertDialog.Builder(new ContextThemeWrapper(
		 * getApplicationContext(), R.style.AppBaseTheme))
		 * .setIconAttribute(android.R.attr.alertDialogIcon)
		 * .setTitle("您收到转账二维码") .setView(img) .setMessage("转出方编号:" +
		 * map.get("TransferUerId") + "\n" + "转账金额:" + map.get("TransferSum") +
		 * "\n" + "转账时间:" + map.get("TransferTime") + "\n" + "转账原因:" +
		 * map.get("TransferCause")) .setPositiveButton("确认转账", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick( DialogInterface dialog, int which) {
		 * // TODO Auto-generated method stub
		 * 
		 * InsertTransferProofService itps=new
		 * InsertTransferProofService(String.valueOf(map.get("TransferUerId")),
		 * String.valueOf(user.getUser_id()), (String)map.get("TransferTime"),
		 * String.valueOf(map.get("TransferSum")),
		 * (String)map.get("TransferCause")); new Thread(itps).start();
		 * Toast.makeText(getApplicationContext(), "收款成功!", Toast.LENGTH_LONG)
		 * .show(); } }) .setNegativeButton("取消转账", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick( DialogInterface dialog, int which) {
		 * // TODO Auto-generated method stub Toast.makeText(
		 * getApplicationContext(), "您已取消转账！", Toast.LENGTH_LONG).show();
		 * return; } }).create().show(); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } break; case 1: try
		 * { Bitmap bitmap = codeUtils.Create2DCode(message
		 * .getMessage_content()); ImageView img = new
		 * ImageView(getApplicationContext()); img.setImageBitmap(bitmap);
		 * 
		 * map.clear(); map=ercodeParse.parse(message.getMessage_content());
		 * 
		 * new AlertDialog.Builder(new ContextThemeWrapper(
		 * getApplicationContext(), R.style.AppBaseTheme))
		 * .setIconAttribute(android.R.attr.alertDialogIcon)
		 * .setTitle("您收到借款二维码") .setView(img) .setMessage("借入方编号:" +
		 * map.get("BorrowUserId") + "\n" + "借入金额:" + map.get("BorrowSum") +
		 * "\n" + "借入时间:" + map.get("BorrowTime") + "\n" + "约定还款时间:" +
		 * map.get("RepayTime")) .setPositiveButton("确认借出", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick( DialogInterface dialog, int which) {
		 * // TODO Auto-generated method stub InsertBorrowProofService inps=new
		 * InsertBorrowProofService(String.valueOf(user.getUser_id()),
		 * String.valueOf(map.get("BorrowUserId")),
		 * (String)map.get("BorrowTime"), String.valueOf(map.get("BorrowSum")),
		 * (String)map.get("RepayTime")); new Thread(inps).start();
		 * Toast.makeText(getApplicationContext(), "借出成功!", Toast.LENGTH_LONG)
		 * .show(); } }) .setNegativeButton("取消借出", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick( DialogInterface dialog, int which) {
		 * // TODO Auto-generated method stub Toast.makeText(
		 * getApplicationContext(), "您已取消借出！", Toast.LENGTH_LONG).show();
		 * return; } }).create().show(); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * break; case 2: strs = message.getMessage_content().split("/");
		 * 
		 * new AlertDialog.Builder(new ContextThemeWrapper(
		 * getApplicationContext(), R.style.AppBaseTheme))
		 * .setIconAttribute(android.R.attr.alertDialogIcon)
		 * .setTitle("您收到展期申请") .setMessage(strs[0]) .setPositiveButton("同意申请",
		 * new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub OKRenewalApplyService
		 * oKRenewalApplyService=new
		 * OKRenewalApplyService(Integer.parseInt(strs[1])); new
		 * Thread(oKRenewalApplyService).start();
		 * 
		 * Toast.makeText(getApplicationContext(), "您已同意展期申请!",
		 * Toast.LENGTH_LONG) .show(); } }) .setNegativeButton("拒绝申请", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * Toast.makeText(getApplicationContext(), "您已拒绝展期申请！",
		 * Toast.LENGTH_LONG) .show(); return; } }).create().show(); break; } }
		 * };
		 */

		// GetMessageService gms=new GetMessageService(user.getUser_id(),
		// handler);
		// new Thread(gms).start();
	}

	protected void onStart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if (user != null) {
			new Thread(new GetUserThread(String.valueOf(user.getUser_id())))
					.start();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.open:
			
			sharedPreferenceds = getSharedPreferences("light",
					Context.MODE_PRIVATE);
			int count = sharedPreferenceds.getInt("light", 0);
			
			if(count==0)
				item.setTitle("开启挥手发送");
			else
				item.setTitle("关闭挥手发送");
			System.out.println("count"+count);
			
			if(count==0)
			{
				Editor editor = sharedPreferenceds.edit();
				editor.putInt("light", 1);
				editor.commit();
			}
			else
			{
				Editor editor = sharedPreferenceds.edit();
				editor.putInt("light", 0);
				editor.commit();
			}
			
			return true;
		case R.id.toHome:
			return true;
		case R.id.sign_out:
			System.exit(0);
			return true;
		}
		
		return false;
	}
}
